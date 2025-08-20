package org.example.nodeService.player;

import cn.hutool.json.JSONUtil;
import com.yomahub.liteflow.core.NodeComponent;
import lombok.extern.slf4j.Slf4j;
import org.example.po.dream.dream.DreamUUserDO;
import org.example.service.dream.DreamUUserService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;


@Slf4j
@Component("loginNode")
public class LoginNodeService extends NodeComponent {

    @Resource
    private DreamUUserService dreamUUserService;

    /**
     * @throws Exception
     */
    @Override
    public void process() throws Exception {
        DreamUUserDO requestData = (DreamUUserDO) this.getRequestData();

        log.info("开始执行Login的流程:{}", JSONUtil.toJsonStr(requestData));
        requestData.setLastLoginTime(new Date());
        requestData.setLoginNum(requestData.getLoginNum() + 1);
        if (requestData.getLastSensitizeTime() == null) {
            requestData.setLastSensitizeTime(new Date());
        }
        requestData.setLoginErrorNum(0);
//        Area area = IPUtils.
//        if (area!= null){
//            requestData.setLand(area.getName());
//        }
        boolean b = dreamUUserService.updateById(requestData);
        log.info("结束执行Login的流程:{}，更新数据：{}", JSONUtil.toJsonStr(requestData), b);

    }
}
