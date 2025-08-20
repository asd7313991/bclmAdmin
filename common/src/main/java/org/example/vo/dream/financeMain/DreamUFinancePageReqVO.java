package org.example.vo.dream.financeMain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.util.date.DateUtils;
import org.example.vo.base.PageReqVO;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class DreamUFinancePageReqVO extends PageReqVO {


    /**
     * 用户名
     */
    @TableId
    private Integer userId;

    /**
     * 可用资金余额
     */
    private Double[] moneyUsable;

    /**
     * 冻结资金
     */
    private Double[] moneyFrozen;

    /**
     * 可提现资金余额
     */
    private Double[] moneyDrawUsable;

    /**
     * 当前用户保证金余额
     */
    private Double[] margin;


    @DateTimeFormat(pattern = DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @JsonFormat(pattern = DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    /**
     *
     */
    private Date updateTime;
}
