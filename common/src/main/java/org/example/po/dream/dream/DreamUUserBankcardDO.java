package org.example.po.dream.dream;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @TableName dream_u_user_bankcard
 */
@TableName(value = "dream_u_user_bankcard")
@Data
public class DreamUUserBankcardDO implements Serializable {
    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 用户id,用用户表中的主键id作为本表中的userid
     */
    private Integer userId;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 身份证号
     */
    private String idCard;

    /**
     * 账户实名认证状态，0：未认证；1：已填写；2：已认证
     */
    private Integer idStatus;

    /**
     *
     */
    private Integer bankId;

    /**
     * 发卡行标识
     */
    private Integer issuingBank;

    /**
     * 发卡行名字
     */
    private String issuingBankName;

    /**
     * 银行卡号
     */
    private String cardNumber;

    /**
     * 银行卡绑定的手机号
     */
    private String cardPhone;

    /**
     * 银行卡的认证状态，0姓名、身份证已填写；1银行卡已填写；2已认证
     */
    private Integer cardState;

    /**
     *
     */
    private Date createTime;

    /**
     *
     */
    private Date updateTime;

    /**
     * 银行卡状态  0未绑定  1已填写  2已绑定
     */
    private Integer bindStatus;

    /**
     * 备注
     */
    private String remark;

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
        DreamUUserBankcardDO other = (DreamUUserBankcardDO) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
                && (this.getRealName() == null ? other.getRealName() == null : this.getRealName().equals(other.getRealName()))
                && (this.getIdCard() == null ? other.getIdCard() == null : this.getIdCard().equals(other.getIdCard()))
                && (this.getIdStatus() == null ? other.getIdStatus() == null : this.getIdStatus().equals(other.getIdStatus()))
                && (this.getBankId() == null ? other.getBankId() == null : this.getBankId().equals(other.getBankId()))
                && (this.getIssuingBank() == null ? other.getIssuingBank() == null : this.getIssuingBank().equals(other.getIssuingBank()))
                && (this.getIssuingBankName() == null ? other.getIssuingBankName() == null : this.getIssuingBankName().equals(other.getIssuingBankName()))
                && (this.getCardNumber() == null ? other.getCardNumber() == null : this.getCardNumber().equals(other.getCardNumber()))
                && (this.getCardPhone() == null ? other.getCardPhone() == null : this.getCardPhone().equals(other.getCardPhone()))
                && (this.getCardState() == null ? other.getCardState() == null : this.getCardState().equals(other.getCardState()))
                && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
                && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
                && (this.getBindStatus() == null ? other.getBindStatus() == null : this.getBindStatus().equals(other.getBindStatus()))
                && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getRealName() == null) ? 0 : getRealName().hashCode());
        result = prime * result + ((getIdCard() == null) ? 0 : getIdCard().hashCode());
        result = prime * result + ((getIdStatus() == null) ? 0 : getIdStatus().hashCode());
        result = prime * result + ((getBankId() == null) ? 0 : getBankId().hashCode());
        result = prime * result + ((getIssuingBank() == null) ? 0 : getIssuingBank().hashCode());
        result = prime * result + ((getIssuingBankName() == null) ? 0 : getIssuingBankName().hashCode());
        result = prime * result + ((getCardNumber() == null) ? 0 : getCardNumber().hashCode());
        result = prime * result + ((getCardPhone() == null) ? 0 : getCardPhone().hashCode());
        result = prime * result + ((getCardState() == null) ? 0 : getCardState().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getBindStatus() == null) ? 0 : getBindStatus().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
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
        sb.append(", realName=").append(realName);
        sb.append(", idCard=").append(idCard);
        sb.append(", idStatus=").append(idStatus);
        sb.append(", bankId=").append(bankId);
        sb.append(", issuingBank=").append(issuingBank);
        sb.append(", issuingBankName=").append(issuingBankName);
        sb.append(", cardNumber=").append(cardNumber);
        sb.append(", cardPhone=").append(cardPhone);
        sb.append(", cardState=").append(cardState);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", bindStatus=").append(bindStatus);
        sb.append(", remark=").append(remark);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
