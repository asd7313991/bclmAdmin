package org.example.po.dream.dream;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @TableName dream_u_statistics
 */
@TableName(value = "dream_u_statistics")
@Data
public class DreamUStatisticsDO implements Serializable {
    /**
     * 用户名
     */
    @TableId
    private Integer userId;

    /**
     * 关注
     */
    private Integer attention;

    /**
     * 粉丝
     */
    private Integer follower;

    /**
     * 观点
     */
    private Integer viewpoint;

    /**
     * 观点通过
     */
    private Integer viewpointPass;

    /**
     * 观点不通过
     */
    private Integer viewpointFail;

    /**
     * 回复
     */
    private Integer reply;

    /**
     * 回复通过
     */
    private Integer replyPass;

    /**
     * 回复不通过
     */
    private Integer replyFail;

    /**
     * 借款
     */
    private Integer loan;

    /**
     * 借款通过
     */
    private Integer loanPass;

    /**
     * 借款不通过
     */
    private Integer loanFail;

    /**
     * 贷款
     */
    private Integer lend;

    /**
     * 贷款通过
     */
    private Integer lendPass;

    /**
     * 贷款不通过
     */
    private Integer lendFail;

    /**
     * 看涨看跌次数
     */
    private Integer guessDirect;

    /**
     * 看涨看跌成功次数
     */
    private Integer guessDirectPass;

    /**
     *
     */
    private Date createDate;

    /**
     *
     */
    private Date modifyDate;

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
        DreamUStatisticsDO other = (DreamUStatisticsDO) that;
        return (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
                && (this.getAttention() == null ? other.getAttention() == null : this.getAttention().equals(other.getAttention()))
                && (this.getFollower() == null ? other.getFollower() == null : this.getFollower().equals(other.getFollower()))
                && (this.getViewpoint() == null ? other.getViewpoint() == null : this.getViewpoint().equals(other.getViewpoint()))
                && (this.getViewpointPass() == null ? other.getViewpointPass() == null : this.getViewpointPass().equals(other.getViewpointPass()))
                && (this.getViewpointFail() == null ? other.getViewpointFail() == null : this.getViewpointFail().equals(other.getViewpointFail()))
                && (this.getReply() == null ? other.getReply() == null : this.getReply().equals(other.getReply()))
                && (this.getReplyPass() == null ? other.getReplyPass() == null : this.getReplyPass().equals(other.getReplyPass()))
                && (this.getReplyFail() == null ? other.getReplyFail() == null : this.getReplyFail().equals(other.getReplyFail()))
                && (this.getLoan() == null ? other.getLoan() == null : this.getLoan().equals(other.getLoan()))
                && (this.getLoanPass() == null ? other.getLoanPass() == null : this.getLoanPass().equals(other.getLoanPass()))
                && (this.getLoanFail() == null ? other.getLoanFail() == null : this.getLoanFail().equals(other.getLoanFail()))
                && (this.getLend() == null ? other.getLend() == null : this.getLend().equals(other.getLend()))
                && (this.getLendPass() == null ? other.getLendPass() == null : this.getLendPass().equals(other.getLendPass()))
                && (this.getLendFail() == null ? other.getLendFail() == null : this.getLendFail().equals(other.getLendFail()))
                && (this.getGuessDirect() == null ? other.getGuessDirect() == null : this.getGuessDirect().equals(other.getGuessDirect()))
                && (this.getGuessDirectPass() == null ? other.getGuessDirectPass() == null : this.getGuessDirectPass().equals(other.getGuessDirectPass()))
                && (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()))
                && (this.getModifyDate() == null ? other.getModifyDate() == null : this.getModifyDate().equals(other.getModifyDate()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getAttention() == null) ? 0 : getAttention().hashCode());
        result = prime * result + ((getFollower() == null) ? 0 : getFollower().hashCode());
        result = prime * result + ((getViewpoint() == null) ? 0 : getViewpoint().hashCode());
        result = prime * result + ((getViewpointPass() == null) ? 0 : getViewpointPass().hashCode());
        result = prime * result + ((getViewpointFail() == null) ? 0 : getViewpointFail().hashCode());
        result = prime * result + ((getReply() == null) ? 0 : getReply().hashCode());
        result = prime * result + ((getReplyPass() == null) ? 0 : getReplyPass().hashCode());
        result = prime * result + ((getReplyFail() == null) ? 0 : getReplyFail().hashCode());
        result = prime * result + ((getLoan() == null) ? 0 : getLoan().hashCode());
        result = prime * result + ((getLoanPass() == null) ? 0 : getLoanPass().hashCode());
        result = prime * result + ((getLoanFail() == null) ? 0 : getLoanFail().hashCode());
        result = prime * result + ((getLend() == null) ? 0 : getLend().hashCode());
        result = prime * result + ((getLendPass() == null) ? 0 : getLendPass().hashCode());
        result = prime * result + ((getLendFail() == null) ? 0 : getLendFail().hashCode());
        result = prime * result + ((getGuessDirect() == null) ? 0 : getGuessDirect().hashCode());
        result = prime * result + ((getGuessDirectPass() == null) ? 0 : getGuessDirectPass().hashCode());
        result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
        result = prime * result + ((getModifyDate() == null) ? 0 : getModifyDate().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userId=").append(userId);
        sb.append(", attention=").append(attention);
        sb.append(", follower=").append(follower);
        sb.append(", viewpoint=").append(viewpoint);
        sb.append(", viewpointPass=").append(viewpointPass);
        sb.append(", viewpointFail=").append(viewpointFail);
        sb.append(", reply=").append(reply);
        sb.append(", replyPass=").append(replyPass);
        sb.append(", replyFail=").append(replyFail);
        sb.append(", loan=").append(loan);
        sb.append(", loanPass=").append(loanPass);
        sb.append(", loanFail=").append(loanFail);
        sb.append(", lend=").append(lend);
        sb.append(", lendPass=").append(lendPass);
        sb.append(", lendFail=").append(lendFail);
        sb.append(", guessDirect=").append(guessDirect);
        sb.append(", guessDirectPass=").append(guessDirectPass);
        sb.append(", createDate=").append(createDate);
        sb.append(", modifyDate=").append(modifyDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
