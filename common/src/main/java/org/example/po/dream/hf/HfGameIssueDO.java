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
    private String issuecode;

    /**
     * 彩种id
     */
    private String lotteryid;

    /**
     * 开始销售时间
     */
    private Date salestarttime;

    /**
     * 结束销售时间
     */
    private Date saleendtime;

    @TableField("saleDrawTime")
    private Date saleDrawTime;

    /**
     * 开奖时间
     */
    private Date opendrawtime;

    /**
     * 中奖号码
     */
    private String numberrecord;

    /**
     * 求和结果
     */
    private String sumrecord;

    /**
     * 中奖方式
     */
    private String winrecord;

    /**
     * 提前结束秒
     */
    private Integer preendissue;

    /**
     * 上期
     */
    private String lastissuecode;

    /**
     *
     */
    private Integer issuestatus;

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
    @TableField("openDrawTimeByPlayNow")
    private Date openDrawTimeByPlayNow;


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
        HfGameIssueDO other = (HfGameIssueDO) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getIssuecode() == null ? other.getIssuecode() == null : this.getIssuecode().equals(other.getIssuecode()))
                && (this.getLotteryid() == null ? other.getLotteryid() == null : this.getLotteryid().equals(other.getLotteryid()))
                && (this.getSalestarttime() == null ? other.getSalestarttime() == null : this.getSalestarttime().equals(other.getSalestarttime()))
                && (this.getSaleendtime() == null ? other.getSaleendtime() == null : this.getSaleendtime().equals(other.getSaleendtime()))
                && (this.getOpendrawtime() == null ? other.getOpendrawtime() == null : this.getOpendrawtime().equals(other.getOpendrawtime()))
                && (this.getNumberrecord() == null ? other.getNumberrecord() == null : this.getNumberrecord().equals(other.getNumberrecord()))
                && (this.getSumrecord() == null ? other.getSumrecord() == null : this.getSumrecord().equals(other.getSumrecord()))
                && (this.getWinrecord() == null ? other.getWinrecord() == null : this.getWinrecord().equals(other.getWinrecord()))
                && (this.getPreendissue() == null ? other.getPreendissue() == null : this.getPreendissue().equals(other.getPreendissue()))
                && (this.getLastissuecode() == null ? other.getLastissuecode() == null : this.getLastissuecode().equals(other.getLastissuecode()))
                && (this.getIssuestatus() == null ? other.getIssuestatus() == null : this.getIssuestatus().equals(other.getIssuestatus()))
                && (this.getCreatetime() == null ? other.getCreatetime() == null : this.getCreatetime().equals(other.getCreatetime()))
                && (this.getUpdatetime() == null ? other.getUpdatetime() == null : this.getUpdatetime().equals(other.getUpdatetime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getIssuecode() == null) ? 0 : getIssuecode().hashCode());
        result = prime * result + ((getLotteryid() == null) ? 0 : getLotteryid().hashCode());
        result = prime * result + ((getSalestarttime() == null) ? 0 : getSalestarttime().hashCode());
        result = prime * result + ((getSaleendtime() == null) ? 0 : getSaleendtime().hashCode());
        result = prime * result + ((getOpendrawtime() == null) ? 0 : getOpendrawtime().hashCode());
        result = prime * result + ((getNumberrecord() == null) ? 0 : getNumberrecord().hashCode());
        result = prime * result + ((getSumrecord() == null) ? 0 : getSumrecord().hashCode());
        result = prime * result + ((getWinrecord() == null) ? 0 : getWinrecord().hashCode());
        result = prime * result + ((getPreendissue() == null) ? 0 : getPreendissue().hashCode());
        result = prime * result + ((getLastissuecode() == null) ? 0 : getLastissuecode().hashCode());
        result = prime * result + ((getIssuestatus() == null) ? 0 : getIssuestatus().hashCode());
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
        sb.append(", issuecode=").append(issuecode);
        sb.append(", lotteryid=").append(lotteryid);
        sb.append(", salestarttime=").append(salestarttime);
        sb.append(", saleendtime=").append(saleendtime);
        sb.append(", opendrawtime=").append(opendrawtime);
        sb.append(", numberrecord=").append(numberrecord);
        sb.append(", sumrecord=").append(sumrecord);
        sb.append(", winrecord=").append(winrecord);
        sb.append(", preendissue=").append(preendissue);
        sb.append(", lastissuecode=").append(lastissuecode);
        sb.append(", issuestatus=").append(issuestatus);
        sb.append(", createtime=").append(createtime);
        sb.append(", updatetime=").append(updatetime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
