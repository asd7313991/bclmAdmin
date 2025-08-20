package org.example.nodeService.finance;

import com.yomahub.liteflow.core.NodeSwitchComponent;
import lombok.extern.slf4j.Slf4j;
import org.example.po.dream.dream.DreamUFinanceIoDO;
import org.springframework.stereotype.Component;


@Slf4j
@Component("financeAcc")
public class FinanceAccSwitchComponent extends NodeSwitchComponent {
    @Override
    public String processSwitch() throws Exception {
        DreamUFinanceIoDO requestData = this.getRequestData();
        log.info("资金操作完成开始后续处理，此次为条件判断");
        if (requestData.getStatus() != 2) {
            return null;
        }

        switch (requestData.getType()) {
            case 10:
                log.info("资金充值");
                return "depositNode";
            case 20:
                log.info("资金提现");
                return "withdrawNode";

        }

        return null;
    }
}
