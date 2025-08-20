
package org.example.game.game.constants;

/**
 * Created by yhj on 17/5/10.
 */
public class FinanceFlowType extends FinanceType {

    public FinanceFlowType() {
    }

    ;

    public FinanceFlowType(Integer type, Integer typeDetail, String remark) {
        super(type, typeDetail, remark);
    }


    /**
     * 20 - 29 为支付 , 提现 类型
     */
    public static final FinanceFlowType LOAN_PAY_IN = new FinanceFlowType(-21, 2100, "支付意向金");
    public static final FinanceFlowType FEE_PAY_IN = new FinanceFlowType(-21, 2102, "支付提现手续费");

    public static final FinanceFlowType MAIM_TRANSFER_REFUSAL = new FinanceFlowType(22, 2207, "提现失败(返还)");
    public static final FinanceFlowType MAIM_PAY_OUT = new FinanceFlowType(-22, 2201, "提现");
    public static final FinanceFlowType MAIM_INSIDE_OUT = new FinanceFlowType(-22, 2203, "内部提现");
    public static final FinanceFlowType MAIM_INSIDE_QTOUT = new FinanceFlowType(-22, 2204, "钱通提现");
    public static final FinanceFlowType MAIM_INSIDE_ALOUT = new FinanceFlowType(-22, 2205, "人工提现");
    public static final FinanceFlowType MAIM_TRANSFER_FROZEN = new FinanceFlowType(-22, 2206, "提现冻结");


    public static final FinanceFlowType AWARD = new FinanceFlowType(-21, 2103, "打赏");

    public static final FinanceFlowType MAIM_PAY_IN = new FinanceFlowType(22, 2200, "充值");
    public static final FinanceFlowType MAIM_INSIDE_IN = new FinanceFlowType(22, 2202, "系统赠送");
    /**
     * 消费类型. 30-39 消费.
     */
    public static final FinanceFlowType CONVET_YUANBAO = new FinanceFlowType(-29, 2900, "充值元宝");
    public static final FinanceFlowType CONVET_CREDIT = new FinanceFlowType(-29, 2901, "充值积分");

    public static final FinanceFlowType CASH_APPLY_ACTIVITY = new FinanceFlowType(-30, 3001, "活动报名");
    public static final FinanceFlowType CASH_APPLY_ACTIVITY_BACK = new FinanceFlowType(30, 3002, "活动报名退费");

    public static final FinanceFlowType CASH_EXCHANGE_ACTIVITY_PRIZE = new FinanceFlowType(-30, 3003, "活动兑奖");


    public static final FinanceFlowType VIEWPOINT_ADD_MONEY = new FinanceFlowType(22, 2208, "观点收入");


//    /**
//     * 佣金处理 类型
//     */
//    public static final FinanceFlowType COMMISSION_IN = new FinanceFlowType(15, 1501, "佣金存入");
//
//    public static final FinanceFlowType COMMISSION_OUT = new FinanceFlowType(-15, -1501, "佣金转出");
//
//    public static final FinanceFlowType COMMISSION_CARRYOVER = new FinanceFlowType(16, 1501, "佣金转余额");
//
//
//    /**
//     * 用户主动类型
//     */
//    public static final FinanceFlowType CONSUME_COMMISSION_PAY = new FinanceFlowType(21, -2101, "支付手续费");
//
//    public static final FinanceFlowType CONSUME_MARGIN_FROZEN = new FinanceFlowType(21, -2102, "冻结保证金");
//
//    /**
//     * 结算类型
//     */
//    public static final FinanceFlowType SETTLE_MARGIN_RETURN = new FinanceFlowType(22, 2201, "返还保证金");
//
//    public static final FinanceFlowType SETTLE_PORFIT_INCREASE = new FinanceFlowType(22, 2202, "收益增加");
//
//    public static final FinanceFlowType SETTLE_PORFIT_DECREASE = new FinanceFlowType(22, -2202, "收益减少");
//
//
//    /**
//     * 退换类型
//     */
//    public static final FinanceFlowType CHARGEBACK_COMMISSION_RETURN = new FinanceFlowType(23, 2301, "返还手续费");
//
//    public static final FinanceFlowType CHARGEBACK_MARGIN_RETURN = new FinanceFlowType(23, 2302, "返还保证金");
//
//
//    /**
//     * 系统内部类型
//     */
//    public static final FinanceFlowType INNER_DEPOSIT_MONEY = new FinanceFlowType(31, 3101, "内部存入");
//
//    public static final FinanceFlowType INNER_EXTRACT_MONEY = new FinanceFlowType(31, -3101, "内部取出");
//
//    public static final FinanceFlowType INNER_EXTRACT_MONEY_REFUSE = new FinanceFlowType(31, 3102, "内部取出拒绝");
//
//    public static final FinanceFlowType INNER_DEPOSIT_SCORE = new FinanceFlowType(32, 3201, "内部存入");
//
//    public static final FinanceFlowType INNER_EXTRACT_SCORE = new FinanceFlowType(32, -3202, "内部取出");
//
//    public static final FinanceFlowType INNER_EXTRACT_SCORE_REFUSE = new FinanceFlowType(32, 3202, "内部取出拒绝");
//
//    public static final FinanceFlowType INNER_MAKE_MONEY = new FinanceFlowType(41, 4101, "系统补单");
//
//    public static final FinanceFlowType INNER_REGISTER_DEPOSIT_SCORE = new FinanceFlowType(42, 4201, "注册赠送");
//
//    /**
//     * 商品类型
//     */
//    public static final FinanceFlowType SHOPPING_PURCHASE_SCORE = new FinanceFlowType(71, -7101, "兑换金币");
//
//    public static final FinanceFlowType SHOPPING_EXCHANGE_SCORE = new FinanceFlowType(72, 7201, "资金兑换");
//
//    public static final FinanceFlowType SHOPPING_PURCHASE_GOODS = new FinanceFlowType(72, -7201, "兑换商品");
//
//    public static final FinanceFlowType SHOPPING_PURCHASE_REFUSE = new FinanceFlowType(72, 7202, "拒绝兑换");
//
//    public static final FinanceFlowType SHOPPING_RECOVER_SCORE = new FinanceFlowType(72, 7203, "获取金币");


    public static final FinanceFlowType HF_BUY_GOODS = new FinanceFlowType(82, -8201, "购买商品");

    public static final FinanceFlowType HF_AWARD_GOODS = new FinanceFlowType(82, 8202, "获得奖品");

    public static final FinanceFlowType HF_FROZEN_GOODS = new FinanceFlowType(83, -8301, "追号冻结");

    public static final FinanceFlowType HF_UNFROZEN_GOODS = new FinanceFlowType(83, 8302, "追号解冻");

    public static final FinanceFlowType HF_UNFROZEN_RUTRUN_GOODS = new FinanceFlowType(83, 8303, "追号返还");

    public static final FinanceFlowType HF_PRZIE_GOODS = new FinanceFlowType(84, 8401, "商品中奖");


}

