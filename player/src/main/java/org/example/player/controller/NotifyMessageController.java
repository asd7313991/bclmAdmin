package org.example.player.controller;


import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import org.example.config.log.TLogAspectExt;
import org.example.enums.UserTypeEnum;
import org.example.operatelog.core.enums.OperateTypeEnum;
import org.example.player.config.log.PlayerLogConvert;
import org.example.player.config.sa.StpPlayerUtil;
import org.example.player.vo.NoticeVO;
import org.example.po.system.NotifyMessageDO;
import org.example.pojo.CommonResult;
import org.example.pojo.PageResult;
import org.example.service.notify.NotifyMessageService;
import org.example.vo.system.dict.vo.message.NotifyMessageMyPageReqVO;
import org.example.vo.system.dict.vo.message.NotifyMessageRespVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/player/notifyMessage")
public class NotifyMessageController {

    @Resource
    private NotifyMessageService notifyMessageService;
    @Resource
    private MapperFacade mapperFacade;

    /**
     * 获取我的消息
     */
    @TLogAspectExt(str = "获取我的消息", moduleName = "Notify", type = OperateTypeEnum.GET, convert = PlayerLogConvert.class)
    @PostMapping("/getNotifyMessage")
    public CommonResult<PageResult<NotifyMessageRespVO>> getNotifyMessage(@RequestBody NotifyMessageMyPageReqVO notifyMessageMyPageReqVO) {
        long loginIdAsLong = StpPlayerUtil.getLoginIdAsLong();
        PageResult<NotifyMessageDO> myMyNotifyMessagePage = notifyMessageService.getMyMyNotifyMessagePage(notifyMessageMyPageReqVO, loginIdAsLong, UserTypeEnum.MEMBER.getValue());

        PageResult<NotifyMessageRespVO> empty = PageResult.empty(myMyNotifyMessagePage.getTotal());
        empty.setList(mapperFacade.mapAsList(myMyNotifyMessagePage.getList(),NotifyMessageRespVO.class));
        return  CommonResult.success(empty);
    }

    /**
     * 获取我的未读消息数量
     * @return
     */
    @TLogAspectExt(str = "获取我的未读消息数量", moduleName = "Notify", type = OperateTypeEnum.GET, convert = PlayerLogConvert.class)
    @PostMapping("/getNotifyMessageUnreadCount")
    public CommonResult<Long > getNotifyMessageUnreadCount(){
        long loginIdAsLong = StpPlayerUtil.getLoginIdAsLong();
        Long unreadNotifyMessageCount = notifyMessageService.getUnreadNotifyMessageCount(loginIdAsLong, UserTypeEnum.MEMBER.getValue());
        return  CommonResult.success(unreadNotifyMessageCount);
    }


    /**
     * 标记站内信为已读
     * @param ids 消息ID
     * @return
     */
    @TLogAspectExt(str = "标记站内信为已读", moduleName = "Notify", type = OperateTypeEnum.UPDATE, convert = PlayerLogConvert.class)
    @PostMapping("/updateNotifyMessageRead")
    public CommonResult updateNotifyMessageRead(@RequestBody NoticeVO noticeVO) {
        long loginIdAsLong = StpPlayerUtil.getLoginIdAsLong();
        int i = notifyMessageService.updateNotifyMessageRead(noticeVO.getIds(), loginIdAsLong, UserTypeEnum.MEMBER.getValue());
        return  CommonResult.success(i);
    }


    /**
     * 标记所有站内信为已读
     * @return
     */
    @TLogAspectExt(str = "标记所有站内信为已读", moduleName = "Notify", type = OperateTypeEnum.UPDATE, convert = PlayerLogConvert.class)
    @PostMapping("/updateAllNotifyMessageRead")
    public CommonResult updateAllNotifyMessageRead(){
        long loginIdAsLong = StpPlayerUtil.getLoginIdAsLong();
        int i = notifyMessageService.updateAllNotifyMessageRead(loginIdAsLong, UserTypeEnum.MEMBER.getValue());

        return  CommonResult.success(i);
    }
}
