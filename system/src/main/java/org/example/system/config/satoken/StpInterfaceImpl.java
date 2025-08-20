package org.example.system.config.satoken;

import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.session.SaSessionCustomUtil;
import cn.hutool.core.collection.CollUtil;
import com.google.common.collect.Lists;
import ma.glasnost.orika.MapperFacade;
import org.example.config.satoken.StpSystemUtil;
import org.example.system.db.mysql.mapper.system.RoleMenuMapper;
import org.example.system.db.mysql.mapper.system.UserRoleMapper;
import org.example.system.db.mysql.po.system.MenuDO;
import org.example.system.vo.permission.vo.menu.MenuSimpleRespVO;
import org.example.system.vo.permission.vo.role.RoleSimpleRespVO;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Stp interface.
 */
@Component
public class StpInterfaceImpl implements SystemStpInterface {


    @Resource
    private UserRoleMapper userRoleMapper;
    @Resource
    private RoleMenuMapper roleMenuMapper;
    @Resource
    private MapperFacade mapperFacade;

    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        List<MenuSimpleRespVO> permissionVoList = this.getPermissionVoList(loginId, loginType);
        if (CollUtil.isNotEmpty(permissionVoList)) {
            return permissionVoList.stream().map(MenuSimpleRespVO::getPermission).distinct().collect(Collectors.toList());
        }
        return Lists.newArrayList();
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        List<RoleSimpleRespVO> roleVoLst = this.getRoleVoLst(loginId, loginType);
        if (CollUtil.isNotEmpty(roleVoLst)) {
            return roleVoLst.stream().map(RoleSimpleRespVO::getCode).collect(Collectors.toList());
        }
        return Lists.newArrayList();
    }


    @Override
    public List<RoleSimpleRespVO> getRoleVoLst(Object loginId, String loginType) {
        SaSession roleIdSaSession = StpSystemUtil.getSessionByLoginId(loginId);
//        return roleIdSaSession.get("role_list", () -> mapperFacade.mapAsList(userRoleMapper.selectByUserId(Long.valueOf((String) loginId)),RoleSimpleRespVO.class));
        return mapperFacade.mapAsList(userRoleMapper.selectByUserId(Long.valueOf((String) loginId)),RoleSimpleRespVO.class);
    }

    @Override
    public List<MenuSimpleRespVO> getPermissionVoList(Object loginId, String loginType) {
        List<MenuSimpleRespVO> permissionList = new ArrayList<>();

        for (RoleSimpleRespVO roleSimpleRespVO : this.getRoleVoLst(loginId, loginType)) {
            SaSession roleSaSession = SaSessionCustomUtil.getSessionById("role_id_"+roleSimpleRespVO.getId());
//            permissionList.addAll(roleSaSession.get("permission", () -> {
//                List<MenuDO> menuDOS = roleMenuMapper.selectListByRoleIds(Lists.newArrayList(roleSimpleRespVO.getId()));
//                return mapperFacade.mapAsList(menuDOS, MenuSimpleRespVO.class);
//            }));
            List<MenuDO> menuDOS = roleMenuMapper.selectListByRoleIds(Lists.newArrayList(roleSimpleRespVO.getId()));
            permissionList.addAll( mapperFacade.mapAsList(menuDOS, MenuSimpleRespVO.class));
        }
        return permissionList;
    }

}
