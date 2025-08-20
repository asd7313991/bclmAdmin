package org.example.system.db.mysql.po.system;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.po.BaseEntity;

/**
 * 用户和角色关联
 *
 * @author ruoyi
 */
@TableName("system_user_role")
@Data
@EqualsAndHashCode(callSuper = false)
public class UserRoleDO extends BaseEntity {

    /**
     * 用户 ID
     */
    private Long userId;
    /**
     * 角色 ID
     */
    private Long roleId;

}
