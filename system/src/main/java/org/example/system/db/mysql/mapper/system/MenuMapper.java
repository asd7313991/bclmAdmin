package org.example.system.db.mysql.mapper.system;

import org.apache.ibatis.annotations.Mapper;
import org.example.config.mybatisplus.BaseMapperX;
import org.example.config.mybatisplus.LambdaQueryWrapperX;
import org.example.system.db.mysql.po.system.MenuDO;
import org.example.system.vo.permission.vo.menu.MenuListReqVO;

import java.util.List;

/**
 * The interface Menu mapper.
 */
@Mapper
public interface MenuMapper extends BaseMapperX<MenuDO> {

    /**
     * Select by parent id and name menu do.
     *
     * @param parentId the parent id
     * @param name     the name
     * @return the menu do
     */
    default MenuDO selectByParentIdAndName(Long parentId, String name) {
        return selectOne(MenuDO::getParentId, parentId, MenuDO::getName, name);
    }

    /**
     * Select count by parent id long.
     *
     * @param parentId the parent id
     * @return the long
     */
    default Long selectCountByParentId(Long parentId) {
        return selectCount(MenuDO::getParentId, parentId);
    }

    /**
     * Select list list.
     *
     * @param reqVO the req vo
     * @return the list
     */
    default List<MenuDO> selectList(MenuListReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<MenuDO>()
                .likeIfPresent(MenuDO::getName, reqVO.getName())
                .eqIfPresent(MenuDO::getStatus, reqVO.getStatus()));
    }

    /**
     * Select list by permission list.
     *
     * @param permission the permission
     * @return the list
     */
    default List<MenuDO> selectListByPermission(String permission) {
        return selectList(MenuDO::getPermission, permission);
    }
}
