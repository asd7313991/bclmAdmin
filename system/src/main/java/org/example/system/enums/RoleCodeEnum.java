package org.example.system.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.util.object.ObjectUtils;

/**
 * 角色标识枚举
 */
@Getter
@AllArgsConstructor
public enum RoleCodeEnum {

    /**
     * Super admin role code enum.
     */
    SUPER_ADMIN("super_admin", "超级管理员"),
    /**
     * Tenant admin role code enum.
     */
    TENANT_ADMIN("tenant_admin", "租户管理员"),
    ;

    /**
     * 角色编码
     */
    private final String code;
    /**
     * 名字
     */
    private final String name;

    /**
     * Is super admin boolean.
     *
     * @param code the code
     * @return the boolean
     */
    public static boolean isSuperAdmin(String code) {
        return ObjectUtils.equalsAny(code, SUPER_ADMIN.getCode());
    }

}
