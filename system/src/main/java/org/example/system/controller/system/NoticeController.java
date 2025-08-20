package org.example.system.controller.system;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.config.log.TLogAspectExt;
import org.example.operatelog.core.enums.OperateTypeEnum;
import org.example.pojo.CommonResult;
import org.example.pojo.PageResult;
import org.example.service.notice.NoticeService;
import org.example.system.config.log.SystemTLogConvert;
import org.example.system.config.satoken.SystemCheckPermission;
import org.example.system.convert.notice.NoticeConvert;
import org.example.vo.system.notice.NoticeCreateReqVO;
import org.example.vo.system.notice.NoticePageReqVO;
import org.example.vo.system.notice.NoticeRespVO;
import org.example.vo.system.notice.NoticeUpdateReqVO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

import static org.example.pojo.CommonResult.success;

/**
 * The type Notice controller.
 */
@Tag(name = "管理后台 - 通知公告")
@RestController
@RequestMapping("/system/notice")
@Validated
public class NoticeController {

    @Resource
    private NoticeService noticeService;

    /**
     * Create notice common result.
     *
     * @param reqVO the req vo
     * @return the common result
     */
    @PostMapping("/create")
    @SystemCheckPermission(value = "system:notice:create")
    @TLogAspectExt(str = "创建通知公告", moduleName = "notice", type = OperateTypeEnum.CREATE, convert = SystemTLogConvert.class)
    public CommonResult<Long> createNotice(@Valid @RequestBody NoticeCreateReqVO reqVO) {
        Long noticeId = noticeService.createNotice(reqVO);
        return success(noticeId);
    }

    /**
     * Update notice common result.
     *
     * @param reqVO the req vo
     * @return the common result
     */
    @PutMapping("/update")
    @SystemCheckPermission(value = "system:notice:update")
    @TLogAspectExt(str = "修改通知公告", moduleName = "notice", type = OperateTypeEnum.UPDATE, convert = SystemTLogConvert.class)
    public CommonResult<Boolean> updateNotice(@Valid @RequestBody NoticeUpdateReqVO reqVO) {
        noticeService.updateNotice(reqVO);
        return success(true);
    }

    /**
     * Delete notice common result.
     *
     * @param id the id
     * @return the common result
     */
    @PostMapping("/delete")
    @SystemCheckPermission(value = "system:notice:delete")
    @TLogAspectExt(str = "删除通知公告", moduleName = "notice", type = OperateTypeEnum.DELETE, convert = SystemTLogConvert.class)
    public CommonResult<Boolean> deleteNotice(@RequestParam("id") Long id) {
        noticeService.deleteNotice(id);
        return success(true);
    }

    /**
     * Gets notice page.
     *
     * @param reqVO the req vo
     * @return the notice page
     */
    @GetMapping("/page")
    @SystemCheckPermission(value = "system:notice:query")
    @TLogAspectExt(str = "获取通知公告列表", moduleName = "notice", type = OperateTypeEnum.GET, convert = SystemTLogConvert.class)
    public CommonResult<PageResult<NoticeRespVO>> getNoticePage(@Validated NoticePageReqVO reqVO) {
        return success(NoticeConvert.INSTANCE.convertPage(noticeService.getNoticePage(reqVO)));
    }

    /**
     * Gets notice.
     *
     * @param id the id
     * @return the notice
     */
    @GetMapping("/get")
    @SystemCheckPermission(value = "system:notice:query")
    @TLogAspectExt(str = "获得通知公告", moduleName = "notice", type = OperateTypeEnum.GET, convert = SystemTLogConvert.class)
    public CommonResult<NoticeRespVO> getNotice(@RequestParam("id") Long id) {
        return success(NoticeConvert.INSTANCE.convert(noticeService.getNotice(id)));
    }

}
