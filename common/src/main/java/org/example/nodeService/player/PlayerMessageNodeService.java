package org.example.nodeService.player;

import cn.hutool.json.JSONUtil;
import com.yomahub.liteflow.core.NodeComponent;
import lombok.extern.slf4j.Slf4j;
import org.example.pojo.MessageDTO;
import org.example.service.notify.NotifySendService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Slf4j
@Component
public class PlayerMessageNodeService extends NodeComponent {

    @Resource
    private NotifySendService notifySendService;

    @Override
    public void process() throws Exception {
        MessageDTO messageDTO = this.getRequestData();
        log.info("开始执行PlayerMessage的流程:{}", JSONUtil.toJsonStr(messageDTO));
        Long l = notifySendService.sendSingleNotifyToMember(messageDTO.getUserId(), messageDTO.getTemplateCode(), messageDTO.getExtraParameters());
        log.info("结束执行PlayerMessage的流程:{}，发送消息ID：{}", JSONUtil.toJsonStr(messageDTO),l);
    }
}
