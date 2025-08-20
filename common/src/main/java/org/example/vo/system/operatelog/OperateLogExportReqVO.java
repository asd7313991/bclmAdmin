package org.example.vo.system.operatelog;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.util.date.DateUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;


@EqualsAndHashCode(callSuper = true)
@Schema(description = "管理后台 - 操作日志分页列表 Request VO")
@Data
public class OperateLogExportReqVO extends OperateLogPageReqVO {

    @Schema(description = "操作模块，模拟匹配", example = "订单")
    private String module;

    @Schema(description = "用户昵称，模拟匹配", example = "后台")
    private String userNickname;

    @Schema(description = "操作分类，参见 OperateLogTypeEnum 枚举类", example = "1")
    private Integer type;

    @Schema(description = "操作状态", example = "true")
    private Boolean success;

    @DateTimeFormat(pattern = DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @JsonFormat(pattern = DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] startTime;

}
