package org.example.game.game.enums;

import java.util.Arrays;

public enum GamePlanType {
    //追号类型 1:普通追号 2:同倍追号 3:翻倍追号 4:盈利率追号
    GENERAL(1), DOUBLE(2), PAY_OFF(3), EARNINGS_RATE(4);
    public Integer _value = 1;

    GamePlanType(Integer action) {
        this._value = action;
    }

    public Integer getValue() {
        return _value;
    }

    public static Integer getName(int ret) {
        GamePlanType[] betResult = GamePlanType.values();
        GamePlanType result = Arrays.asList(betResult).stream()
                .filter(i -> i.getValue() == ret)
                .findFirst().orElse(null);
        return result.getValue();
    }

    public static GamePlanType fromGamePlanType(int ret) {
        for (GamePlanType type : GamePlanType.values()) {
            if (type.getValue().equals(ret)) {
                return type;
            }
        }
        return null;
    }
}
