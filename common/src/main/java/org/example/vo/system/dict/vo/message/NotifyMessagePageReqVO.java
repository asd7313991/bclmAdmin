package org.example.vo.system.dict.vo.message;

//import cn.iocoder.yudao.framework.common.pojo.PageParam;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.example.pojo.PageParam;
import org.example.util.date.DateUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 站内信分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class NotifyMessagePageReqVO extends PageParam {

    @Schema(description = "用户编号", example = "25025")
    private Long userId;

    @Schema(description = "用户类型", example = "1")
    private Integer userType;

    @Schema(description = "模板编码", example = "test_01")
    private String templateCode;

    @Schema(description = "模版类型", example = "2")
    private Integer templateType;

    @DateTimeFormat(pattern = DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @JsonFormat(pattern = DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
