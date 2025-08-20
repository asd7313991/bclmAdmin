package org.example.system.vo.dept.vo.post;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * The type Post list req vo.
 */
@Schema(description = "管理后台 - 岗位列表 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
public class PostListReqVO extends PostBaseVO {

    @Schema(description = "岗位名称，模糊匹配", example = "后台")
    private String name;

    @Schema(description = "展示状态，参见 CommonStatusEnum 枚举类", example = "1")
    private Integer status;

}
