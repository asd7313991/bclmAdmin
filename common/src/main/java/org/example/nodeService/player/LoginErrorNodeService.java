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
@Component("loginErrorNode")
public class LoginErrorNodeService extends NodeComponent {

    @Resource
    private DreamUUserService dreamUUserService;

    @Override
    public void process() throws Exception {
        DreamUUserDO requestData = (DreamUUserDO) this.getRequestData();
        log.warn("登陆错误，开始进行规则处理:{}", JSONUtil.toJsonStr(requestData));
        requestData.setLoginErrorNum(requestData.getLoginErrorNum() + 1);
        requestData.setLastLoginTime(new Date());
        dreamUUserService.updateById(requestData);

    }
}
