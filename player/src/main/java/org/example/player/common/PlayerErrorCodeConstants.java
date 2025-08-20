package org.example.player.common;

import org.example.exception.ErrorCode;

public interface PlayerErrorCodeConstants {

    ErrorCode USER_NOT_FOUND = new ErrorCode(800000, "找不到用户");
    ErrorCode USER_NOT_LOGIN = new ErrorCode(800002, "用户未登录");
    ErrorCode PARAMS_IS_UNDEFINED = new ErrorCode(800001, "参数未定义");
    ErrorCode PARAMS_STATUS_IS_NOT_SUPORT_PAY = new ErrorCode(800001, "用户禁止交易中，请联系客服");
    ErrorCode USER_NOT_SET_PAY_PASSWORD =  new ErrorCode(800001, "用户未设置交易密码，请先设置交易密码");




    ErrorCode PAY_IS_ERROR = new ErrorCode(90000,"支付异常");
    ErrorCode DEPOSIT_TYPE_IS_NULL = new ErrorCode(900001,"支付方式获取失败，请刷新页面重新获取");
    ErrorCode DEPOSIT_TYPE_IS_ERROR = new ErrorCode(900003,"支付金额超过配置限制，请重新选择金额");
    ErrorCode DEPOSIT_ADDRESS_IS_NULL = new ErrorCode(900002,"支付方式获取失败，请刷新页面重新获取");
    ErrorCode INSUFFICIENT_BALANCE = new ErrorCode(900004,"余额不足，不能提款");
    ErrorCode INSUFFICIENT_MONEY_USABLE = new ErrorCode(900004,"可提现资金不足，不能提款");
    ErrorCode USER_VIR_ADDRESS_IS_ERROR = new ErrorCode(900005,"提款地址不可用");
}
