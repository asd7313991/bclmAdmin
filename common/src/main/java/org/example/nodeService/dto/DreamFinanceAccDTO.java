package org.example.nodeService.dto;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class DreamFinanceAccDTO {


    /**
     * 玩家ID
     */
    private Long userId;


    /**
     * 提现或充值成功金额
     */
    private BigDecimal amount;

    /**
     * 类型
     */
    private Integer type;
}
