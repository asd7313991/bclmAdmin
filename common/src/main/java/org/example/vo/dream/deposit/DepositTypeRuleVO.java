package org.example.vo.dream.deposit;

import lombok.Data;

import java.math.BigDecimal;


@Data
public class DepositTypeRuleVO {


    private Long id;
    /**
     * 充值规则名称
     */
    private String name;

    /**
     * 最小金额
     */
    private BigDecimal minAmount;

    /**
     * 最大金额
     */
    private BigDecimal maxAmount;

    /**
     * 充值默认金额 100,200,300   如果为0，则显示，让用户自行输入
     */
    private String amountDefault;

    /**
     * 手续费 %
     */
    private BigDecimal charges;
}
