package org.example.system.vo.dept.vo.dept;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * The type Dept create req vo.
 */
@Schema(description = "管理后台 - 部门创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DeptCreateReqVO extends DeptBaseVO {
}
