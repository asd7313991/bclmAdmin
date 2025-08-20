package org.example.po.dream.hf;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @TableName hf_game_plan_detail
 */
@TableName(value = "hf_game_plan_detail")
@Data
public class HfGamePlanDetailDO implements Serializable {
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Long userId;
    /**
     * 追号编码
     */
    private String plancode;

    /**
     * 名称
     */
    private String gameissue;

    /**
     * 倍数
     */
    private Integer mutiple;

    /**
     * 总金额
     */
    private Integer totamount;

    /**
     * 状态
     */
    private Integer detailstatus;

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
        HfGamePlanDetailDO other = (HfGamePlanDetailDO) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getPlancode() == null ? other.getPlancode() == null : this.getPlancode().equals(other.getPlancode()))
                && (this.getGameissue() == null ? other.getGameissue() == null : this.getGameissue().equals(other.getGameissue()))
                && (this.getMutiple() == null ? other.getMutiple() == null : this.getMutiple().equals(other.getMutiple()))
                && (this.getTotamount() == null ? other.getTotamount() == null : this.getTotamount().equals(other.getTotamount()))
                && (this.getDetailstatus() == null ? other.getDetailstatus() == null : this.getDetailstatus().equals(other.getDetailstatus()))
                && (this.getCreatetime() == null ? other.getCreatetime() == null : this.getCreatetime().equals(other.getCreatetime()))
                && (this.getUpdatetime() == null ? other.getUpdatetime() == null : this.getUpdatetime().equals(other.getUpdatetime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getPlancode() == null) ? 0 : getPlancode().hashCode());
        result = prime * result + ((getGameissue() == null) ? 0 : getGameissue().hashCode());
        result = prime * result + ((getMutiple() == null) ? 0 : getMutiple().hashCode());
        result = prime * result + ((getTotamount() == null) ? 0 : getTotamount().hashCode());
        result = prime * result + ((getDetailstatus() == null) ? 0 : getDetailstatus().hashCode());
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
        sb.append(", plancode=").append(plancode);
        sb.append(", gameissue=").append(gameissue);
        sb.append(", mutiple=").append(mutiple);
        sb.append(", totamount=").append(totamount);
        sb.append(", detailstatus=").append(detailstatus);
        sb.append(", createtime=").append(createtime);
        sb.append(", updatetime=").append(updatetime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
