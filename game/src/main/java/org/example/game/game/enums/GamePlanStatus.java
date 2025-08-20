package org.example.game.game.enums;

public enum GamePlanStatus {
    //状态 0:可执行 1:用户终止 2:系统终止
//	executable(0), user(1), system(2);
    EXECUTABLE(0), WAITING(1), FINISH(2), STOP(3), PAUSE(4), UN_EXEC(-1);

    private int _value = 0;

    GamePlanStatus(int action) {
        this._value = action;
    }

    public int getValue() {
        return _value;
    }
}
