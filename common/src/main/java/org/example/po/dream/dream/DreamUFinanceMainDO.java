package org.example.po.dream.dream;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @TableName dream_u_finance_main
 */
@TableName(value = "dream_u_finance_main")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DreamUFinanceMainDO implements Serializable {
    public DreamUFinanceMainDO(Long userId) {
        this.userId = userId;
    }

    /**
     * 用户名
     */
    @TableId
    private Long userId;

    /**
     * 可用资金余额
     */
    private BigDecimal moneyUsable;

    /**
     * 冻结资金
     */
    private BigDecimal moneyFrozen;

    /**
     * 可提现资金余额
     */
    private BigDecimal moneyDrawUsable;

    /**
     * 当前用户保证金余额
     */
    private BigDecimal margin;

    /**
     *
     */
    private Date createTime;

    /**
     *
     */
    private Date updateTime;

    /**
     * 安全密码
     */
    private String password;

    /**
     * 密言
     */
    private String salt;

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
        DreamUFinanceMainDO other = (DreamUFinanceMainDO) that;
        return (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
                && (this.getMoneyUsable() == null ? other.getMoneyUsable() == null : this.getMoneyUsable().equals(other.getMoneyUsable()))
                && (this.getMoneyFrozen() == null ? other.getMoneyFrozen() == null : this.getMoneyFrozen().equals(other.getMoneyFrozen()))
                && (this.getMoneyDrawUsable() == null ? other.getMoneyDrawUsable() == null : this.getMoneyDrawUsable().equals(other.getMoneyDrawUsable()))
                && (this.getMargin() == null ? other.getMargin() == null : this.getMargin().equals(other.getMargin()))
                && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
                && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
                && (this.getPassword() == null ? other.getPassword() == null : this.getPassword().equals(other.getPassword()))
                && (this.getSalt() == null ? other.getSalt() == null : this.getSalt().equals(other.getSalt()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getMoneyUsable() == null) ? 0 : getMoneyUsable().hashCode());
        result = prime * result + ((getMoneyFrozen() == null) ? 0 : getMoneyFrozen().hashCode());
        result = prime * result + ((getMoneyDrawUsable() == null) ? 0 : getMoneyDrawUsable().hashCode());
        result = prime * result + ((getMargin() == null) ? 0 : getMargin().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
        result = prime * result + ((getSalt() == null) ? 0 : getSalt().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userId=").append(userId);
        sb.append(", moneyUsable=").append(moneyUsable);
        sb.append(", moneyFrozen=").append(moneyFrozen);
        sb.append(", moneyDrawUsable=").append(moneyDrawUsable);
        sb.append(", margin=").append(margin);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", password=").append(password);
        sb.append(", salt=").append(salt);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
