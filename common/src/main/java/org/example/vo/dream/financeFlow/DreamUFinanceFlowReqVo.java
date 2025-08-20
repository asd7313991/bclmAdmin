package org.example.vo.dream.financeFlow;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.util.date.DateUtils;
import org.example.vo.base.PageReqVO;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;


@EqualsAndHashCode(callSuper = true)
@Data
public class DreamUFinanceFlowReqVo extends PageReqVO {
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 流水类型，
     */
    private Integer type;

    /**
     * 流水具体类型
     */
    private Integer typeDetail;

    /**
     *
     */
    private Integer platformId;

    /**
     * 流水描述
     */
    private String remark;

    /**
     * 本次流水现金发生金额
     */
    private Double money;

    /**
     * 本次流水红包发生金额
     */
    private Double redbag;

    /**
     * 本次流水后现金剩余
     */
    private Double moneyLeft;

    /**
     * 本次流水后红包剩余
     */
    private Double redbagLeft;

    /**
     * 内部消费订单id
     */
    private String cOrderId;

    /**
     * 内部充提订单id
     */
    private Long ioOrderId;

    /**
     * 优惠券id
     */
    private Integer couponsId;

    /**
     * 优惠券面额
     */
    private Double couponsValue;


    @DateTimeFormat(pattern = DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @JsonFormat(pattern = DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;
    @DateTimeFormat(pattern = DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @JsonFormat(pattern = DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] updateTime;


}
