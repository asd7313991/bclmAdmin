
package org.example.game.game.constants;

/**
 * Created by yhj on 17/5/10.
 */
public class FinanceStatusType {


    public static final int IO_STATUS_INIT = 0; // 初始状态

    public static final int IO_STATUS_REQUIRE = 1; // 发起请求
    public static final int IO_STATUS_REQUIRE_FAIL = -1; // 发起请求失败


    public static final int IO_STATUS_PAY = 2; // 用户支付成功
    public static final int IO_STATUS_PAY_FAIL = -2; // 用户支付失败

    public static final int TRANSFER_STATUS_WAIT = 1;//待审核
    public static final int TRANSFER_STATUS_PASS = 2; // 审核通过
    public static final int TRANSFER_STATUS_NOTPASS = 3; // 审核不通过
    public static final int TRANSFER_STATUS_INIT = 4; // 提现中
    public static final int TRANSFER_STATUS_SUCCESS = 5; // 提现成功
    public static final int TRANSFER_STATUS_FAIL = 6; // 提现失败

    public static final int DEPOSIT_STATUS_STAR = 1; // 刚刚发起
    public static final int DEPOSIT_STATUS_SUCCESS = 2; // 充值成功
    public static final int DEPOSIT_STATUS_FAIT = 3; // 充值失败


//    \r\n1表示审批通过（提现）\r\n2表示转账中（提现）\r\n3表示支付成功（支付）\r\n4表示支付拒绝（支付）\r\n5表示转账失败（提现）\r\n


}

