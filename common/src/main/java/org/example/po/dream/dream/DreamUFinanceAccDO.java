package org.example.po.dream.dream;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @TableName dream_u_finance_acc
 */
@TableName(value = "dream_u_finance_acc")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DreamUFinanceAccDO implements Serializable {


    public DreamUFinanceAccDO(Integer userId) {
        this.userId = userId;
    }

    /**
     * 用户id
     */
    @TableId
    private Integer userId;

    /**
     * 用户总充值金额
     */
    private Double depositMoney;

    /**
     *
     */
    private Double sysDepopsitMoney;

    /**
     * 用户总提现金额
     */
    private Double drawMoney;

    /**
     *
     */
    private Double sysDrawMoney;

    /**
     *
     */
    private Double payMoney;

    /**
     * 用户累计保证金
     */
    private Double marginMoney;

    /**
     * 累计现金手续费
     */
    private Double commissionMoney;

    /**
     * 用户总收益金额
     */
    private Double profitMoney;

    /**
     * 累计佣金转现金
     */
    private Double brokerageMoney;

    /**
     * 用户获得系统赠送的红包总额
     */
    private Double depositRedbag;

    /**
     * 用户消费的红包总额
     */
    private Double consumeRedbag;

    /**
     * 用户被系统抹掉的红包总额
     */
    private Double eraseRadbag;

    /**
     * 兑换积分花费的资金
     */
    private Double exchangeMoney;

    /**
     *
     */
    private Date createTime;

    /**
     *
     */
    private Date updateTime;

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
        DreamUFinanceAccDO other = (DreamUFinanceAccDO) that;
        return (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
                && (this.getDepositMoney() == null ? other.getDepositMoney() == null : this.getDepositMoney().equals(other.getDepositMoney()))
                && (this.getSysDepopsitMoney() == null ? other.getSysDepopsitMoney() == null : this.getSysDepopsitMoney().equals(other.getSysDepopsitMoney()))
                && (this.getDrawMoney() == null ? other.getDrawMoney() == null : this.getDrawMoney().equals(other.getDrawMoney()))
                && (this.getSysDrawMoney() == null ? other.getSysDrawMoney() == null : this.getSysDrawMoney().equals(other.getSysDrawMoney()))
                && (this.getPayMoney() == null ? other.getPayMoney() == null : this.getPayMoney().equals(other.getPayMoney()))
                && (this.getMarginMoney() == null ? other.getMarginMoney() == null : this.getMarginMoney().equals(other.getMarginMoney()))
                && (this.getCommissionMoney() == null ? other.getCommissionMoney() == null : this.getCommissionMoney().equals(other.getCommissionMoney()))
                && (this.getProfitMoney() == null ? other.getProfitMoney() == null : this.getProfitMoney().equals(other.getProfitMoney()))
                && (this.getBrokerageMoney() == null ? other.getBrokerageMoney() == null : this.getBrokerageMoney().equals(other.getBrokerageMoney()))
                && (this.getDepositRedbag() == null ? other.getDepositRedbag() == null : this.getDepositRedbag().equals(other.getDepositRedbag()))
                && (this.getConsumeRedbag() == null ? other.getConsumeRedbag() == null : this.getConsumeRedbag().equals(other.getConsumeRedbag()))
                && (this.getEraseRadbag() == null ? other.getEraseRadbag() == null : this.getEraseRadbag().equals(other.getEraseRadbag()))
                && (this.getExchangeMoney() == null ? other.getExchangeMoney() == null : this.getExchangeMoney().equals(other.getExchangeMoney()))
                && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
                && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getDepositMoney() == null) ? 0 : getDepositMoney().hashCode());
        result = prime * result + ((getSysDepopsitMoney() == null) ? 0 : getSysDepopsitMoney().hashCode());
        result = prime * result + ((getDrawMoney() == null) ? 0 : getDrawMoney().hashCode());
        result = prime * result + ((getSysDrawMoney() == null) ? 0 : getSysDrawMoney().hashCode());
        result = prime * result + ((getPayMoney() == null) ? 0 : getPayMoney().hashCode());
        result = prime * result + ((getMarginMoney() == null) ? 0 : getMarginMoney().hashCode());
        result = prime * result + ((getCommissionMoney() == null) ? 0 : getCommissionMoney().hashCode());
        result = prime * result + ((getProfitMoney() == null) ? 0 : getProfitMoney().hashCode());
        result = prime * result + ((getBrokerageMoney() == null) ? 0 : getBrokerageMoney().hashCode());
        result = prime * result + ((getDepositRedbag() == null) ? 0 : getDepositRedbag().hashCode());
        result = prime * result + ((getConsumeRedbag() == null) ? 0 : getConsumeRedbag().hashCode());
        result = prime * result + ((getEraseRadbag() == null) ? 0 : getEraseRadbag().hashCode());
        result = prime * result + ((getExchangeMoney() == null) ? 0 : getExchangeMoney().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userId=").append(userId);
        sb.append(", depositMoney=").append(depositMoney);
        sb.append(", sysDepopsitMoney=").append(sysDepopsitMoney);
        sb.append(", drawMoney=").append(drawMoney);
        sb.append(", sysDrawMoney=").append(sysDrawMoney);
        sb.append(", payMoney=").append(payMoney);
        sb.append(", marginMoney=").append(marginMoney);
        sb.append(", commissionMoney=").append(commissionMoney);
        sb.append(", profitMoney=").append(profitMoney);
        sb.append(", brokerageMoney=").append(brokerageMoney);
        sb.append(", depositRedbag=").append(depositRedbag);
        sb.append(", consumeRedbag=").append(consumeRedbag);
        sb.append(", eraseRadbag=").append(eraseRadbag);
        sb.append(", exchangeMoney=").append(exchangeMoney);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
