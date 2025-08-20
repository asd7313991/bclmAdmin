package org.example.system.config.satoken;

import cn.dev33.satoken.stp.StpInterface;
import org.example.system.vo.permission.vo.menu.MenuSimpleRespVO;
import org.example.system.vo.permission.vo.role.RoleSimpleRespVO;

import java.util.List;

/**
 * The interface System stp interface.
 */
public interface SystemStpInterface extends StpInterface {
    /**
     * Gets role vo lst.
     *
     * @param loginId   the login id
     * @param loginType the login type
     * @return the role vo lst
     */
    List<RoleSimpleRespVO> getRoleVoLst(Object loginId, String loginType);

    /**
     * Gets permission vo list.
     *
     * @param loginId   the login id
     * @param loginType the login type
     * @return the permission vo list
     */
    List<MenuSimpleRespVO> getPermissionVoList(Object loginId, String loginType);
}
