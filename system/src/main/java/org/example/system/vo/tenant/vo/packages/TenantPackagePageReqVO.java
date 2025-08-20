package org.example.system.vo.tenant.vo.packages;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.example.pojo.PageParam;
import org.example.util.date.DateUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;


/**
 * The type Tenant package page req vo.
 */
@Schema(description = "管理后台 - 租户套餐分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TenantPackagePageReqVO extends PageParam {

    @Schema(description = "套餐名", example = "VIP")
    private String name;

    @Schema(description = "状态", example = "1")
    private Integer status;

    @Schema(description = "备注", example = "好")
    private String remark;

    @DateTimeFormat(pattern = DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @JsonFormat(pattern = DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;
}
