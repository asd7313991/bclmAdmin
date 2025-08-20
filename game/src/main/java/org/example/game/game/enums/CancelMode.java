package org.example.game.game.enums;

/**
 * @author Richard
 * @ClassName: CancelMode
 * @Description: 撤销方式
 * @date 2014-4-1 下午5:17:46
 */
public enum CancelMode {

    DEFAULTS(0), USER(1), SYSTEM(2);

    private int vlaue;

    CancelMode(int value) {
        this.vlaue = value;
    }

    public int getValue() {
        return this.vlaue;
    }

}
