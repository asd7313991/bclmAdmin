package org.example.po.dream.dream;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @TableName dream_u_finance_io
 */
@TableName(value = "dream_u_finance_io")
@Data
public class
DreamUFinanceIoDO implements Serializable {
    /**
     *
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @TableId(type = IdType.INPUT)
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 20-29为支付类型
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
     * 0：待审核 1：支付中 -1 审核失败 2支付成功 -2支付失败
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
     *
     */
    private Date createTime;

    /**
     *
     */
    private Date updateTime;

    /**
     * 优惠类型
     */
    private Integer promotionType;

    /**
     * 优惠抵扣金额
     */
    private Double deductionMoney;


    /**
     * 支付配置ID
     */
    private Long payTypeId;
    /**
     * 支付规则ID
     */
    private Long ruleId;
    /**
     * 支付名称
     */
    private String label;

    /**
     * 实际到账金额
     */
    private Double actAmount;

    /**
     * 来源的业务模块
     */
    private String src;

    private String auditRemark;

}
