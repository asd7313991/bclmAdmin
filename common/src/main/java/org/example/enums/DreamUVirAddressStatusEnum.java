package org.example.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DreamUVirAddressStatusEnum {

    PENDING_REVIEW(0, "待审核"),
    REVIEWED(1, "已审核"),
    REVIEW_FAILED(-1, "审核失败")
    ;


    /**
     * 类型
     */
    private final Integer type;
    /**
     * 名字
     */
    private final String name;

}
