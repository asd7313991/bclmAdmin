package org.example.game.game.constants;

/**
 * @author wangmeishu
 * @date 2017年5月26日 下午5:41:38
 */
public class CurrencyFlowType extends FinanceType {
    public CurrencyFlowType() {
    }

    ;

    public CurrencyFlowType(Integer type, Integer typeDetail, String remark) {
        super(type, typeDetail, remark);
    }

    /**
     * 虚拟的消费类型 定义为  40-49 .
     * <p>
     * 40 -44 表示 元宝   40 , 兑换成元宝, 41 消费(商品),
     * <p>
     * 50 游戏 51促活活动
     * 45-49 表示 积分   45 , 兑换成积分, 46 消费(商品),
     */


    public static final CurrencyFlowType GOODS_TO_YUANBAO = new CurrencyFlowType(41, 4102, "活动返还元宝");
    public static final CurrencyFlowType YUANBAO_TO_GAME = new CurrencyFlowType(-42, 4201, "期货对战");
    public static final CurrencyFlowType GAME_TO_YUANBAO = new CurrencyFlowType(42, 4202, "期货对战");
    public static final CurrencyFlowType CREDIT_TO_GOODS = new CurrencyFlowType(46, 4601, "积分兑换商品");
    public static final CurrencyFlowType GOODS_TO_CREDIT = new CurrencyFlowType(-46, 4602, "商品返还积分");
    public static final CurrencyFlowType YUANBAO_TO_GOODS = new CurrencyFlowType(-41, 4101, "活动兑换");


    public static final CurrencyFlowType ALIPAY_TO_YUANBAO = new CurrencyFlowType(40, 4001, "充值元宝");
    public static final CurrencyFlowType INNER_DEPOSIT_YUANBAO = new CurrencyFlowType(40, 4002, "系统充值元宝");
    public static final CurrencyFlowType INNER_DRAW_YUANBAO = new CurrencyFlowType(-40, 4003, "系统扣除元宝");
    public static final CurrencyFlowType YUANBAO_TO_CREDIT_BY_Y = new CurrencyFlowType(-41, 4103, "兑换积分");
    public static final CurrencyFlowType YUANBAO_STOCK_CONVER = new CurrencyFlowType(-41, 4106, "模拟交易兑换模拟金");

    public static final CurrencyFlowType REG_GET_YUANBAO = new CurrencyFlowType(40, 4004, "注册赠送");
    public static final CurrencyFlowType SHARE = new CurrencyFlowType(41, 4105, "分享奖金");
    public static final CurrencyFlowType DAY_TASK = new CurrencyFlowType(41, 4104, "每日一题奖励");
    public static final CurrencyFlowType WORSHIP_GOLD = new CurrencyFlowType(44, 4401, "膜拜奖金");
    public static final CurrencyFlowType INVITE_NEW_USER = new CurrencyFlowType(48, 4801, "邀请奖励");

    public static final CurrencyFlowType AWARD_OUT = new CurrencyFlowType(-43, 4301, "打赏支出");
    public static final CurrencyFlowType AWARD_IN = new CurrencyFlowType(43, 4302, "打赏收入");

    public static final CurrencyFlowType ACTIVITY_SKIN_RUSH = new CurrencyFlowType(-47, 4701, "活动支出");
    public static final CurrencyFlowType ACTIVITY_APPLY_EXPEND = new CurrencyFlowType(-47, 4702, "活动报名支出");
    public static final CurrencyFlowType ACTIVITY_APPLY_EXPEND_BACK = new CurrencyFlowType(47, 4703, "退回活动报名费");
    public static final CurrencyFlowType ACTIVITY_EXCHANGE_PRIZE = new CurrencyFlowType(-47, 4704, "活动兑换支出");

    public static final CurrencyFlowType CURRENCY_TO_GAME = new CurrencyFlowType(-50, 5001, "游戏支出");
    public static final CurrencyFlowType GAME_TO_CURRENCY = new CurrencyFlowType(50, 5002, "游戏收入");
    public static final CurrencyFlowType CURRENCY_FREEZE_GAME = new CurrencyFlowType(-50, 5003, "游戏冻结");
    public static final CurrencyFlowType GAME_UNFREEZE_CURRENCY = new CurrencyFlowType(50, 5004, "游戏解冻");
    public static final CurrencyFlowType CURRENCY_FREEZE_GAME_CALLBACK = new CurrencyFlowType(50, 5005, "游戏冻结返还");

    public static final CurrencyFlowType ALIPAY_TO_CREDIT = new CurrencyFlowType(45, 4505, "兑换积分");
    public static final CurrencyFlowType YUANBAO_TO_CREDIT_BY_C = new CurrencyFlowType(45, 4501, "兑换积分");
    public static final CurrencyFlowType INNER_DEPOSIT_CREDIT = new CurrencyFlowType(45, 4502, "系统充值积分");
    public static final CurrencyFlowType INNER_DRAW_CREDIT = new CurrencyFlowType(-45, 4503, "系统扣除积分");
    public static final CurrencyFlowType REG_GET_CREDIT = new CurrencyFlowType(45, 4504, "注册赠送");

    /**
     * 51促活活动
     */
    /**
     * 股票首次买入
     */
    public static final CurrencyFlowType STOCK_FIRST_AWARD = new CurrencyFlowType(51, 5101, "活动奖励");

    /**
     * 股票日常任务奖励
     */
    public static final CurrencyFlowType STOCK_DAILY_AWARD = new CurrencyFlowType(51, 5102, "活动奖励");

    public static final CurrencyFlowType ACTIVITY_PRESENTED_AWARD = new CurrencyFlowType(52, 5201, "活动赠送");

    public static final CurrencyFlowType VIEWPOINT_ADD_MILI = new CurrencyFlowType(40, 4006, "观点收入");


    public enum InOrOut {
        IN(1, "收入"),
        OUT(2, "支出");
        public Integer value;
        public String show;

        private InOrOut(Integer value, String show) {
            this.value = value;
            this.show = show;
        }
    }
}
