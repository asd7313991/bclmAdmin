package org.example.system.db.mysql.mapper.system;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.config.mybatisplus.BaseMapperX;
import org.example.system.db.mysql.po.system.MenuDO;
import org.example.system.db.mysql.po.system.RoleMenuDO;

import java.util.Collection;
import java.util.List;

/**
 * The interface Role menu mapper.
 */
@Mapper
public interface RoleMenuMapper extends BaseMapperX<RoleMenuDO> {

    /**
     * Select list by role id list.
     *
     * @param roleId the role id
     * @return the list
     */
    default List<RoleMenuDO> selectListByRoleId(Long roleId) {
        return selectList(RoleMenuDO::getRoleId, roleId);
    }

    /**
     * Select list by role id list.
     *
     * @param roleIds the role ids
     * @return the list
     */
    default List<RoleMenuDO> selectListByRoleId(Collection<Long> roleIds) {
        return selectList(RoleMenuDO::getRoleId, roleIds);
    }

    /**
     * Select list by menu id list.
     *
     * @param menuId the menu id
     * @return the list
     */
    default List<RoleMenuDO> selectListByMenuId(Long menuId) {
        return selectList(RoleMenuDO::getMenuId, menuId);
    }

    /**
     * Delete list by role id and menu ids.
     *
     * @param roleId  the role id
     * @param menuIds the menu ids
     */
    default void deleteListByRoleIdAndMenuIds(Long roleId, Collection<Long> menuIds) {
        delete(new LambdaQueryWrapper<RoleMenuDO>()
                .eq(RoleMenuDO::getRoleId, roleId)
                .in(RoleMenuDO::getMenuId, menuIds));
    }

    /**
     * Delete list by menu id.
     *
     * @param menuId the menu id
     */
    default void deleteListByMenuId(Long menuId) {
        delete(new LambdaQueryWrapper<RoleMenuDO>().eq(RoleMenuDO::getMenuId, menuId));
    }

    /**
     * Delete list by role id.
     *
     * @param roleId the role id
     */
    default void deleteListByRoleId(Long roleId) {
        delete(new LambdaQueryWrapper<RoleMenuDO>().eq(RoleMenuDO::getRoleId, roleId));
    }

    /**
     * Select list by role ids list.
     *
     * @param roleList the role list
     * @return the list
     */
    List<MenuDO> selectListByRoleIds(@Param("roleList") List<Long> roleList);

}
