package org.example.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class WithdrawOrderReq {


    /**
     * 充值的支付方式 对接的时候会配好
     */
    private Long withdrawId;

    /**
     * 提款地址
     */
    private Long addressId;

    /**
     * 充值或者提款的金额
     */
    private BigDecimal amount;


    /**
     * 充值或者提款的币种
     */
    private String currency;

    /**
     * 充值或者提款的协议
     */
    private String protocol;

    /**
     * 充值或者提款的账户
     */
    private String address;


    /**
     * 对应的规则ID
     */
    private String ruleId;




    private String password;
}
