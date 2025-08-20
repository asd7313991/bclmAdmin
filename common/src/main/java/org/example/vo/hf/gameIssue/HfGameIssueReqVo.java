package org.example.vo.hf.gameIssue;


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
public class HfGameIssueReqVo extends PageReqVO {


    @DateTimeFormat(pattern = DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @JsonFormat(pattern = DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] saleTime;
    @DateTimeFormat(pattern = DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @JsonFormat(pattern = DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;
    @DateTimeFormat(pattern = DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @JsonFormat(pattern = DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] opendrawtime;


    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 奖期
     */
    private String issuecode;

    /**
     * 彩种id
     */
    private String lotteryid;


    /**
     * 位记录
     */
    private String numberrecord;

    /**
     * 求和结果
     */
    private String sumrecord;

    /**
     * 中奖方式
     */
    private String winrecord;

    /**
     * 提前结束秒
     */
    private Integer preendissue;

    /**
     * 上期
     */
    private String lastissuecode;

    /**
     *
     */
    private Integer issuestatus;

    /**
     * 更新时间
     */
    private Date updatetime;
}
