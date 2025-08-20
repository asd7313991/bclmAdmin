package org.example.vo.hf.gamePlan;


import com.baomidou.mybatisplus.annotation.IdType;
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
public class HfGamePlanReqVo extends PageReqVO {


    @DateTimeFormat(pattern = DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @JsonFormat(pattern = DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 彩种id
     */
    private String lotteryid;

    /**
     * 开始期号
     */
    private String startisuuecode;

    /**
     * 完成期号
     */
    private Integer finishissue;

    /**
     * 总共期号
     */
    private Integer totalissue;

    /**
     * 停止模式
     */
    private Integer stopmode;

    /**
     * 追号类型
     */
    private Integer plantype;

    /**
     * 追号编码
     */
    private String plancode;

    /**
     * 销售总金额
     */
    private Integer[] totamount;

    /**
     * 实际销售金额
     */
    private Integer[] soldamount;

    /**
     * 追号状态
     */
    private Integer status;

    /**
     * 盈利金额
     */
    private Integer[] winamount;

    /**
     *
     */
    private Integer userid;

    /**
     * 起始倍数
     */
    private Integer startmutiple;

    /**
     * 间隔期数
     */
    private Integer issuerate;

    /**
     * 利润率
     */
    private Integer winrate;

    /**
     *
     */
    private Integer startamount;

    /**
     *
     */
    private String numberrecord;


    /**
     * 更新时间
     */
    private Date updatetime;
}
