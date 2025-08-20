package org.example.exception.enums;


import org.example.exception.ErrorCode;

/**
 * 全局错误码枚举
 * 0-999 系统异常编码保留
 * <p>
 * 一般情况下，使用 HTTP 响应状态码 https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Status
 * 虽然说，HTTP 响应状态码作为业务使用表达能力偏弱，但是使用在系统层面还是非常不错的
 * 比较特殊的是，因为之前一直使用 0 作为成功，就不使用 200 啦。
 *
 * @author
 */
public interface GlobalErrorCodeConstants {

    ErrorCode SUCCESS = new ErrorCode(200, "成功");

    // ========== 客户端错误段 ==========

    ErrorCode BAD_REQUEST = new ErrorCode(400, "请求参数不正确");
    ErrorCode UNAUTHORIZED = new ErrorCode(401, "账号未登录");
    ErrorCode FORBIDDEN = new ErrorCode(403, "没有该操作权限");
    ErrorCode NOT_FOUND = new ErrorCode(404, "请求未找到");
    ErrorCode METHOD_NOT_ALLOWED = new ErrorCode(405, "请求方法不正确");
    ErrorCode LOCKED = new ErrorCode(423, "请求失败，请稍后重试"); // 并发请求，不允许
    ErrorCode TOO_MANY_REQUESTS = new ErrorCode(429, "请求过于频繁，请稍后重试");

    // ========== 服务端错误段 ==========

    ErrorCode INTERNAL_SERVER_ERROR = new ErrorCode(500, "系统异常");
    ErrorCode NOT_IMPLEMENTED = new ErrorCode(501, "功能未实现/未开启");

    // ========== 自定义错误段 ==========
    ErrorCode REPEATED_REQUESTS = new ErrorCode(900, "重复请求，请稍后重试"); // 重复请求

    ErrorCode UNKNOWN = new ErrorCode(999, "未知错误");


    ErrorCode NOT_FOUND_DATA = new ErrorCode(998, "数据未找到");

    ErrorCode LOGIN_PWD_NOT_SAME = new ErrorCode(900002, "用户旧登陆密码输入错误");
    ErrorCode CONFIRM_PWD_NOT_SAME = new ErrorCode(900002, "确认密码不一致");
    ErrorCode REGISTER_ADD_INFO_FAIL = new ErrorCode(900003, "注册添加新用户失败");
    ErrorCode USER_NOT_LOGIN = new ErrorCode(900004, "用户未登录");
    ErrorCode USER_HAS_EXIT= new ErrorCode(900005, "用户名已存在");
    ErrorCode USER_STATUS_IS_WARN = new ErrorCode(900007, "用户登录状态异常,请联系客服");
    ErrorCode USER_STATUS_IS_WARN_NOT_FINANCE = new ErrorCode(900007, "用户交易状态异常,不允许操作资金");


    ErrorCode PASSWORD_IS_ERROR = new ErrorCode(90006,"密码错误,请重新输入");



    ErrorCode USER_NOT_FOUND = new ErrorCode(800000, "找不到用户");
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


    ErrorCode USER_IS_DISABLE = new ErrorCode(900006, "用户禁止提款，请联系客服");

}
