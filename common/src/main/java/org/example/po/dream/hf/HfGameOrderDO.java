package org.example.po.dream.hf;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @TableName hf_game_order
 */
@TableName(value = "hf_game_order")
@Data
public class HfGameOrderDO implements Serializable {
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 期号
     */
    private String issuecode;

    /**
     * 名称
     */
    private String lotteryid;

    /**
     * 销售时间
     */
    private Date saletime;

    /**
     * 计奖时间
     */
    private Date calculatewintime;

    /**
     * 订单编码
     */
    private String ordercode;

    /**
     * 总金额
     */
    private Long totalamount;

    /**
     * 状态
     */
    private Integer orderstatus;

    /**
     * 下注内容
     */
    private String numberrecord;

    /**
     * 追号id
     */
    private Integer gameplanid;

    /**
     * 追号详情id
     */
    private Integer gameplandetailid;

    /**
     * 用户id
     */
    private Integer userid;

    /**
     * 实际奖金额
     */
    private Double accwin;

    /**
     *
     */
    private Double prewinnum;

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
        HfGameOrderDO other = (HfGameOrderDO) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getIssuecode() == null ? other.getIssuecode() == null : this.getIssuecode().equals(other.getIssuecode()))
                && (this.getLotteryid() == null ? other.getLotteryid() == null : this.getLotteryid().equals(other.getLotteryid()))
                && (this.getSaletime() == null ? other.getSaletime() == null : this.getSaletime().equals(other.getSaletime()))
                && (this.getCalculatewintime() == null ? other.getCalculatewintime() == null : this.getCalculatewintime().equals(other.getCalculatewintime()))
                && (this.getOrdercode() == null ? other.getOrdercode() == null : this.getOrdercode().equals(other.getOrdercode()))
                && (this.getTotalamount() == null ? other.getTotalamount() == null : this.getTotalamount().equals(other.getTotalamount()))
                && (this.getOrderstatus() == null ? other.getOrderstatus() == null : this.getOrderstatus().equals(other.getOrderstatus()))
                && (this.getNumberrecord() == null ? other.getNumberrecord() == null : this.getNumberrecord().equals(other.getNumberrecord()))
                && (this.getGameplanid() == null ? other.getGameplanid() == null : this.getGameplanid().equals(other.getGameplanid()))
                && (this.getGameplandetailid() == null ? other.getGameplandetailid() == null : this.getGameplandetailid().equals(other.getGameplandetailid()))
                && (this.getUserid() == null ? other.getUserid() == null : this.getUserid().equals(other.getUserid()))
                && (this.getAccwin() == null ? other.getAccwin() == null : this.getAccwin().equals(other.getAccwin()))
                && (this.getPrewinnum() == null ? other.getPrewinnum() == null : this.getPrewinnum().equals(other.getPrewinnum()))
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
        result = prime * result + ((getSaletime() == null) ? 0 : getSaletime().hashCode());
        result = prime * result + ((getCalculatewintime() == null) ? 0 : getCalculatewintime().hashCode());
        result = prime * result + ((getOrdercode() == null) ? 0 : getOrdercode().hashCode());
        result = prime * result + ((getTotalamount() == null) ? 0 : getTotalamount().hashCode());
        result = prime * result + ((getOrderstatus() == null) ? 0 : getOrderstatus().hashCode());
        result = prime * result + ((getNumberrecord() == null) ? 0 : getNumberrecord().hashCode());
        result = prime * result + ((getGameplanid() == null) ? 0 : getGameplanid().hashCode());
        result = prime * result + ((getGameplandetailid() == null) ? 0 : getGameplandetailid().hashCode());
        result = prime * result + ((getUserid() == null) ? 0 : getUserid().hashCode());
        result = prime * result + ((getAccwin() == null) ? 0 : getAccwin().hashCode());
        result = prime * result + ((getPrewinnum() == null) ? 0 : getPrewinnum().hashCode());
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
        sb.append(", saletime=").append(saletime);
        sb.append(", calculatewintime=").append(calculatewintime);
        sb.append(", ordercode=").append(ordercode);
        sb.append(", totalamount=").append(totalamount);
        sb.append(", orderstatus=").append(orderstatus);
        sb.append(", numberrecord=").append(numberrecord);
        sb.append(", gameplanid=").append(gameplanid);
        sb.append(", gameplandetailid=").append(gameplandetailid);
        sb.append(", userid=").append(userid);
        sb.append(", accwin=").append(accwin);
        sb.append(", prewinnum=").append(prewinnum);
        sb.append(", createtime=").append(createtime);
        sb.append(", updatetime=").append(updatetime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
