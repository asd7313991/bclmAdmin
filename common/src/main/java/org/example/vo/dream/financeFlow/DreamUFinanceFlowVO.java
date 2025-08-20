package org.example.vo.dream.financeFlow;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.po.dream.dream.DreamUFinanceFlowDO;


@EqualsAndHashCode(callSuper = true)
@Data
public class DreamUFinanceFlowVO extends DreamUFinanceFlowDO {
    private String username;
}
