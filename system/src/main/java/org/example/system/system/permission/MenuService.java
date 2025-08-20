package org.example.system.system.permission;


import org.example.system.db.mysql.po.system.MenuDO;
import org.example.system.vo.permission.vo.menu.MenuCreateReqVO;
import org.example.system.vo.permission.vo.menu.MenuListReqVO;
import org.example.system.vo.permission.vo.menu.MenuUpdateReqVO;

import java.util.Collection;
import java.util.List;

/**
 * 菜单 Service 接口
 *
 * @author 后台源码
 */
public interface MenuService {

    /**
     * 创建菜单
     *
     * @param reqVO 菜单信息
     * @return 创建出来的菜单编号 long
     */
    Long createMenu(MenuCreateReqVO reqVO);

    /**
     * 更新菜单
     *
     * @param reqVO 菜单信息
     */
    void updateMenu(MenuUpdateReqVO reqVO);

    /**
     * 删除菜单
     *
     * @param id 菜单编号
     */
    void deleteMenu(Long id);

    /**
     * 获得所有菜单列表
     *
     * @return 菜单列表 menu list
     */
    List<MenuDO> getMenuList();

    /**
     * 基于租户，筛选菜单列表
     * 注意，如果是系统租户，返回的还是全菜单
     *
     * @param reqVO 筛选条件请求 VO
     * @return 菜单列表 menu list by tenant
     */
    List<MenuDO> getMenuListByTenant(MenuListReqVO reqVO);

    /**
     * 筛选菜单列表
     *
     * @param reqVO 筛选条件请求 VO
     * @return 菜单列表 menu list
     */
    List<MenuDO> getMenuList(MenuListReqVO reqVO);

    /**
     * 获得权限对应的菜单编号数组
     *
     * @param permission 权限标识
     * @return 数组 menu id list by permission from cache
     */
    List<Long> getMenuIdListByPermissionFromCache(String permission);

    /**
     * 获得菜单
     *
     * @param id 菜单编号
     * @return 菜单 menu
     */
    MenuDO getMenu(Long id);

    /**
     * 获得菜单数组
     *
     * @param ids 菜单编号数组
     * @return 菜单数组 menu list
     */
    List<MenuDO> getMenuList(Collection<Long> ids);

}
