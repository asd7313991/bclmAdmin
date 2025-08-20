package org.example.system.db.mysql.po.system;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.po.BaseEntity;

/**
 * 角色和菜单关联
 *
 * @author ruoyi
 */
@TableName("system_role_menu")
@KeySequence("system_role_menu_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
public class RoleMenuDO  extends BaseEntity {
//
//    /**
//     * 自增主键
//     */
//    @TableId
//    private Long id;
    /**
     * 角色ID
     */
    private Long roleId;
    /**
     * 菜单ID
     */
    private Long menuId;

}
