package org.example.po.dream.dream;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @TableName dream_u_finance_deposit
 */
@TableName(value = "dream_u_finance_deposit")
@Data
public class DreamUFinanceDepositDO implements Serializable {
    /**
     * 充值id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 充值类型1微信充值2支付宝充值3银行卡充值
     */
    private Integer type;

    /**
     * 描述
     */
    private String remark;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 充值金额
     */
    private Double money;

    /**
     * 内部订单id
     */
    private String submitOrderId;

    /**
     * 流水id
     */
    private Integer ioId;

    /**
     * 回调描述
     */
    private String comments;

    /**
     * 1刚刚发起 2充值成功 3充值失败  4交易不存在
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
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
        DreamUFinanceDepositDO other = (DreamUFinanceDepositDO) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
                && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
                && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
                && (this.getMoney() == null ? other.getMoney() == null : this.getMoney().equals(other.getMoney()))
                && (this.getSubmitOrderId() == null ? other.getSubmitOrderId() == null : this.getSubmitOrderId().equals(other.getSubmitOrderId()))
                && (this.getIoId() == null ? other.getIoId() == null : this.getIoId().equals(other.getIoId()))
                && (this.getComments() == null ? other.getComments() == null : this.getComments().equals(other.getComments()))
                && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
                && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
                && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getMoney() == null) ? 0 : getMoney().hashCode());
        result = prime * result + ((getSubmitOrderId() == null) ? 0 : getSubmitOrderId().hashCode());
        result = prime * result + ((getIoId() == null) ? 0 : getIoId().hashCode());
        result = prime * result + ((getComments() == null) ? 0 : getComments().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
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
        sb.append(", id=").append(id);
        sb.append(", type=").append(type);
        sb.append(", remark=").append(remark);
        sb.append(", userId=").append(userId);
        sb.append(", money=").append(money);
        sb.append(", submitOrderId=").append(submitOrderId);
        sb.append(", ioId=").append(ioId);
        sb.append(", comments=").append(comments);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
