package org.example.system.controller.system;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.config.log.TLogAspectExt;
import org.example.operatelog.core.enums.OperateTypeEnum;
import org.example.po.system.NotifyTemplateDO;
import org.example.pojo.CommonResult;
import org.example.pojo.PageResult;
import org.example.service.notify.NotifySendService;
import org.example.service.notify.NotifyTemplateService;
import org.example.system.config.log.SystemTLogConvert;
import org.example.system.config.satoken.SystemCheckPermission;
import org.example.system.convert.notify.NotifyTemplateConvert;
import org.example.vo.system.dict.vo.template.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

import static org.example.pojo.CommonResult.success;


/**
 * The type Notify template controller.
 */
@Tag(name = "管理后台 - 站内信模版")
@RestController
@RequestMapping("/system/notify-template")
@Validated
public class NotifyTemplateController {

    @Resource
    private NotifyTemplateService notifyTemplateService;

    @Resource
    private NotifySendService notifySendService;

    /**
     * Create notify template common result.
     *
     * @param createReqVO the create req vo
     * @return the common result
     */
    @PostMapping("/create")
    @SystemCheckPermission(value = "system:notify-template:create")
    @TLogAspectExt(str = "获取当前用户的最新站内信列表", moduleName = "notify-template", type = OperateTypeEnum.CREATE, convert = SystemTLogConvert.class)
    public CommonResult<Long> createNotifyTemplate(@Valid @RequestBody NotifyTemplateCreateReqVO createReqVO) {
        return success(notifyTemplateService.createNotifyTemplate(createReqVO));
    }

    /**
     * Update notify template common result.
     *
     * @param updateReqVO the update req vo
     * @return the common result
     */
    @PutMapping("/update")
    @SystemCheckPermission(value = "system:notify-template:update")
    @TLogAspectExt(str = "更新站内信模版", moduleName = "notify-template", type = OperateTypeEnum.UPDATE, convert = SystemTLogConvert.class)
    public CommonResult<Boolean> updateNotifyTemplate(@Valid @RequestBody NotifyTemplateUpdateReqVO updateReqVO) {
        notifyTemplateService.updateNotifyTemplate(updateReqVO);
        return success(true);
    }

    /**
     * Delete notify template common result.
     *
     * @param id the id
     * @return the common result
     */
    @PostMapping("/delete")
    @SystemCheckPermission(value = "system:notify-template:delete")
    @TLogAspectExt(str = "删除站内信模版", moduleName = "notify-template", type = OperateTypeEnum.DELETE, convert = SystemTLogConvert.class)
    public CommonResult<Boolean> deleteNotifyTemplate(@RequestParam("id") Long id) {
        notifyTemplateService.deleteNotifyTemplate(id);
        return success(true);
    }

    /**
     * Gets notify template.
     *
     * @param id the id
     * @return the notify template
     */
    @GetMapping("/get")
    @SystemCheckPermission(value = "system:notify-template:query")
    @TLogAspectExt(str = "获得站内信模版", moduleName = "notify-template", type = OperateTypeEnum.GET, convert = SystemTLogConvert.class)
    public CommonResult<NotifyTemplateRespVO> getNotifyTemplate(@RequestParam("id") Long id) {
        NotifyTemplateDO notifyTemplate = notifyTemplateService.getNotifyTemplate(id);
        return success(NotifyTemplateConvert.INSTANCE.convert(notifyTemplate));
    }

    /**
     * Gets notify template page.
     *
     * @param pageVO the page vo
     * @return the notify template page
     */
    @GetMapping("/page")
    @SystemCheckPermission(value = "system:notify-template:query")
    @TLogAspectExt(str = "获得站内信模版分页", moduleName = "notify-template", type = OperateTypeEnum.GET, convert = SystemTLogConvert.class)
    public CommonResult<PageResult<NotifyTemplateRespVO>> getNotifyTemplatePage(@Valid NotifyTemplatePageReqVO pageVO) {
        PageResult<NotifyTemplateDO> pageResult = notifyTemplateService.getNotifyTemplatePage(pageVO);
        return success(NotifyTemplateConvert.INSTANCE.convertPage(pageResult));
    }

    /**
     * Send notify common result.
     *
     * @param sendReqVO the send req vo
     * @return the common result
     */
    @PostMapping("/send-notify")
    @SystemCheckPermission(value = "system:notify-template:send-notify")
    @TLogAspectExt(str = "发送站内信", moduleName = "notify-template", type = OperateTypeEnum.GET, convert = SystemTLogConvert.class)
    public CommonResult<Long> sendNotify(@Valid @RequestBody NotifyTemplateSendReqVO sendReqVO) {
        return success(notifySendService.sendSingleNotifyToAdmin(sendReqVO.getUserId(),
                sendReqVO.getTemplateCode(), sendReqVO.getTemplateParams()));
    }

}
