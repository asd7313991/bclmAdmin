package org.example.system.vo.banner;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.example.enums.CommonStatusEnum;
import org.example.pojo.PageParam;
import org.example.util.date.DateUtils;
import org.example.validation.InEnum;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;


/**
 * The type Banner page req vo.
 *
 * @author xia
 */
@Schema(description = "管理后台 - Banner 分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BannerPageReqVO extends PageParam {

    @Schema(description = "标题")
    private String title;


    @Schema(description = "状态")
    @InEnum(CommonStatusEnum.class)
    private Integer status;

    @DateTimeFormat(pattern = DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @JsonFormat(pattern = DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
