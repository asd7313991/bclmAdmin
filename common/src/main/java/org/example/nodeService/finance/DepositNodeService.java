package org.example.nodeService.finance;

import com.yomahub.liteflow.core.NodeComponent;
import lombok.extern.slf4j.Slf4j;
import org.example.service.dream.DreamUFinanceAccService;
import org.example.service.dream.DreamUUserService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


@Slf4j
@Component("depositNode")
public class DepositNodeService extends NodeComponent {

    @Resource
    private DreamUUserService dreamUUserService;
    @Resource
    private DreamUFinanceAccService dreamUFinanceAccService;

    /**
     * @throws Exception
     */
    @Override
    public void process() throws Exception {
        log.info("开始执行DepositNodeService");


    }
}
