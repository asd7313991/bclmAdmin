package org.example.system.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 菜单类型枚举类
 *
 * @author 后台源码
 */
@Getter
@AllArgsConstructor
public enum MenuTypeEnum {

    /**
     * Dir menu type enum.
     */
    DIR(1), // 目录
    /**
     * Menu menu type enum.
     */
    MENU(2), // 菜单
    /**
     * Button menu type enum.
     */
    BUTTON(3) // 按钮
    ;

    /**
     * 类型
     */
    private final Integer type;

}
