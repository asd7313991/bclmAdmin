package org.example.system.db.mysql.mapper.system;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.config.mybatisplus.BaseMapperX;
import org.example.system.db.mysql.po.system.RoleDO;
import org.example.system.db.mysql.po.system.UserRoleDO;

import java.util.Collection;
import java.util.List;

/**
 * The interface User role mapper.
 */
@Mapper
public interface UserRoleMapper extends BaseMapperX<UserRoleDO> {


    /**
     * Select by user id list.
     *
     * @param userId the user id
     * @return list
     */
    List<RoleDO> selectByUserId(@Param("userId") Long userId);


    /**
     * Select list by user id list.
     *
     * @param userId the user id
     * @return the list
     */
    default List<UserRoleDO> selectListByUserId(Long userId) {
        return selectList(UserRoleDO::getUserId, userId);
    }

    /**
     * Delete list by user id and role id ids.
     *
     * @param userId  the user id
     * @param roleIds the role ids
     */
    default void deleteListByUserIdAndRoleIdIds(Long userId, Collection<Long> roleIds) {
        delete(new LambdaQueryWrapper<UserRoleDO>()
                .eq(UserRoleDO::getUserId, userId)
                .in(UserRoleDO::getRoleId, roleIds));
    }

    /**
     * Delete list by user id.
     *
     * @param userId the user id
     */
    default void deleteListByUserId(Long userId) {
        delete(new LambdaQueryWrapper<UserRoleDO>().eq(UserRoleDO::getUserId, userId));
    }

    /**
     * Delete list by role id.
     *
     * @param roleId the role id
     */
    default void deleteListByRoleId(Long roleId) {
        delete(new LambdaQueryWrapper<UserRoleDO>().eq(UserRoleDO::getRoleId, roleId));
    }

    /**
     * Select list by role ids list.
     *
     * @param roleIds the role ids
     * @return the list
     */
    default List<UserRoleDO> selectListByRoleIds(Collection<Long> roleIds) {
        return selectList(UserRoleDO::getRoleId, roleIds);
    }

}
