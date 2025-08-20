package org.example.game.game.constants;

/**
 * Created by yhj on 17/6/17.
 */
public class BattleType {

    public static final Integer BATTLE_FUTURE_TYPE = 1;

    public static final Integer BATTLE_FUTURE_ACTI_TYPE = 2;


    /**
     * 对战状态
     */
    public static final Integer BATTLE_STATUS_CANCEL = 0; //游戏被取消

    public static final Integer BATTLE_STATUS_LAUNCH = 1; //发起
    public static final Integer BATTLE_STATUS_START = 2;   // 开始

    public static final Integer BATTLE_STATUS_END = 3; /// 结束


    public static final Integer PUSH_BATTLE_CREATE = 11;// 对战被创建 了
    public static final Integer PUSH_BATTLE_JOIN = 12;// 对战有人加入
    public static final Integer PUSH_BATTLE_END = 13; // 对战结束
    public static final Integer PUSH_BATTLE_CANCEL = 14; // 对战取消

    public static final Integer PUSH_ORDER_CREATE = 21; // 创建一个订单
    public static final Integer PUSH_ORDER_UNWIND = 22;// 订单平仓

    public static final Integer PUSH_QUICK_MATCH = 31;// 快速匹配成功
    public static final Integer PUSH_QUICK_MATCH_FAIL = 32;// 快速匹配失败

    public static final Integer PUSH_QUICK_MATCH_TIMEOUT = 33;// 快速匹配超时.


    public static final Integer PUSH_CREATE_TIMEOUT = 41; // 房间创建超时.

    public static final Integer PUSH_USER_PRAISE = 51; // 用户点赞.


    public static final int DIRECTION_UP = 1;
    public static final int DIRECTION_DOWN = 0;

    public static final int DIRECTION_NONE = -1;   //不操作


    public static final int BATTLE_ORDER_UNWIND_TYPE_USER = 1;
    public static final int BATTLE_ORDER_UNWIND_TYPE_TIMEOUT = 2;


    public static final int BATTLE_RESULT_DRAW = 0; // 平局
    public static final int BATTLE_RESULT_LAUNCH = 1;// 发起者赢
    public static final int BATTLE_RESULT_AGAINST = 2; // 应战者赢


    public static final Integer BATTLE_MATCH_TYPE_NORMAL = 1;
    public static final Integer BATTLE_MATCH_TYPE_AGAINST_AI = 2; // 应战者是AI
    public static final Integer BATTLE_MATCH_TYPE_LAUNCH_AI = 3; //发起者AI


    //    订单状态-1：失败：0:待支付，1：已支付，待持仓 2：持仓中 3 ：平仓处理中 4：结算完成
    public enum OrderStatus {
        FAIL(-1, "失败"), PAY_READY(0, "待支付"), PAY_FINISH(1, "已支付，待持仓"), TASK_POSTION(2, "持仓中"), RISK_HAND(3, "平仓处理中"), FINISH(4, "结算完成");

        public String name;
        public Integer value;

        OrderStatus(Integer value, String name) {
            this.name = name;
            this.value = value;
        }
    }


    public enum OrderOptStatus {

        BUY_UP_CREATE(1, "BUY_UP_CREATE"),
        BUY_DOWN_CREATE(2, "BUY_DOWN_CREATE"),


        BUY_UP_CLOSE(3, "BUY_UP_CLOSE"),
        BUY_DOWN_CLOSE(4, "BUY_DOWN_CLOSE"),
        ;

        public String name;
        public Integer value;

        OrderOptStatus(Integer value, String name) {
            this.name = name;
            this.value = value;
        }


        public static int reverse(int optStatus) {
            if (optStatus == BUY_UP_CREATE.value) {
                return BUY_DOWN_CREATE.value;
            }
            if (optStatus == BUY_DOWN_CREATE.value) {
                return BUY_UP_CREATE.value;
            }

            if (optStatus == BUY_UP_CLOSE.value) {
                return BUY_DOWN_CLOSE.value;
            }
            if (optStatus == BUY_DOWN_CLOSE.value) {
                return BUY_UP_CLOSE.value;
            }

            throw new IllegalArgumentException("can't found the opt status ");
        }


        public static OrderOptStatus valueOf(int direction, boolean create) {

            if (direction == DIRECTION_UP) {
                if (create) {
                    return BUY_UP_CREATE;
                } else {
                    return BUY_UP_CLOSE;
                }
            } else {
                if (create) {
                    return BUY_DOWN_CREATE;
                } else {
                    return BUY_DOWN_CLOSE;
                }
            }
        }

    }


    public enum CronType {

        Money(1, "money", "现金战"), Gold(2, "gold", "元宝战"), Integral(3, "integral", "积分战");

        public String name;
        public Integer type;
        public String show;

        CronType(Integer type, String name, String show) {
            this.name = name;
            this.show = show;
            this.type = type;
        }

    }
}
