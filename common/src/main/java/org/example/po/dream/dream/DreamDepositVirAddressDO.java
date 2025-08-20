package org.example.po.dream.dream;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.example.util.date.DateUtils;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @TableName dream_deposit_vir_address
 */
@TableName(value ="dream_deposit_vir_address")
@Data
public class DreamDepositVirAddressDO implements Serializable {
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     *
     */
    @DateTimeFormat(pattern = DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @JsonFormat(pattern = DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date createTime;

    /**
     *
     */
    private Date updateTime;

    /**
     *
     */
    private Long version;

    /**
     *
     */
    private Integer deleted;

    /**
     *
     */
    private Integer status;

    /**
     *
     */
    private Long depositTypeId;

    /**
     * 协议
     */
    private String protocol;

    /**
     * 收款地址
     */
    private String address;

    /**
     * 备注
     */
    private String remark;

    /**
     * 最大限额
     */
    private String maxMount;

    /**
     * 排序值
     */
    private Integer sort;

    /**
     *
     */
    private String name;

    /**
     * 收款码
     */
    private String qrImage;

    private String label;

}
