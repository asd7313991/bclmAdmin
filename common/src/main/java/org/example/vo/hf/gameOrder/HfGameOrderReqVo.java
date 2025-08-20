package org.example.vo.hf.gameOrder;


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
public class HfGameOrderReqVo extends PageReqVO {


    @DateTimeFormat(pattern = DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @JsonFormat(pattern = DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;
    @DateTimeFormat(pattern = DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @JsonFormat(pattern = DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] saletime;
    @DateTimeFormat(pattern = DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @JsonFormat(pattern = DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] calculatewintime;
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 名称
     */
    private String lotteryid;
    /**
     * 期号
     */
    private String issuecode;


    /**
     * 订单编码
     */
    private String ordercode;

    /**
     * 总金额
     */
    private Integer totalamount;

    /**
     * 状态
     */
    private Integer orderstatus;

    /**
     * 下注内容
     */
    private String numberrecord;

    /**
     * 追号id
     */
    private Integer gameplanid;

    /**
     * 追号详情id
     */
    private Integer gameplandetailid;

    /**
     * 用户id
     */
    private Integer userid;

    /**
     * 实际奖金额
     */
    private Double accwin;

    /**
     *
     */
    private Double prewinnum;
}
