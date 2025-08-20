package org.example.po.dream.dream;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName dream_deposit_type_rule
 */
@TableName(value ="dream_deposit_type_rule")
@Data
public class DreamDepositTypeRuleDO implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 备注
     */
    private String remark;

    /**
     * 
     */
    private Date createTime;

    /**
     * 
     */
    private Date updateTime;

    /**
     * 
     */
    private Long version;

    /**
     * 
     */
    private Integer deleted;

    /**
     * 
     */
    private Integer status;

    /**
     * 支付ID
     *
     */
    private Long depositTypeId;

    /**
     * 充值规则名称
     */
    private String name;

    /**
     * 最小金额
     */
    private BigDecimal minAmount;

    /**
     * 最大金额
     */
    private BigDecimal maxAmount;

    /**
     * 充值默认金额  如果为0，则显示，让用户自行输入
     */
    private String amountDefault;

    /**
     * 手续费 %
     */
    private BigDecimal charges;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}