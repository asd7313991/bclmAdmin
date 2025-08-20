package org.example.vo.dream.financeDeposit;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.po.dream.dream.DreamUFinanceDepositDO;


@EqualsAndHashCode(callSuper = true)
@Data
public class DreamUFinanceDepositVO extends DreamUFinanceDepositDO {

    private String username;

//    /**
//     * 最后登录时间
//     */
//    @DateTimeFormat(pattern = DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
//    @JsonFormat(pattern = DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
//    private LocalDateTime createTime;
}
