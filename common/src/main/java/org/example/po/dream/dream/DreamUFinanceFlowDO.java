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
 * @TableName dream_u_finance_flow
 */
@TableName(value = "dream_u_finance_flow")
@Data
public class DreamUFinanceFlowDO implements Serializable {
    /**
     *
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

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
    private BigDecimal money;

    /**
     * 本次流水红包发生金额
     */
    private Double redbag;

    /**
     * 本次流水后现金剩余
     */
    private BigDecimal moneyLeft;

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

    /**
     *
     */
    private Date createTime;


    private String auditRemark;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        DreamUFinanceFlowDO other = (DreamUFinanceFlowDO) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
                && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
                && (this.getTypeDetail() == null ? other.getTypeDetail() == null : this.getTypeDetail().equals(other.getTypeDetail()))
                && (this.getPlatformId() == null ? other.getPlatformId() == null : this.getPlatformId().equals(other.getPlatformId()))
                && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
                && (this.getMoney() == null ? other.getMoney() == null : this.getMoney().equals(other.getMoney()))
                && (this.getRedbag() == null ? other.getRedbag() == null : this.getRedbag().equals(other.getRedbag()))
                && (this.getMoneyLeft() == null ? other.getMoneyLeft() == null : this.getMoneyLeft().equals(other.getMoneyLeft()))
                && (this.getRedbagLeft() == null ? other.getRedbagLeft() == null : this.getRedbagLeft().equals(other.getRedbagLeft()))
                && (this.getCOrderId() == null ? other.getCOrderId() == null : this.getCOrderId().equals(other.getCOrderId()))
                && (this.getIoOrderId() == null ? other.getIoOrderId() == null : this.getIoOrderId().equals(other.getIoOrderId()))
                && (this.getCouponsId() == null ? other.getCouponsId() == null : this.getCouponsId().equals(other.getCouponsId()))
                && (this.getCouponsValue() == null ? other.getCouponsValue() == null : this.getCouponsValue().equals(other.getCouponsValue()))
                && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getTypeDetail() == null) ? 0 : getTypeDetail().hashCode());
        result = prime * result + ((getPlatformId() == null) ? 0 : getPlatformId().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getMoney() == null) ? 0 : getMoney().hashCode());
        result = prime * result + ((getRedbag() == null) ? 0 : getRedbag().hashCode());
        result = prime * result + ((getMoneyLeft() == null) ? 0 : getMoneyLeft().hashCode());
        result = prime * result + ((getRedbagLeft() == null) ? 0 : getRedbagLeft().hashCode());
        result = prime * result + ((getCOrderId() == null) ? 0 : getCOrderId().hashCode());
        result = prime * result + ((getIoOrderId() == null) ? 0 : getIoOrderId().hashCode());
        result = prime * result + ((getCouponsId() == null) ? 0 : getCouponsId().hashCode());
        result = prime * result + ((getCouponsValue() == null) ? 0 : getCouponsValue().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", type=").append(type);
        sb.append(", typeDetail=").append(typeDetail);
        sb.append(", platformId=").append(platformId);
        sb.append(", remark=").append(remark);
        sb.append(", money=").append(money);
        sb.append(", redbag=").append(redbag);
        sb.append(", moneyLeft=").append(moneyLeft);
        sb.append(", redbagLeft=").append(redbagLeft);
        sb.append(", cOrderId=").append(cOrderId);
        sb.append(", ioOrderId=").append(ioOrderId);
        sb.append(", couponsId=").append(couponsId);
        sb.append(", couponsValue=").append(couponsValue);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
