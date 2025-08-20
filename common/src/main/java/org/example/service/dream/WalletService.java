package org.example.service.dream;


import cn.dev33.satoken.secure.BCrypt;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.json.JSONUtil;
import com.yomahub.liteflow.core.FlowExecutor;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import org.apache.commons.lang3.StringUtils;
import org.example.config.mybatisplus.LambdaQueryWrapperX;
import org.example.exception.enums.GlobalErrorCodeConstants;
import org.example.exception.util.ServiceExceptionUtil;
import org.example.po.dream.dream.*;
import org.example.vo.DepositOrderReq;
import org.example.vo.WithdrawOrderReq;
import org.example.vo.dream.financeIo.DreamUFinanceIoReqVo;
import org.example.vo.dream.financeMain.DreamUFinanceMainVO;
import org.example.vo.dream.user.AddOrSubReq;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import static org.example.exception.util.ServiceExceptionUtil.exception;

@Slf4j
@Service
public class WalletService {

    @Resource
    private DreamDepositVirAddressService dreamDepositVirAddressService;
    @Resource
    private DreamDepositTypeRuleService depositTypeRuleService;
    @Resource
    private DreamDepositTypeService depositTypeService;

    @Resource
    private MapperFacade mapperFacade;
    @Resource
    private DreamUUserService dreamUUserService;
    @Resource
    private DreamUFinanceMainService dreamUFinanceMainService;
    @Resource
    private DreamUFinanceIoService dreamUFinanceIoService;
    @Resource
    private DreamUFinanceFlowService dreamUFinanceFlowService;

    @Resource
    private DreamWithdrawTypeRuleService withdrawTypeRuleService;
    @Resource
    private DreamWithdrawTypeService withdrawTypeService;
    @Resource
    private DreamUVirAddressService dreamUVirAddressService;
    @Resource
    private DreamUFinanceIoCredentialsService dreamUFinanceIoCredentials;
    @Resource
    private FlowExecutor flowExecutor;


    /**
     * 充值的业务
     *
     * @param depositOrderReq
     * @param userId
     */
    @Transactional(rollbackFor = Exception.class)
    public Boolean deposit(DepositOrderReq depositOrderReq, long userId) {
        // 开始各种参数验证
        log.info("用户：{}开始发起充值订单，请求参数：{}",userId, JSONUtil.toJsonStr(depositOrderReq));
        //验证用户
        DreamUUserDO dreamUUserDO = dreamUUserService.selectById(userId);
        if (dreamUUserDO == null) {
            throw ServiceExceptionUtil.exception(GlobalErrorCodeConstants.USER_NOT_FOUND);
        }
        if (dreamUUserDO.getStatus() != 0) {
            throw ServiceExceptionUtil.exception(GlobalErrorCodeConstants.PARAMS_STATUS_IS_NOT_SUPORT_PAY);
        }
        DreamUFinanceMainVO dreamUFinanceMainVO = dreamUFinanceMainService.setectByUserId(userId);
        if (dreamUFinanceMainVO == null || StringUtils.isBlank(dreamUFinanceMainVO.getPassword())) {
            throw ServiceExceptionUtil.exception(GlobalErrorCodeConstants.USER_NOT_SET_PAY_PASSWORD);
        }
        //验证密码
        if (!BCrypt.hashpw(depositOrderReq.getPassword(), dreamUFinanceMainVO.getSalt()).equals(dreamUFinanceMainVO.getPassword())) {
            throw exception(GlobalErrorCodeConstants.PASSWORD_IS_ERROR);
        }


        // 验证充值方式
        DreamDepositTypeDO dreamDepositTypeDO = depositTypeService.selectByDepositId(depositOrderReq.getDepositTypeId());
        if (dreamDepositTypeDO == null) {
            log.warn("充值方式未查询到");
            throw ServiceExceptionUtil.exception(GlobalErrorCodeConstants.DEPOSIT_TYPE_IS_NULL);
        }

        //验证充值方式规则
        DreamDepositTypeRuleDO ruleDO = depositTypeRuleService.selectByDepositTypeId(depositOrderReq.getDepositTypeId());
        DreamDepositTypeRuleDO dreamDepositTypeRuleDO = depositTypeRuleService.selectById(depositOrderReq.getRuleId(), depositOrderReq.getDepositTypeId());
        if (dreamDepositTypeRuleDO == null || ruleDO == null || !ruleDO.getId().equals(depositOrderReq.getRuleId())) {
            log.warn("充值方式对应充值规则未查询到");
            throw ServiceExceptionUtil.exception(GlobalErrorCodeConstants.DEPOSIT_TYPE_IS_NULL);
        }

        if (depositOrderReq.getAmount().compareTo(ruleDO.getMinAmount()) < 0 || depositOrderReq.getAmount().compareTo(ruleDO.getMaxAmount()) > 0) {
            log.warn("充值金额超过规则限制");
            throw ServiceExceptionUtil.exception(GlobalErrorCodeConstants.DEPOSIT_TYPE_IS_ERROR);
        }

        DreamDepositVirAddressDO dreamDepositVirAddressDO = dreamDepositVirAddressService.selectByAddressId(depositOrderReq.getAddressId());

        if (dreamDepositVirAddressDO == null) {
            throw ServiceExceptionUtil.exception(GlobalErrorCodeConstants.DEPOSIT_ADDRESS_IS_NULL);
        }

        //更新保证金余额
        dreamUFinanceMainVO.setMargin(dreamUFinanceMainVO.getMargin().add(depositOrderReq.getAmount()));
        dreamUFinanceMainService.update(dreamUFinanceMainVO);

        //插入 订单
        DreamUFinanceIoDO dreamUFinanceIoDO = new DreamUFinanceIoDO();


        long snowflakeNextId = IdUtil.getSnowflakeNextId();
        dreamUFinanceIoDO.setId(snowflakeNextId);
        dreamUFinanceIoDO.setUserId(userId);
        // 支付类型
        dreamUFinanceIoDO.setPayTypeId(depositOrderReq.getDepositTypeId());
        dreamUFinanceIoDO.setRuleId(depositOrderReq.getRuleId());
        // 支付类型名称
        dreamUFinanceIoDO.setLabel(dreamDepositVirAddressDO.getProtocol());
        //支付来源地址
        dreamUFinanceIoDO.setSrc(dreamDepositVirAddressDO.getAddress());

        dreamUFinanceIoDO.setType(10);
        dreamUFinanceIoDO.setTypeDetail(101);
        dreamUFinanceIoDO.setPlatformId(1);
        dreamUFinanceIoDO.setRemark(String.format(DEPOSIT_REMARK, userId, DateUtil.now(), dreamDepositTypeDO.getName()
                , depositOrderReq.getAmount(), dreamDepositVirAddressDO.getProtocol(), dreamDepositVirAddressDO.getAddress()));

        dreamUFinanceIoDO.setStatus(0);
        dreamUFinanceIoDO.setSelfOrderId(String.valueOf(userId));
        dreamUFinanceIoDO.setCommission(depositOrderReq.getAmount().multiply(dreamDepositTypeRuleDO.getCharges()).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP).doubleValue());
        dreamUFinanceIoDO.setMoney(depositOrderReq.getAmount().doubleValue());
        dreamUFinanceIoDO.setActAmount(depositOrderReq.getAmount().doubleValue() - depositOrderReq.getAmount().multiply(dreamDepositTypeRuleDO.getCharges()).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP).doubleValue());
        dreamUFinanceIoService.save(dreamUFinanceIoDO);

        DreamUFinanceFlowDO dreamUFinanceFlowDO = new DreamUFinanceFlowDO();
        dreamUFinanceFlowDO.setType(10);
        dreamUFinanceFlowDO.setTypeDetail(101);
        dreamUFinanceFlowDO.setRemark("用户充值创建订单，资金添加到保证金");
        dreamUFinanceFlowDO.setMoney(depositOrderReq.getAmount());
        dreamUFinanceFlowDO.setIoOrderId(snowflakeNextId);
        dreamUFinanceFlowDO.setUserId(userId);
        dreamUFinanceFlowService.save(dreamUFinanceFlowDO);

        return Boolean.TRUE;
    }

    private static final String DEPOSIT_REMARK = "用户【%s】在【%S】选择【%s】充值【%s】,收款信息为：【%s】的地址【%s】";

    /**
     * 提款的业务
     */
    @Transactional(rollbackFor = Exception.class)
    public Boolean withdrew(WithdrawOrderReq withdrawOrderReq, long userId) {
        // 开始各种参数验证
        //验证用户
        DreamUUserDO dreamUUserDO = dreamUUserService.selectById(userId);
        if (dreamUUserDO == null) {
            throw ServiceExceptionUtil.exception(GlobalErrorCodeConstants.USER_NOT_FOUND);
        }
        if (dreamUUserDO.getStatus() != 0) {
            throw ServiceExceptionUtil.exception(GlobalErrorCodeConstants.PARAMS_STATUS_IS_NOT_SUPORT_PAY);
        }
        DreamUFinanceMainVO dreamUFinanceMainVO = dreamUFinanceMainService.setectByUserId(userId);
        if (dreamUFinanceMainVO == null || StringUtils.isBlank(dreamUFinanceMainVO.getPassword())) {
            throw ServiceExceptionUtil.exception(GlobalErrorCodeConstants.USER_NOT_SET_PAY_PASSWORD);
        }
        //验证密码
//        if (!BCrypt.hashpw(authReqVO.getPassword(), dreamUUserDO.getPassSalt()).equals(dreamUUserDO.getUserPass())) {
        if (!BCrypt.hashpw(withdrawOrderReq.getPassword(), dreamUFinanceMainVO.getSalt()).equals(dreamUFinanceMainVO.getPassword())) {
            throw exception(GlobalErrorCodeConstants.PASSWORD_IS_ERROR);
        }
        // 验证可提现余额是否可以提款
        if (dreamUFinanceMainVO.getMoneyDrawUsable().compareTo(withdrawOrderReq.getAmount()) < 0) {
            throw ServiceExceptionUtil.exception(GlobalErrorCodeConstants.INSUFFICIENT_BALANCE);
        }
        //可提现资金moneyDrawUsable
        if (dreamUFinanceMainVO.getMoneyUsable().compareTo(withdrawOrderReq.getAmount()) < 0) {
            throw ServiceExceptionUtil.exception(GlobalErrorCodeConstants.INSUFFICIENT_MONEY_USABLE);
        }
        DreamUVirAddressDO oneReviewedBy = null;
        if (withdrawOrderReq.getAddressId() != null){
            //验证地址是否可以用
            oneReviewedBy = dreamUVirAddressService.getOneReviewedBy(withdrawOrderReq.getAddressId(), userId);
            if (oneReviewedBy == null) {
                throw ServiceExceptionUtil.exception(GlobalErrorCodeConstants.USER_VIR_ADDRESS_IS_ERROR);
            }
            if (!oneReviewedBy.getAddress().equals(withdrawOrderReq.getAddress())) {
                throw ServiceExceptionUtil.exception(GlobalErrorCodeConstants.USER_VIR_ADDRESS_IS_ERROR);
            }
        }



        // 验证充值方式
        DreamWithdrawTypeDO dreamDepositTypeDO = withdrawTypeService.selectByWithdrawTypeId(withdrawOrderReq.getWithdrawId());
        if (dreamDepositTypeDO == null) {
            log.warn("充值方式未查询到");
            throw ServiceExceptionUtil.exception(GlobalErrorCodeConstants.DEPOSIT_TYPE_IS_NULL);
        }

        //验证充值方式规则
        DreamWithdrawTypeRuleDO ruleDO = withdrawTypeRuleService.selectByWithdrawTypeId(withdrawOrderReq.getWithdrawId());
        if (ruleDO == null) {
            log.warn("充值方式对应充值规则未查询到");
            throw ServiceExceptionUtil.exception(GlobalErrorCodeConstants.DEPOSIT_TYPE_IS_NULL);
        }
//        if (!ruleDO.getId().equals(withdrawOrderReq.getRuleId())){
//
//        }
        if (withdrawOrderReq.getAmount().compareTo(ruleDO.getMinAmount()) < 0 || withdrawOrderReq.getAmount().compareTo(ruleDO.getMaxAmount()) > 0) {
            log.warn("提款金额超过规则限制");
            throw ServiceExceptionUtil.exception(GlobalErrorCodeConstants.DEPOSIT_TYPE_IS_ERROR);
        }


        //更新余额
        dreamUFinanceMainVO.setMoneyUsable(dreamUFinanceMainVO.getMoneyUsable().subtract(withdrawOrderReq.getAmount()));
        dreamUFinanceMainVO.setMoneyDrawUsable(dreamUFinanceMainVO.getMoneyDrawUsable().subtract(withdrawOrderReq.getAmount()));
        dreamUFinanceMainVO.setMoneyFrozen(dreamUFinanceMainVO.getMoneyFrozen().add(withdrawOrderReq.getAmount()));
        dreamUFinanceMainService.update(dreamUFinanceMainVO);

        //插入 订单
        DreamUFinanceIoDO dreamUFinanceIoDO = new DreamUFinanceIoDO();

        dreamUFinanceIoDO.setPayTypeId(withdrawOrderReq.getWithdrawId());
        dreamUFinanceIoDO.setRuleId(ruleDO.getId());

        if (oneReviewedBy == null){
            if (StringUtils.isAnyBlank(withdrawOrderReq.getProtocol(),withdrawOrderReq.getAddress())){
                throw exception(GlobalErrorCodeConstants.BAD_REQUEST);
            }
            dreamUFinanceIoDO.setLabel(withdrawOrderReq.getProtocol());
            dreamUFinanceIoDO.setSrc(withdrawOrderReq.getAddress());
        }else {
            dreamUFinanceIoDO.setLabel(oneReviewedBy.getProtocol());
            dreamUFinanceIoDO.setSrc(oneReviewedBy.getAddress());
        }



        long snowflakeNextId = IdUtil.getSnowflakeNextId();
        dreamUFinanceIoDO.setId(snowflakeNextId);
        dreamUFinanceIoDO.setUserId(userId);

        dreamUFinanceIoDO.setType(20);
        dreamUFinanceIoDO.setTypeDetail(201);
        dreamUFinanceIoDO.setPlatformId(1);
        dreamUFinanceIoDO.setRemark(String.format(DEPOSIT_REMARK, userId, DateUtil.now(), dreamDepositTypeDO.getName()
                , withdrawOrderReq.getAmount(),oneReviewedBy ==null? withdrawOrderReq.getProtocol():oneReviewedBy.getProtocol(), oneReviewedBy == null?withdrawOrderReq.getAddress():oneReviewedBy.getAddress()));

        dreamUFinanceIoDO.setStatus(0);
        dreamUFinanceIoDO.setSelfOrderId(String.valueOf(userId));
        dreamUFinanceIoDO.setCommission(withdrawOrderReq.getAmount().multiply(ruleDO.getCharges()).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP).doubleValue());
        dreamUFinanceIoDO.setMoney(withdrawOrderReq.getAmount().doubleValue() - withdrawOrderReq.getAmount().multiply(ruleDO.getCharges()).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP).doubleValue());
        dreamUFinanceIoService.save(dreamUFinanceIoDO);

        DreamUFinanceFlowDO dreamUFinanceFlowDO = new DreamUFinanceFlowDO();
        dreamUFinanceFlowDO.setType(20);
        dreamUFinanceFlowDO.setTypeDetail(201);
        dreamUFinanceFlowDO.setRemark("用户提款创建订单，资金扣减");
        dreamUFinanceFlowDO.setMoney(withdrawOrderReq.getAmount());
        dreamUFinanceFlowDO.setIoOrderId(snowflakeNextId);
        dreamUFinanceFlowDO.setUserId(userId);
        dreamUFinanceFlowService.save(dreamUFinanceFlowDO);

        return Boolean.TRUE;

    }





    /**
     * 需要补充 状态机的判断， 数据的验证
     * @param pageVO
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public Boolean orderIoAudit(DreamUFinanceIoReqVo pageVO) {
        log.info("订单审核：{}", JSONUtil.toJsonStr(pageVO));

        DreamUFinanceIoDO dreamUFinanceIoDO = dreamUFinanceIoService.getBaseMapper().selectById(pageVO.getId());
        log.info("订单详情信息：{}", JSONUtil.toJsonStr(dreamUFinanceIoDO));
        if (dreamUFinanceIoDO == null){
            throw exception(GlobalErrorCodeConstants.NOT_FOUND_DATA);
        }
        if (pageVO.getActAmount() != null){
            dreamUFinanceIoDO.setActAmount(pageVO.getActAmount().doubleValue());
        }
//        else if (dreamUFinanceIoDO.getActAmount() == null){
//            dreamUFinanceIoDO.setActAmount(dreamUFinanceIoDO.getMoney());
//        }
        // 验证用户状态
        DreamUUserDO dreamUUserDO = dreamUUserService.selectById(dreamUFinanceIoDO.getUserId());
        if (dreamUUserDO == null) {
            throw exception(GlobalErrorCodeConstants.NOT_FOUND_DATA);
        }
        //用户状态0正常，1删除，2禁止登录,3禁止交易
        if (dreamUUserDO.getStatus() != 0) {
            throw exception(GlobalErrorCodeConstants.USER_STATUS_IS_WARN_NOT_FINANCE);
        }



        DreamUFinanceMainVO dreamUFinanceMainVO = dreamUFinanceMainService.setectByUserId(dreamUFinanceIoDO.getUserId());
        log.info("用户资金情况：{}",JSONUtil.toJsonStr(dreamUFinanceMainVO));
        if (dreamUFinanceMainVO == null){
            throw exception(GlobalErrorCodeConstants.NOT_FOUND_DATA);
        }
        DreamUFinanceFlowDO dreamUFinanceFlowDO = new DreamUFinanceFlowDO();
        if (StringUtils.isNotBlank(pageVO.getAuditRemark())){
            dreamUFinanceFlowDO.setAuditRemark(pageVO.getAuditRemark());
        }
        dreamUFinanceFlowDO.setUserId(dreamUFinanceMainVO.getUserId());
        dreamUFinanceFlowDO.setMoney(BigDecimal.valueOf(dreamUFinanceIoDO.getActAmount()));
        dreamUFinanceFlowDO.setIoOrderId(dreamUFinanceIoDO.getId());
        // 充值确认到账情况
        if (pageVO.getType() == 10 && (pageVO.getStatus() ==2 || pageVO.getStatus() == -2)){
            log.info("充值订单确认到账");
            //添加资金变更记录

            dreamUFinanceFlowDO.setType(10);
            dreamUFinanceFlowDO.setRemark("用户提款创建订单，资金扣减");
            if (pageVO.getStatus() == 2 ){
                // 资金增加，添加资金记录  保证金扣减，添加到可用余额中
                dreamUFinanceMainVO.setMargin(dreamUFinanceMainVO.getMargin().subtract(new BigDecimal(dreamUFinanceIoDO.getMoney())));
                dreamUFinanceMainVO.setMoneyUsable(dreamUFinanceMainVO.getMoneyUsable().add(new BigDecimal(dreamUFinanceIoDO.getActAmount())));
                // TODO 这里的可提现资金 保存和 可用余额一致
                dreamUFinanceMainVO.setMoneyDrawUsable(dreamUFinanceMainVO.getMoneyDrawUsable().add(new BigDecimal(dreamUFinanceIoDO.getActAmount())));
                dreamUFinanceFlowDO.setTypeDetail(103);
                dreamUFinanceFlowDO.setRemark("确认到账，支付成功");
            }else {
                dreamUFinanceMainVO.setMargin(dreamUFinanceMainVO.getMargin().subtract(new BigDecimal(dreamUFinanceIoDO.getMoney())));
                dreamUFinanceFlowDO.setTypeDetail(102);
                dreamUFinanceFlowDO.setRemark("确认未到账，支付失败");
                dreamUFinanceIoDO.setActAmount((double) 0);
            }
            //资金变更
        }else if (pageVO.getType() == 20 && (pageVO.getStatus() ==1 || pageVO.getStatus() == -1)){
            log.info("提款订单审核");
            // 仅变更状态，不处理资金
        }else if (pageVO.getType() == 20 && (pageVO.getStatus() ==2 || pageVO.getStatus() == -2)){
            log.info("提款订单支付");
            dreamUFinanceFlowDO.setType(20);
            dreamUFinanceFlowDO.setRemark("用户提款创建订单，资金扣减");
            if (pageVO.getStatus() == 2 ){
                // 冻结资金扣减
                dreamUFinanceMainVO.setMoneyFrozen(dreamUFinanceMainVO.getMoneyFrozen().subtract(new BigDecimal(dreamUFinanceIoDO.getMoney())));
                dreamUFinanceFlowDO.setTypeDetail(202);
                dreamUFinanceFlowDO.setRemark("确认提款已支付，支付成功");
            }else {
                //冻结资金扣减
                dreamUFinanceMainVO.setMoneyFrozen(dreamUFinanceMainVO.getMoneyFrozen().subtract(new BigDecimal(dreamUFinanceIoDO.getMoney())));
                // 可用余额 添加
                dreamUFinanceMainVO.setMoneyUsable(dreamUFinanceMainVO.getMoneyUsable().add(new BigDecimal(dreamUFinanceIoDO.getMoney())));
                // 可提现资金 添加
                dreamUFinanceMainVO.setMoneyDrawUsable(dreamUFinanceMainVO.getMoneyDrawUsable().add(new BigDecimal(dreamUFinanceIoDO.getMoney())));
                dreamUFinanceFlowDO.setTypeDetail(203);
                dreamUFinanceFlowDO.setRemark("确认无法支付，支付失败");
                dreamUFinanceIoDO.setActAmount((double) 0);
            }
        }else{
            throw exception(GlobalErrorCodeConstants.INTERNAL_SERVER_ERROR);
        }
        if (dreamUFinanceFlowDO.getType() != null){
            log.info("用户资金更新为:{}",dreamUFinanceMainVO);
            dreamUFinanceMainService.update(dreamUFinanceMainVO);
            log.info("资金记录更新为:{}",dreamUFinanceFlowDO);
            dreamUFinanceFlowService.save(dreamUFinanceFlowDO);
        }


        dreamUFinanceIoDO.setStatus(pageVO.getStatus());
        log.info("订单记录更新为:{}",dreamUFinanceIoDO);

//        创建凭证表信息
        List<String> filePath = pageVO.getFilePath();
        if (CollectionUtil.isNotEmpty(filePath)){
            List<DreamUFinanceIoCredentialsDO> dreamUFinanceIoCredentialsDOS = new ArrayList<>();
            for (String s : filePath) {
                DreamUFinanceIoCredentialsDO dreamUFinanceIoCredentialsDO = new DreamUFinanceIoCredentialsDO();
                dreamUFinanceIoCredentialsDO.setFileUrl(s);
                dreamUFinanceIoCredentialsDO.setIoOrderId(dreamUFinanceIoDO.getId());
                dreamUFinanceIoCredentialsDOS.add(dreamUFinanceIoCredentialsDO);
                dreamUFinanceIoCredentialsDO.setFlowId(dreamUFinanceFlowDO.getId());
            }
            dreamUFinanceIoCredentials.saveBatch(dreamUFinanceIoCredentialsDOS);
        }

        int update =  dreamUFinanceIoService.getBaseMapper().update(dreamUFinanceIoDO, new LambdaQueryWrapperX<DreamUFinanceIoDO>()
                .eq(DreamUFinanceIoDO::getId, pageVO.getId()));

        flowExecutor.execute2Future("financeAcc", dreamUFinanceIoDO, DreamUFinanceIoDO.class);
        return update>0;
    }


    /**
     * 手动上下分
     * @param updateReqVO
     * @return
     */

    @Transactional(rollbackFor = Exception.class)
    public Boolean addOrSub(AddOrSubReq updateReqVO) {
        DreamUFinanceFlowDO dreamUFinanceFlowDO = new DreamUFinanceFlowDO();
        DreamUFinanceMainDO dreamUFinanceMainDO = dreamUFinanceMainService.setectByUserId(updateReqVO.getUserId());

        long snowflakeNextId = IdUtil.getSnowflakeNextId();

        //加款订单
        if (updateReqVO.getType() == 10){

            dreamUFinanceMainDO.setMargin(dreamUFinanceMainDO.getMargin().add(updateReqVO.getMoney()));
            dreamUFinanceMainService.update(mapperFacade.map(dreamUFinanceMainDO,DreamUFinanceMainVO.class));


            dreamUFinanceFlowDO.setType(10);
            dreamUFinanceFlowDO.setTypeDetail(103);

            dreamUFinanceFlowDO.setUserId(updateReqVO.getUserId());
            dreamUFinanceFlowDO.setMoney(updateReqVO.getMoney());
            dreamUFinanceFlowDO.setIoOrderId(snowflakeNextId);

            dreamUFinanceFlowDO.setCOrderId(String.valueOf(updateReqVO.getOrderId()));

            dreamUFinanceFlowDO.setRemark(updateReqVO.getRemark());

            dreamUFinanceFlowService.save(dreamUFinanceFlowDO);

            DreamUFinanceIoDO dreamUFinanceIoDO = new DreamUFinanceIoDO();
            dreamUFinanceIoDO.setId(snowflakeNextId);
            dreamUFinanceIoDO.setType(10);
            dreamUFinanceIoDO.setTypeDetail(103);

            dreamUFinanceIoDO.setSelfOrderId(String.valueOf(updateReqVO.getOrderId()));

            dreamUFinanceIoDO.setStatus(0);
            dreamUFinanceIoDO.setUserId(updateReqVO.getUserId());
            dreamUFinanceIoDO.setRemark(updateReqVO.getRemark());

            dreamUFinanceIoDO.setCommission(0d);
            dreamUFinanceIoDO.setMoney(updateReqVO.getMoney().doubleValue());
            dreamUFinanceIoDO.setActAmount(updateReqVO.getMoney().doubleValue());
            dreamUFinanceIoService.save(dreamUFinanceIoDO);
        }else if (updateReqVO.getType() == 20){

            dreamUFinanceMainDO.setMoneyUsable(dreamUFinanceMainDO.getMoneyUsable().subtract(updateReqVO.getMoney()));
            dreamUFinanceMainDO.setMoneyDrawUsable(dreamUFinanceMainDO.getMoneyDrawUsable().subtract(updateReqVO.getMoney()));
            dreamUFinanceMainDO.setMargin(dreamUFinanceMainDO.getMargin().add(updateReqVO.getMoney()));
            dreamUFinanceMainService.update(mapperFacade.map(dreamUFinanceMainDO,DreamUFinanceMainVO.class));

            dreamUFinanceFlowDO.setType(20);
            dreamUFinanceFlowDO.setTypeDetail(205);
            dreamUFinanceFlowDO.setUserId(updateReqVO.getUserId());
            dreamUFinanceFlowDO.setMoney(updateReqVO.getMoney());
            dreamUFinanceFlowDO.setIoOrderId(snowflakeNextId);

            dreamUFinanceFlowDO.setCOrderId(String.valueOf(updateReqVO.getOrderId()));
            dreamUFinanceFlowDO.setRemark(updateReqVO.getRemark());

            dreamUFinanceFlowService.save(dreamUFinanceFlowDO);

            DreamUFinanceIoDO dreamUFinanceIoDO = new DreamUFinanceIoDO();
            dreamUFinanceIoDO.setId(snowflakeNextId);
            dreamUFinanceIoDO.setType(20);
            dreamUFinanceIoDO.setTypeDetail(205);

            dreamUFinanceIoDO.setSelfOrderId(String.valueOf(updateReqVO.getOrderId()));

            dreamUFinanceIoDO.setStatus(0);
            dreamUFinanceIoDO.setUserId(updateReqVO.getUserId());

            dreamUFinanceIoDO.setCommission(0d);
            dreamUFinanceIoDO.setRemark(updateReqVO.getRemark());
            dreamUFinanceIoDO.setMoney(updateReqVO.getMoney().doubleValue());
            dreamUFinanceIoDO.setActAmount(updateReqVO.getMoney().doubleValue());
            dreamUFinanceIoService.save(dreamUFinanceIoDO);

        }else {
            throw exception(GlobalErrorCodeConstants.PAY_IS_ERROR);
        }

        return Boolean.TRUE;
    }
}
