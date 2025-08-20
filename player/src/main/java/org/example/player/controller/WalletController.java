package org.example.player.controller;


import cn.hutool.core.util.ObjectUtil;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import org.example.config.log.TLogAspectExt;
import org.example.exception.enums.GlobalErrorCodeConstants;
import org.example.exception.util.ServiceExceptionUtil;
import org.example.operatelog.core.enums.OperateTypeEnum;
import org.example.player.config.log.PlayerLogConvert;
import org.example.player.config.sa.StpPlayerUtil;
import org.example.player.vo.VirAddressVO;
import org.example.po.dream.dream.DreamUVirAddressDO;
import org.example.pojo.CommonResult;
import org.example.service.dream.DreamUFinanceMainService;
import org.example.service.dream.DreamUVirAddressService;
import org.example.vo.dream.financeMain.DreamUFinanceMainSimpleVO;
import org.example.vo.dream.financeMain.DreamUFinanceMainVO;
import org.example.vo.dream.virAddress.AddVirAddressReq;
import org.example.vo.dream.virAddress.DreamUVirAddressVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;


/**
 * 我的钱包
 */
@Slf4j
@RestController
@RequestMapping("/player/wallet")
public class WalletController {

    @Resource
    private DreamUFinanceMainService dreamUFinanceMainService;
    @Resource
    private MapperFacade mapperFacade;
    @Resource
    private DreamUVirAddressService dreamUVirAddressService;

    /**
     * 获取我的资金
     *
     * @return
     */
    @TLogAspectExt(str = "获取我的资金", moduleName = "Wallet", type = OperateTypeEnum.GET, convert = PlayerLogConvert.class)
    @PostMapping("/getMyWallet")
    public CommonResult<DreamUFinanceMainSimpleVO> getMyWallet() {
        long loginIdAsLong = StpPlayerUtil.getLoginIdAsLong();
        DreamUFinanceMainVO dreamUFinanceMainVO = dreamUFinanceMainService.setectByUserId(loginIdAsLong);
        return CommonResult.success(mapperFacade.map(dreamUFinanceMainVO, DreamUFinanceMainSimpleVO.class));
    }

    /**
     * 获取我的地址
     */
    @TLogAspectExt(str = "获取我的地址", moduleName = "Wallet", type = OperateTypeEnum.GET, convert = PlayerLogConvert.class)
    @PostMapping("/getMyVirAddressList")
    public CommonResult<List<DreamUVirAddressVO>> getMyVirAddressList() {
        long loginIdAsLong = StpPlayerUtil.getLoginIdAsLong();
        List<DreamUVirAddressDO> dreamUVirAddressDOS = dreamUVirAddressService.setectByUserId(loginIdAsLong);
        return CommonResult.success(mapperFacade.mapAsList(dreamUVirAddressDOS, DreamUVirAddressVO.class));
    }


    /**
     * 添加我的地址
     *
     * @return
     */
    @TLogAspectExt(str = "添加我的地址", moduleName = "Wallet", type = OperateTypeEnum.CREATE, convert = PlayerLogConvert.class)
    @PostMapping("/addVirAddressList")
    public CommonResult<Boolean> addVirAddressList(@RequestBody AddVirAddressReq addVirAddressReq) {
        DreamUVirAddressDO dreamUVirAddressDO = mapperFacade.map(addVirAddressReq, DreamUVirAddressDO.class);
        dreamUVirAddressDO.setUserId(StpPlayerUtil.getLoginIdAsLong());
        dreamUVirAddressDO.setId(null);

        //添加我的地址
        return CommonResult.success(dreamUVirAddressService.addVirAddress(dreamUVirAddressDO));
    }


    /**
     * 切换默认 ，这里只能选择已审核通过的
     *
     * @param id
     * @return
     */
    @TLogAspectExt(str = "切换默认", moduleName = "Wallet", type = OperateTypeEnum.UPDATE, convert = PlayerLogConvert.class)
    @PostMapping("/changeVirAddressDefaulted")
    public CommonResult<Boolean> changeVirAddressDefaulted(@RequestBody VirAddressVO virAddressVO) {
        long loginIdAsLong = StpPlayerUtil.getLoginIdAsLong();
        //切换默认 ，这里只能选择已审核通过的
        DreamUVirAddressDO byId = dreamUVirAddressService.getOneReviewedBy(virAddressVO.getId(), loginIdAsLong);
        if((ObjectUtil.isEmpty(byId))){
            throw ServiceExceptionUtil.exception(GlobalErrorCodeConstants.NOT_FOUND_DATA);
        }

        return CommonResult.success(dreamUVirAddressService.changeVirAddressDefaulted(virAddressVO.getId(), loginIdAsLong));
    }


    /**
     * 删除我的虚拟币地址
     *
     * @param id
     * @return
     */
    @TLogAspectExt(str = "删除我的虚拟币地址", moduleName = "Wallet", type = OperateTypeEnum.DELETE, convert = PlayerLogConvert.class)
    @PostMapping("/deleteVirAddressDefaulted")
    public CommonResult<Boolean> deleteVirAddress(@RequestBody VirAddressVO virAddressVO) {
        long loginIdAsLong = StpPlayerUtil.getLoginIdAsLong();
        DreamUVirAddressDO byId = dreamUVirAddressService.getOneNotDefaultedBy(virAddressVO.getId(), loginIdAsLong);
        if(ObjectUtil.isEmpty(byId)){
            throw ServiceExceptionUtil.exception(GlobalErrorCodeConstants.NOT_FOUND_DATA);
        }

        //删除我的虚拟币地址
        return CommonResult.success(dreamUVirAddressService.deleteVirAddress(virAddressVO.getId()));
    }

}
