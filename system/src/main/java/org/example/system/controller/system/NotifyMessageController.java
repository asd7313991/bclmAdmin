package org.example.system.controller.system;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.config.log.TLogAspectExt;
import org.example.config.satoken.StpSystemUtil;
import org.example.enums.UserTypeEnum;
import org.example.operatelog.core.enums.OperateTypeEnum;
import org.example.po.system.NotifyMessageDO;
import org.example.pojo.CommonResult;
import org.example.pojo.PageResult;
import org.example.service.notify.NotifyMessageService;
import org.example.system.config.log.SystemTLogConvert;
import org.example.system.config.satoken.SystemCheckPermission;
import org.example.system.convert.notify.NotifyMessageConvert;
import org.example.vo.system.dict.vo.message.NotifyMessageMyPageReqVO;
import org.example.vo.system.dict.vo.message.NotifyMessagePageReqVO;
import org.example.vo.system.dict.vo.message.NotifyMessageRespVO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

import static org.example.pojo.CommonResult.success;

/**
 * The type Notify message controller.
 */
@Tag(name = "管理后台 - 我的站内信")
@RestController
@RequestMapping("/system/notify-message")
@Validated
public class NotifyMessageController {

    @Resource
    private NotifyMessageService notifyMessageService;

    // ========== 管理所有的站内信 ==========

    /**
     * Gets notify message.
     *
     * @param id the id
     * @return the notify message
     */
    @GetMapping("/get")
    @SystemCheckPermission(value = "system:notify-message:query")
    @TLogAspectExt(str = "获得站内信", moduleName = "Dict", type = OperateTypeEnum.GET, convert = SystemTLogConvert.class)
    public CommonResult<NotifyMessageRespVO> getNotifyMessage(@RequestParam("id") Long id) {
        NotifyMessageDO notifyMessage = notifyMessageService.getNotifyMessage(id);
        return success(NotifyMessageConvert.INSTANCE.convert(notifyMessage));
    }

    /**
     * Gets notify message page.
     *
     * @param pageVO the page vo
     * @return the notify message page
     */
    @GetMapping("/page")
    @SystemCheckPermission(value = "system:notify-message:query")
    @TLogAspectExt(str = "获得站内信分页", moduleName = "Dict", type = OperateTypeEnum.GET, convert = SystemTLogConvert.class)
    public CommonResult<PageResult<NotifyMessageRespVO>> getNotifyMessagePage(@Valid NotifyMessagePageReqVO pageVO) {
        PageResult<NotifyMessageDO> pageResult = notifyMessageService.getNotifyMessagePage(pageVO);
        return success(NotifyMessageConvert.INSTANCE.convertPage(pageResult));
    }

    // ========== 查看自己的站内信 ==========

    /**
     * Gets my my notify message page.
     *
     * @param pageVO the page vo
     * @return the my my notify message page
     */
    @GetMapping("/my-page")
    @TLogAspectExt(str = "获得我的站内信分页", moduleName = "Dict", type = OperateTypeEnum.GET, convert = SystemTLogConvert.class)
    public CommonResult<PageResult<NotifyMessageRespVO>> getMyMyNotifyMessagePage(@Valid NotifyMessageMyPageReqVO pageVO) {
        PageResult<NotifyMessageDO> pageResult = notifyMessageService.getMyMyNotifyMessagePage(pageVO,
                StpSystemUtil.getLoginIdAsLong(), UserTypeEnum.ADMIN.getValue());
        return success(NotifyMessageConvert.INSTANCE.convertPage(pageResult));
    }

    /**
     * Update notify message read common result.
     *
     * @param ids the ids
     * @return the common result
     */
    @PutMapping("/update-read")
    @TLogAspectExt(str = "标记站内信为已读", moduleName = "Dict", type = OperateTypeEnum.UPDATE, convert = SystemTLogConvert.class)
    public CommonResult<Boolean> updateNotifyMessageRead(@RequestParam("ids") List<Long> ids) {
        notifyMessageService.updateNotifyMessageRead(ids, StpSystemUtil.getLoginIdAsLong(), UserTypeEnum.ADMIN.getValue());
        return success(Boolean.TRUE);
    }

    /**
     * Update all notify message read common result.
     *
     * @return the common result
     */
    @PutMapping("/update-all-read")
    @TLogAspectExt(str = "标记所有站内信为已读", moduleName = "Dict", type = OperateTypeEnum.UPDATE, convert = SystemTLogConvert.class)
    public CommonResult<Boolean> updateAllNotifyMessageRead() {
        notifyMessageService.updateAllNotifyMessageRead(StpSystemUtil.getLoginIdAsLong(), UserTypeEnum.ADMIN.getValue());
        return success(Boolean.TRUE);
    }

    /**
     * Gets unread notify message list.
     *
     * @param size the size
     * @return the unread notify message list
     */
    @GetMapping("/get-unread-list")
    @TLogAspectExt(str = "获取当前用户的最新站内信列表", moduleName = "Dict", type = OperateTypeEnum.GET, convert = SystemTLogConvert.class)
    public CommonResult<List<NotifyMessageRespVO>> getUnreadNotifyMessageList(
            @RequestParam(name = "size", defaultValue = "10") Integer size) {
        List<NotifyMessageDO> list = notifyMessageService.getUnreadNotifyMessageList(
                StpSystemUtil.getLoginIdAsLong(), UserTypeEnum.ADMIN.getValue(), size);
        return success(NotifyMessageConvert.INSTANCE.convertList(list));
    }

    /**
     * Gets unread notify message count.
     *
     * @return the unread notify message count
     */
    @GetMapping("/get-unread-count")
    @TLogAspectExt(str = "获得当前用户的未读站内信数量", moduleName = "Dict", type = OperateTypeEnum.GET, convert = SystemTLogConvert.class)
    public CommonResult<Long> getUnreadNotifyMessageCount() {
        return success(notifyMessageService.getUnreadNotifyMessageCount(StpSystemUtil.getLoginIdAsLong(), UserTypeEnum.ADMIN.getValue()));
    }

}
