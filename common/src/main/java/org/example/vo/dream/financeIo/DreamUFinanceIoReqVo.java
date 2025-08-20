package org.example.vo.dream.financeIo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.util.date.DateUtils;
import org.example.vo.base.PageReqVO;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Data
public class DreamUFinanceIoReqVo extends PageReqVO {
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    private String userName;

    private Integer userType;

    private String address;

    /**
     * 20-29为支付类型
     * 10：充值订单
     * 20：提现订单
     */
    private Integer type;

    /**
     * 支付具体类型
     */
    private Integer typeDetail;

    /**
     * 平台id
     */
    private Integer platformId;

    /**
     * 充提类型描述
     */
    private String remark;

    /**
     * 充提金额
     */
    private Double money;

    /**
     * 根据各平台计算得出本次手续费
     */
    private Double commission;

    /**
     * 系统存入或抹掉的红包数量
     */
    private Double redbag;

    /**
     * 0：刚刚发起 1：最新发起成功 -1表示请求旧数据 2支付成功 -2支付失败
     */
    private Integer status;

    /**
     * 支付对象id如借款id和账号id
     */
    private String selfOrderId;

    /**
     * 第三方平台通知过来的唯一标识
     */
    private String thirdOrderId;

    /**
     *
     */
    private String submitOrderId;


    /**
     * 优惠类型
     */
    private Integer promotionType;

    /**
     * 优惠抵扣金额
     */
    private Double deductionMoney;

    /**
     * 来源的业务模块
     */
    private String src;

    private BigDecimal actAmount;

    @DateTimeFormat(pattern = DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @JsonFormat(pattern = DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;
    @DateTimeFormat(pattern = DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @JsonFormat(pattern = DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] updateTime;


    private Integer days;


    /**
     * 审核上传凭证
     */
    private List<String> filePath;

    /**
     * 审核备注
     */
    private String auditRemark;



}
