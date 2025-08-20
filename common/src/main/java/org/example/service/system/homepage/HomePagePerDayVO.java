package org.example.service.system.homepage;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class HomePagePerDayVO {

    /**
     * 总收益
     */
    private BigDecimal totalEarnings = BigDecimal.ZERO;

    /**
     * 新增普通用户
     */
    private Long newRegularUsers = 0L;

    /**
     * 新增 VIP 用户
     */
    private Long newVIPUsers = 0L;

    /**
     * 充值金额
     */
    private BigDecimal rechargeAmount = BigDecimal.ZERO;

    /**
     * 提现金额
     */
    private BigDecimal withdrawalAmount = BigDecimal.ZERO;

    /**
     * 注单金额
     */
    private BigDecimal betAmount = BigDecimal.ZERO;

    /**
     * 注单人数
     */
    private Long betUsers = 0L;

    private LocalDate perDate;

}
