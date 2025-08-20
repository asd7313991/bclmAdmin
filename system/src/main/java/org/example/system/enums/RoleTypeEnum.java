package org.example.system.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * The enum Role type enum.
 */
@Getter
@AllArgsConstructor
public enum RoleTypeEnum {

    /**
     * 内置角色
     */
    SYSTEM(1),
    /**
     * 自定义角色
     */
    CUSTOM(2);

    private final Integer type;

}
