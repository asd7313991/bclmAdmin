package org.example.game.game.enums;

import java.util.Arrays;

public enum GamePlanStop {
    //0 不停止，1 盈利停止，2 中奖即停
    NO_STOP(0), STOP_BY_BENIFIT(1), WIN_STOP(2);

    private int _value = 0;

    GamePlanStop(int action) {
        this._value = action;
    }

    public int getValue() {
        return _value;
    }

    public static Integer getName(int ret) {
        GamePlanStop[] betResult = GamePlanStop.values();
        GamePlanStop result = Arrays.asList(betResult).stream()
                .filter(i -> i.getValue() == ret)
                .findFirst().orElse(null);
        return result == null ? null : result.getValue();
    }
}
