package org.example.system.vo.dept.vo.post;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * The type Post create req vo.
 */
@Schema(description = "管理后台 - 岗位创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
public class PostCreateReqVO extends PostBaseVO {
}
