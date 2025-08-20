package org.example.game.game.enums;

public enum GamePlanDetailStatus {
    //状态 0:未执行 1:已执行 2:已撤销
    UN_EXEC(0), EXEC(1), CANCEL(2), PAUSE(3);

    private int _value = 0;

    GamePlanDetailStatus(int action) {
        this._value = action;
    }

    public int getValue() {
        return _value;
    }
}