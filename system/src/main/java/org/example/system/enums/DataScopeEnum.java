package org.example.system.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 数据范围枚举类
 * <p>
 * 用于实现数据级别的权限
 *
 * @author
 */
@Getter
@AllArgsConstructor
public enum DataScopeEnum {

    /**
     * All data scope enum.
     */
    ALL(1), // 全部数据权限
    /**
     * Dept custom data scope enum.
     */
    DEPT_CUSTOM(2), // 指定部门数据权限
    /**
     * Dept only data scope enum.
     */
    DEPT_ONLY(3), // 部门数据权限
    /**
     * Dept and child data scope enum.
     */
    DEPT_AND_CHILD(4), // 部门及以下数据权限
    /**
     * Self data scope enum.
     */
    SELF(5); // 仅本人数据权限
    /**
     * 范围
     */
    private final Integer scope;

}
