package org.example.system.controller.dream;


import cn.hutool.json.JSONUtil;
import com.baomidou.lock.annotation.Lock4j;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import org.example.config.log.TLogAspectExt;
import org.example.exception.enums.GlobalErrorCodeConstants;
import org.example.po.dream.dream.DreamUUserDO;
import org.example.pojo.CommonResult;
import org.example.pojo.PageResult;
import org.example.service.dream.DreamUFinanceMainService;
import org.example.service.dream.DreamUUserService;
import org.example.service.dream.WalletService;
import org.example.system.config.log.SystemTLogConvert;
import org.example.vo.dream.user.AddOrSubReq;
import org.example.vo.dream.user.DreamUUserInfoVO;
import org.example.vo.dream.user.DreamUUserPageReqVO;
import org.example.vo.dream.user.DreamUUserVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

import static org.example.exception.util.ServiceExceptionUtil.exception;
import static org.example.operatelog.core.enums.OperateTypeEnum.*;
import static org.example.pojo.CommonResult.success;

/**
 * The type Dream u user controller.
 */
@RestController
@RequestMapping("/dream/user")
@Slf4j
public class DreamUUserController {

    @Resource
    private DreamUUserService dreamUUserService;
    @Resource
    private MapperFacade mapperFacade;
    @Resource
    private DreamUFinanceMainService dreamUFinanceMainService;
    @Resource
    private WalletService walletService;


    /**
     * 创建用户 现在默认创建为VIP用户
     *
     * @param updateReqVO the update req vo
     * @return common result
     */
    @TLogAspectExt(str = "创建用户", moduleName = "User", type = CREATE, convert = SystemTLogConvert.class)
    @PostMapping("/createUser")
    public CommonResult<Boolean> createVipUser(@Valid @RequestBody DreamUUserVO updateReqVO) {
        updateReqVO.setUserType(1);
        dreamUUserService.createVipUser(updateReqVO);
        return success(true);
    }

    /**
     * 更新用户
     *
     * @param updateReqVO the update req vo
     * @return common result
     */
    @Lock4j(keys = {"#updateReqVO.id"}, acquireTimeout = 0, expire = 3000, autoRelease = false)
    @TLogAspectExt(str = "更新用户", moduleName = "User", type = UPDATE, convert = SystemTLogConvert.class)
    @PostMapping("/updateUser")
    public CommonResult<Boolean> updateUser(@Valid @RequestBody DreamUUserVO updateReqVO) {
        dreamUUserService.updateUserInfoByAdmin(updateReqVO);
        return success(true);
    }


    /**
     * 更新密码
     *
     * @param updateReqVO the update req vo
     * @return common result
     */
    @Lock4j(keys = {"#updateReqVO.id"}, acquireTimeout = 0, expire = 3000, autoRelease = false)
    @TLogAspectExt(str = "更新密码", moduleName = "User", type = UPDATE, convert = SystemTLogConvert.class)
    @PostMapping("/changeUserPw")
    public CommonResult<Boolean> changeUserPw(@Valid @RequestBody DreamUUserVO updateReqVO) {
        if (updateReqVO.getType() == null) {
            log.error("修改密码类型不能为空");
            throw exception(GlobalErrorCodeConstants.BAD_REQUEST);
        }

        if (!updateReqVO.getPassword().equals(updateReqVO.getAgainPassword())) {
            log.info("两次密码不一致：{}", JSONUtil.toJsonStr(updateReqVO));
            throw exception(GlobalErrorCodeConstants.BAD_REQUEST);
        }

        switch (updateReqVO.getType()) {
            case 1:
                dreamUUserService.changePassword(updateReqVO);
                break;
            case 2:
                dreamUFinanceMainService.changePassword(updateReqVO);
                break;
            default:
                log.info("不支持的修改类型");
                throw exception(GlobalErrorCodeConstants.BAD_REQUEST);
        }
        return success(true);
    }


    /**
     * Add or sub op common result.
     *
     * @param addOrSubReq the add or sub req
     * @return common result
     */
    @Lock4j(name = "lock4j_finance", keys = {"#addOrSubReq.userId"}, acquireTimeout = 0, expire = 3000, autoRelease = false)
    @TLogAspectExt(str = "人工上下分", moduleName = "User", type = UPDATE, convert = SystemTLogConvert.class)
    @PostMapping("/addOrSubOp")
    public CommonResult<Boolean> addOrSubOp(@Valid @RequestBody AddOrSubReq addOrSubReq) {
        if (addOrSubReq.getType() == null) {
            log.error("操作类型不能为空");
            throw exception(GlobalErrorCodeConstants.BAD_REQUEST);
        }
        //操作加扣款
        return success(walletService.addOrSub(addOrSubReq));
    }


    /**
     * Gets banner.
     *
     * @param id the id
     * @return the banner
     */
    @TLogAspectExt(str = "查询单个用户信息", moduleName = "User", type = GET, convert = SystemTLogConvert.class)
    @GetMapping("/getUserInfo")
    public CommonResult<DreamUUserInfoVO> getBanner(@RequestParam("id") Long id) {
        DreamUUserInfoVO dreamUUserInfoVO = dreamUUserService.getInfoById(id);
        return success(dreamUUserInfoVO);
    }


    /**
     * Gets banner page.
     *
     * @param pageVO the page vo
     * @return the banner page
     */
    @TLogAspectExt(str = "查询用户信息-分页", moduleName = "User", type = GET, convert = SystemTLogConvert.class)
    @GetMapping("/page")
    public CommonResult<PageResult<DreamUUserVO>> getBannerPage(@Valid DreamUUserPageReqVO pageVO) {
        PageResult<DreamUUserDO> pageResult = dreamUUserService.pageByParams(pageVO);
        PageResult<DreamUUserVO> empty = PageResult.empty(pageResult.getTotal());
        empty.setList(mapperFacade.mapAsList(pageResult.getList(), DreamUUserVO.class));
        return success(empty);
    }
}
