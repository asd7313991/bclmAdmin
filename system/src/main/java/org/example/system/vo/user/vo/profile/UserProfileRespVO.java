package org.example.system.vo.user.vo.profile;

//import cn.iocoder.yudao.module.system.controller.admin.user.vo.user.UserBaseVO;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.example.system.vo.user.vo.user.UserBaseVO;
import org.example.util.date.DateUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;


/**
 * The type User profile resp vo.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "管理后台 - 用户个人中心信息 Response VO")
public class UserProfileRespVO extends UserBaseVO {

    @Schema(description = "用户编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long id;

    @Schema(description = "状态，参见 CommonStatusEnum 枚举类", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Integer status;

    @Schema(description = "最后登录 IP", requiredMode = Schema.RequiredMode.REQUIRED, example = "192.168.1.1")
    private String loginIp;

    @DateTimeFormat(pattern = DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @JsonFormat(pattern = DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime loginDate;

    @DateTimeFormat(pattern = DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @JsonFormat(pattern = DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime createTime;

    /**
     * 所属角色
     */
    private List<Role> roles;

    /**
     * 所在部门
     */
    private Dept dept;

    /**
     * 所属岗位数组
     */
    private List<Post> posts;
    /**
     * 社交用户数组
     */
    private List<SocialUser> socialUsers;

    /**
     * The type Role.
     */
    @Schema(description = "角色")
    @Data
    public static class Role {

        @Schema(description = "角色编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
        private Long id;

        @Schema(description = "角色名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "普通角色")
        private String name;

    }

    /**
     * The type Dept.
     */
    @Schema(description = "部门")
    @Data
    public static class Dept {

        @Schema(description = "部门编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
        private Long id;

        @Schema(description = "部门名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "研发部")
        private String name;

    }

    /**
     * The type Post.
     */
    @Schema(description = "岗位")
    @Data
    public static class Post {

        @Schema(description = "岗位编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
        private Long id;

        @Schema(description = "岗位名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "开发")
        private String name;

    }

    /**
     * The type Social user.
     */
    @Schema(description = "社交用户")
    @Data
    public static class SocialUser {

        @Schema(description = "社交平台的类型，参见 SocialTypeEnum 枚举类", requiredMode = Schema.RequiredMode.REQUIRED, example = "10")
        private Integer type;

        @Schema(description = "社交用户的 openid", requiredMode = Schema.RequiredMode.REQUIRED, example = "IPRmJ0wvBptiPIlGEZiPewGwiEiE")
        private String openid;

    }

}
