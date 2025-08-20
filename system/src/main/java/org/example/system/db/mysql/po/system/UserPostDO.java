package org.example.system.db.mysql.po.system;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.po.BaseEntity;

/**
 * 用户和岗位关联
 *
 * @author ruoyi
 */
@TableName("system_user_post")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserPostDO extends BaseEntity {

    /**
     * 用户 ID
     *
     * 关联 {@link AdminUserDO#getId()}
     */
    private Long userId;
    /**
     * 角色 ID
     *
     * 关联 {@link PostDO#getId()}
     */
    private Long postId;

}
