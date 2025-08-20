package org.example.vo.dream;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.pojo.PageParam;
import org.example.util.date.DateUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;


/**
 * @author D588
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class DreamUVirAddressVO  extends PageParam {

    /**
     * ID
     */
    @TableId
    private Long id;


    @DateTimeFormat(pattern = DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @JsonFormat(pattern = DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

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
