package org.example.po.dream.hf;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @TableName hf_game_issue
 */
@TableName(value = "hf_game_issue")
@Data
public class HfGameIssueDO implements Serializable {
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 奖期
     */
    private String issueCode;

    /**
     * 彩种id
     */
    private String lotteryid;

    /**
     * 开始销售时间
     */
    @TableField("saleStartTime")
    private Date saleStartTime;

    /**
     * 结束销售时间
     */
    @TableField("saleEndTime")
    private Date saleEndTime;

    @TableField("saleDrawTime")
    private Date saleDrawTime;

    /**
     * 开奖时间
     */
    @TableField("openDrawTime")
    private Date openDrawTime;

    /**
     * 中奖号码
     */
    private String numberRecord;

    /**
     * 求和结果
     */
    private String sumRecord;

    /**
     * 中奖方式
     */
    private String winRecord;

    /**
     * 提前结束秒
     */
    private Integer preendissue;

    /**
     * 上期
     */
    private String lastIssueCode;

    /**
     *
     */
    private Integer issueStatus;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 更新时间
     */
    private Date updatetime;

    @TableField("receiveTime")
    private Date receiveTime;
    @TableField("OpenDrawTimeByPlayNow")
    private Date OpenDrawTimeByPlayNow;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    public Date getSaleStartTime() {
        return saleStartTime;
    }

    public void setSaleStartTime(Date saleStartTime) {
        this.saleStartTime = saleStartTime;
    }

    public Date getSaleEndTime() {
        return saleEndTime;
    }

    public void setSaleEndTime(Date saleEndTime) {
        this.saleEndTime = saleEndTime;
    }

    public Date getSaleDrawTime() {
        return saleDrawTime;
    }

    public void setSaleDrawTime(Date saleDrawTime) {
        this.saleDrawTime = saleDrawTime;
    }

    public Date getOpenDrawTime() {
        return openDrawTime;
    }

    public void setOpenDrawTime(Date openDrawTime) {
        this.openDrawTime = openDrawTime;
    }

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
        HfGameIssueDO other = (HfGameIssueDO) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getIssueCode() == null ? other.getIssueCode() == null : this.getIssueCode().equals(other.getIssueCode()))
                && (this.getLotteryid() == null ? other.getLotteryid() == null : this.getLotteryid().equals(other.getLotteryid()))
                && (this.getSaleStartTime() == null ? other.getSaleStartTime() == null : this.getSaleStartTime().equals(other.getSaleStartTime()))
                && (this.getSaleEndTime() == null ? other.getSaleEndTime() == null : this.getSaleEndTime().equals(other.getSaleEndTime()))
                && (this.getOpenDrawTime() == null ? other.getOpenDrawTime() == null : this.getOpenDrawTime().equals(other.getOpenDrawTime()))
                && (this.getNumberRecord() == null ? other.getNumberRecord() == null : this.getNumberRecord().equals(other.getNumberRecord()))
                && (this.getSumRecord() == null ? other.getSumRecord() == null : this.getSumRecord().equals(other.getSumRecord()))
                && (this.getWinRecord() == null ? other.getWinRecord() == null : this.getWinRecord().equals(other.getWinRecord()))
                && (this.getPreendissue() == null ? other.getPreendissue() == null : this.getPreendissue().equals(other.getPreendissue()))
                && (this.getLastIssueCode() == null ? other.getLastIssueCode() == null : this.getLastIssueCode().equals(other.getLastIssueCode()))
                && (this.getIssueStatus() == null ? other.getIssueStatus() == null : this.getIssueStatus().equals(other.getIssueStatus()))
                && (this.getCreatetime() == null ? other.getCreatetime() == null : this.getCreatetime().equals(other.getCreatetime()))
                && (this.getUpdatetime() == null ? other.getUpdatetime() == null : this.getUpdatetime().equals(other.getUpdatetime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getIssueCode() == null) ? 0 : getIssueCode().hashCode());
        result = prime * result + ((getLotteryid() == null) ? 0 : getLotteryid().hashCode());
        result = prime * result + ((getSaleStartTime() == null) ? 0 : getSaleStartTime().hashCode());
        result = prime * result + ((getSaleEndTime() == null) ? 0 : getSaleEndTime().hashCode());
        result = prime * result + ((getOpenDrawTime() == null) ? 0 : getOpenDrawTime().hashCode());
        result = prime * result + ((getNumberRecord() == null) ? 0 : getNumberRecord().hashCode());
        result = prime * result + ((getSumRecord() == null) ? 0 : getSumRecord().hashCode());
        result = prime * result + ((getWinRecord() == null) ? 0 : getWinRecord().hashCode());
        result = prime * result + ((getPreendissue() == null) ? 0 : getPreendissue().hashCode());
        result = prime * result + ((getLastIssueCode() == null) ? 0 : getLastIssueCode().hashCode());
        result = prime * result + ((getIssueStatus() == null) ? 0 : getIssueStatus().hashCode());
        result = prime * result + ((getCreatetime() == null) ? 0 : getCreatetime().hashCode());
        result = prime * result + ((getUpdatetime() == null) ? 0 : getUpdatetime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", IssueCode=").append(issueCode);
        sb.append(", lotteryid=").append(lotteryid);
        sb.append(", SaleStartTime=").append(saleStartTime);
        sb.append(", SaleEndTime=").append(saleEndTime);
        sb.append(", OpenDrawTime=").append(openDrawTime);
        sb.append(", NumberRecord=").append(numberRecord);
        sb.append(", SumRecord=").append(sumRecord);
        sb.append(", WinRecord=").append(winRecord);
        sb.append(", preendissue=").append(preendissue);
        sb.append(", LastIssueCode=").append(lastIssueCode);
        sb.append(", IssueStatus=").append(issueStatus);
        sb.append(", createtime=").append(createtime);
        sb.append(", updatetime=").append(updatetime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
