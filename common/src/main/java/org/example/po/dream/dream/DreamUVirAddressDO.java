package org.example.po.dream.dream;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @TableName dream_u_vir_address
 */
@TableName(value = "dream_u_vir_address")
@Data
public class DreamUVirAddressDO implements Serializable {
    /**
     * ID
     */
    @TableId
    private Long id;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 版本好
     */
    private Long version;

    /**
     * 是否删除
     */
    private Integer deleted;

    /**
     * 地址名称
     */
    private String name;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 虚拟币币种
     */
    private String currency;

    /**
     * 虚拟币协议
     */
    private String protocol;

    /**
     * 虚拟币地址
     */
    private String address;

    /**
     * 是否默认 0：非默认，1：默认
     */
    private Integer defaulted;

    /**
     * 状态，0：待审核，1：已审核，-1：审核失败
     */
    private Integer status;

}