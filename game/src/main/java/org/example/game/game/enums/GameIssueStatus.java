package org.example.game.game.enums;

public enum GameIssueStatus {

    /**
     * 奖期状态 0:已生成(M1) 1:开始销售(M2) 2:结束销售(M3) 3:开奖号码确认(M4) 4:计奖完成(M5) 5:验奖完成(M6) 6:派奖完成(M7) 7:奖期结束(M8) 8:对账结束(M9)
     */
    CREATE(0),
    SALE_START(1),
    SALE_END(2),
    ACK_DRAW_RESULT(3),
    /**
     * 计奖
     */
    CALCULATION(4),
    /**
     * 验奖
     */
    VERIFICATION(5),
    /**
     * 派奖
     */
    PRIZE(6),
    /**
     * 奖期结束
     */
    ISSUE_OVER(7),
    /**
     * 对账
     */
    RECONCILIATION(8);

    private int value;

    GameIssueStatus(int action) {
        this.value = action;
    }

    public int getValue() {
        return this.value;
    }
}
