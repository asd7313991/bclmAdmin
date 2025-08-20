package org.example.vo.dream;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.pojo.PageParam;
import org.example.util.date.DateUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class DepositVirAddressVO extends PageParam {


    private Long id;



    @DateTimeFormat(pattern = DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @JsonFormat(pattern = DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;


    private Long userId;

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
