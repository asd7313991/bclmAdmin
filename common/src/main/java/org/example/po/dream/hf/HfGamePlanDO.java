package org.example.po.dream.hf;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @TableName hf_game_plan
 */
@TableName(value = "hf_game_plan")
@Data
public class HfGamePlanDO implements Serializable {
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 彩种id
     */
    private String lotteryid;

    /**
     * 开始期号
     */
    private String startisuuecode;

    /**
     * 完成期号
     */
    private Integer finishissue;

    /**
     * 总共期号
     */
    private Integer totalissue;

    /**
     * 停止模式
     */
    private Integer stopmode;

    /**
     * 追号类型
     */
    private Integer plantype;

    /**
     * 追号编码
     */
    private String plancode;

    /**
     * 销售总金额
     */
    private BigDecimal totamount;

    /**
     * 实际销售金额
     */
    private BigDecimal soldamount;

    /**
     * 追号状态
     */
    private Integer status;

    /**
     * 盈利金额
     */
    private BigDecimal winamount;

    /**
     *
     */
    private Integer userid;

    /**
     * 起始倍数
     */
    private Integer startmutiple;

    /**
     * 间隔期数
     */
    private Integer issuerate;

    /**
     * 利润率
     */
    private Integer winrate;

    /**
     *
     */
    private Integer startamount;


    /**
     *
     */
    private Double preWinNum;


    /**
     *
     */
    private String numberrecord;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 更新时间
     */
    private Date updatetime;


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
        HfGamePlanDO other = (HfGamePlanDO) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getLotteryid() == null ? other.getLotteryid() == null : this.getLotteryid().equals(other.getLotteryid()))
                && (this.getStartisuuecode() == null ? other.getStartisuuecode() == null : this.getStartisuuecode().equals(other.getStartisuuecode()))
                && (this.getFinishissue() == null ? other.getFinishissue() == null : this.getFinishissue().equals(other.getFinishissue()))
                && (this.getTotalissue() == null ? other.getTotalissue() == null : this.getTotalissue().equals(other.getTotalissue()))
                && (this.getStopmode() == null ? other.getStopmode() == null : this.getStopmode().equals(other.getStopmode()))
                && (this.getPlantype() == null ? other.getPlantype() == null : this.getPlantype().equals(other.getPlantype()))
                && (this.getPlancode() == null ? other.getPlancode() == null : this.getPlancode().equals(other.getPlancode()))
                && (this.getTotamount() == null ? other.getTotamount() == null : this.getTotamount().equals(other.getTotamount()))
                && (this.getSoldamount() == null ? other.getSoldamount() == null : this.getSoldamount().equals(other.getSoldamount()))
                && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
                && (this.getWinamount() == null ? other.getWinamount() == null : this.getWinamount().equals(other.getWinamount()))
                && (this.getUserid() == null ? other.getUserid() == null : this.getUserid().equals(other.getUserid()))
                && (this.getStartmutiple() == null ? other.getStartmutiple() == null : this.getStartmutiple().equals(other.getStartmutiple()))
                && (this.getIssuerate() == null ? other.getIssuerate() == null : this.getIssuerate().equals(other.getIssuerate()))
                && (this.getWinrate() == null ? other.getWinrate() == null : this.getWinrate().equals(other.getWinrate()))
                && (this.getStartamount() == null ? other.getStartamount() == null : this.getStartamount().equals(other.getStartamount()))
                && (this.getNumberrecord() == null ? other.getNumberrecord() == null : this.getNumberrecord().equals(other.getNumberrecord()))
                && (this.getCreatetime() == null ? other.getCreatetime() == null : this.getCreatetime().equals(other.getCreatetime()))
                && (this.getUpdatetime() == null ? other.getUpdatetime() == null : this.getUpdatetime().equals(other.getUpdatetime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getLotteryid() == null) ? 0 : getLotteryid().hashCode());
        result = prime * result + ((getStartisuuecode() == null) ? 0 : getStartisuuecode().hashCode());
        result = prime * result + ((getFinishissue() == null) ? 0 : getFinishissue().hashCode());
        result = prime * result + ((getTotalissue() == null) ? 0 : getTotalissue().hashCode());
        result = prime * result + ((getStopmode() == null) ? 0 : getStopmode().hashCode());
        result = prime * result + ((getPlantype() == null) ? 0 : getPlantype().hashCode());
        result = prime * result + ((getPlancode() == null) ? 0 : getPlancode().hashCode());
        result = prime * result + ((getTotamount() == null) ? 0 : getTotamount().hashCode());
        result = prime * result + ((getSoldamount() == null) ? 0 : getSoldamount().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getWinamount() == null) ? 0 : getWinamount().hashCode());
        result = prime * result + ((getUserid() == null) ? 0 : getUserid().hashCode());
        result = prime * result + ((getStartmutiple() == null) ? 0 : getStartmutiple().hashCode());
        result = prime * result + ((getIssuerate() == null) ? 0 : getIssuerate().hashCode());
        result = prime * result + ((getWinrate() == null) ? 0 : getWinrate().hashCode());
        result = prime * result + ((getStartamount() == null) ? 0 : getStartamount().hashCode());
        result = prime * result + ((getNumberrecord() == null) ? 0 : getNumberrecord().hashCode());
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
        sb.append(", lotteryid=").append(lotteryid);
        sb.append(", startisuuecode=").append(startisuuecode);
        sb.append(", finishissue=").append(finishissue);
        sb.append(", totalissue=").append(totalissue);
        sb.append(", stopmode=").append(stopmode);
        sb.append(", plantype=").append(plantype);
        sb.append(", plancode=").append(plancode);
        sb.append(", totamount=").append(totamount);
        sb.append(", soldamount=").append(soldamount);
        sb.append(", status=").append(status);
        sb.append(", winamount=").append(winamount);
        sb.append(", userid=").append(userid);
        sb.append(", startmutiple=").append(startmutiple);
        sb.append(", issuerate=").append(issuerate);
        sb.append(", winrate=").append(winrate);
        sb.append(", startamount=").append(startamount);
        sb.append(", numberrecord=").append(numberrecord);
        sb.append(", createtime=").append(createtime);
        sb.append(", updatetime=").append(updatetime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
