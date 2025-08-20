package org.example.nodeService.finance;


import com.yomahub.liteflow.core.NodeComponent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Slf4j
@Component("withdrawNode")
public class WithdrawNodeService extends NodeComponent {


    @Override
    public void process() throws Exception {

        log.info("开始执行withdraw节点");

    }


}
