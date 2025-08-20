package org.example.po.system;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName sa_token
 */
@TableName(value ="sa_token")
@Data
public class SaTokenDO implements Serializable {
    /**
     * 
     */
    private Long id;

    /**
     * 
     */
    private String tokenKey;

    /**
     * 
     */
    private String value;

    /**
     * 
     */
    private Date createTime;

    /**
     * 
     */
    private Long timeOut;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;


}