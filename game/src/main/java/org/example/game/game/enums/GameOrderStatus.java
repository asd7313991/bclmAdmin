package org.example.game.game.enums;

public enum GameOrderStatus {
    WAITING(1), PRIZE(2), UN_PRIZE(3), CANCEL(4), ERROR(5), ARCHIVING(6), AUDITWAIT(8), AUDITFAIL(7), AUDITSUCC(9);

    private int _value = 0;

    GameOrderStatus(int action) {
        this._value = action;
    }

    public int getValue() {
        return _value;
    }
}
