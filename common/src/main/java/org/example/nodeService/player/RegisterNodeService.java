package org.example.nodeService.player;

import cn.hutool.json.JSONUtil;
import com.yomahub.liteflow.core.NodeComponent;
import lombok.extern.slf4j.Slf4j;
import org.example.po.dream.dream.DreamUFinanceAccDO;
import org.example.po.dream.dream.DreamUFinanceMainDO;
import org.example.po.dream.dream.DreamUUserDO;
import org.example.service.dream.DreamUFinanceAccService;
import org.example.service.dream.DreamUFinanceMainService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


@Slf4j
@Component("registerNode")
public class RegisterNodeService extends NodeComponent {

    @Resource
    private DreamUFinanceAccService dreamUFinanceAccService;
    @Resource
    private DreamUFinanceMainService dreamUFinanceMainService;

    /**
     *
     * @throws Exception
     */
    @Override
    public void process() throws Exception {
        DreamUUserDO requestData = this.getRequestData();
        log.info("开始执行register的流程:{}", JSONUtil.toJsonStr(requestData));
        // 初始化  main acc表
        dreamUFinanceAccService.save(new DreamUFinanceAccDO(requestData.getId().intValue()));
        dreamUFinanceMainService.save(new DreamUFinanceMainDO(requestData.getId()));
        log.info("结束执行Login的流程:{}，更新数据完成", JSONUtil.toJsonStr(requestData));
    }
}
