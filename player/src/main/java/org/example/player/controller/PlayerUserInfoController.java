package org.example.player.controller;

import ma.glasnost.orika.MapperFacade;
import org.apache.commons.lang3.StringUtils;
import org.example.config.log.TLogAspectExt;
import org.example.exception.enums.GlobalErrorCodeConstants;
import org.example.exception.util.ServiceExceptionUtil;
import org.example.operatelog.core.enums.OperateTypeEnum;
import org.example.player.common.PlayerErrorCodeConstants;
import org.example.player.config.log.PlayerLogConvert;
import org.example.player.config.sa.StpPlayerUtil;
import org.example.player.vo.DreamUUserLoginPwdVO;
import org.example.player.vo.DreamUUserPayPwdVO;
import org.example.player.vo.DreamUUserSimpleVO;
import org.example.po.dream.dream.DreamUUserDO;
import org.example.pojo.CommonResult;
import org.example.service.dream.DreamUFinanceMainService;
import org.example.service.dream.DreamUUserService;
import org.example.vo.dream.financeMain.DreamUFinanceMainVO;
import org.example.vo.dream.user.DreamUUserPwdReqVO;
import org.example.vo.dream.user.DreamUUserSimpleReqVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("/player/user")
public class PlayerUserInfoController {

    @Resource
    private DreamUUserService dreamUUserService;
    @Resource
    private MapperFacade mapperFacade;
    @Resource
    private DreamUFinanceMainService dreamUFinanceMainService;


    /**
     * 查询用户基本信息
     *
     * @param request
     * @param response
     * @return
     */
    @TLogAspectExt(str = "查询用户基本信息", moduleName = "USER", type = OperateTypeEnum.GET, convert = PlayerLogConvert.class)
    @PostMapping(value = "/getUserInfo")
    public CommonResult<DreamUUserSimpleVO> getUserInfo(HttpServletRequest request, HttpServletResponse response) {

        DreamUUserDO dreamUUserDO = dreamUUserService.selectById(StpPlayerUtil.getLoginIdAsLong());
        if (dreamUUserDO == null) {
            throw ServiceExceptionUtil.exception(PlayerErrorCodeConstants.USER_NOT_FOUND);
        }
        DreamUUserSimpleVO map = mapperFacade.map(dreamUUserDO, DreamUUserSimpleVO.class);
        // 设置是否设置了交易密码
        DreamUFinanceMainVO dreamUFinanceMainVO = dreamUFinanceMainService.setectByUserId(StpPlayerUtil.getLoginIdAsLong());
        if (dreamUFinanceMainVO == null || StringUtils.isBlank(dreamUFinanceMainVO.getPassword())) {
            map.setPayPassword(Boolean.FALSE);
        } else {
            map.setPayPassword(Boolean.TRUE);
        }
        if (dreamUFinanceMainVO != null) {
            map.setMargin(dreamUFinanceMainVO.getMargin());
            map.setMoneyUsable(dreamUFinanceMainVO.getMoneyUsable());
            map.setMoneyDrawUsable(dreamUFinanceMainVO.getMoneyDrawUsable());
            map.setMoneyUsable(dreamUFinanceMainVO.getMoneyUsable());
        }
        map.setTokenName(StpPlayerUtil.getTokenName());   ;
        map.setTokenValue(StpPlayerUtil.getTokenValue());   ;
        return CommonResult.success(map);
    }


    /**
     * 更新用户基本信息
     *
     * @param dreamUUserSimpleReqVO
     * @return
     */
    @TLogAspectExt(str = "更新用户基本信息", moduleName = "USER", type = OperateTypeEnum.UPDATE, convert = PlayerLogConvert.class)
    @PostMapping("/updateUserInfo")
    public CommonResult<Boolean> updateUserInfo(@RequestBody DreamUUserSimpleReqVO dreamUUserSimpleReqVO) {
        DreamUUserSimpleReqVO updateReqVO = mapperFacade.map(dreamUUserSimpleReqVO, DreamUUserSimpleReqVO.class);
        updateReqVO.setId( StpPlayerUtil.getLoginIdAsLong());

        return CommonResult.success(dreamUUserService.updateUserInfo(updateReqVO));
    }


    /**
     * 修改登录密码
     *
     * @param dreamUUserLoginPwdVO
     * @return
     */
    @TLogAspectExt(str = "修改登录密码", moduleName = "USER", type = OperateTypeEnum.UPDATE, convert = PlayerLogConvert.class)
    @PostMapping("/changeLoginPassword")
    public CommonResult<Boolean> changeLoginPassword(@RequestBody DreamUUserLoginPwdVO dreamUUserLoginPwdVO) {
        if (!dreamUUserLoginPwdVO.getNewPwd().equals(dreamUUserLoginPwdVO.getNewAgainPwd())) {
            throw ServiceExceptionUtil.exception(GlobalErrorCodeConstants.CONFIRM_PWD_NOT_SAME);
        }

        DreamUUserPwdReqVO updateReqVO = mapperFacade.map(dreamUUserLoginPwdVO, DreamUUserPwdReqVO.class);
        updateReqVO.setId(StpPlayerUtil.getLoginIdAsLong());

        return CommonResult.success(dreamUUserService.changeLoginPassword(updateReqVO));
    }


    /**
     * 修改交易密码
     *
     * @param dreamUUserPayPwdVO
     * @return
     */
    @TLogAspectExt(str = "修改交易密码", moduleName = "USER", type = OperateTypeEnum.UPDATE, convert = PlayerLogConvert.class)
    @PostMapping("/changePayPassword")
    public CommonResult<Boolean> changePayPassword(@RequestBody DreamUUserPayPwdVO dreamUUserPayPwdVO) {
        if (!dreamUUserPayPwdVO.getNewPwd().equals(dreamUUserPayPwdVO.getNewAgainPwd())) {
            throw ServiceExceptionUtil.exception(GlobalErrorCodeConstants.CONFIRM_PWD_NOT_SAME);
        }

        DreamUUserPwdReqVO updateReqVO = mapperFacade.map(dreamUUserPayPwdVO, DreamUUserPwdReqVO.class);
        updateReqVO.setId(StpPlayerUtil.getLoginIdAsLong());

        return CommonResult.success(dreamUFinanceMainService.changePayPassword(updateReqVO));
    }

}
