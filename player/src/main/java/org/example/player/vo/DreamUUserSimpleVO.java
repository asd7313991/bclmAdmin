package org.example.player.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DreamUUserSimpleVO {


    private Long id;

    /**
     * 匿名
     */
    private String userName;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 电话号码
     */
    private String userPhone;


    /**
     * 性别（1是女，2是男）
     */
    private Integer userSex;

    /**
     * 邮箱
     */
    private String userEmail;

    /**
     * 用户头像
     */
    private String userPortrait;

    /**
     * 个性签名
     */
    private String userSign;

    /**
     * 年龄
     */
    private Integer age;
    /**
     * 实名认证状态  1认证 0未认证
     */
    private Integer certificationStatus;

    /**
     * 个人简介
     */
    private String introduction;

    /**
     * 生日
     */
    private String birthday;

    /**
     *
     */
    private String sourceBackup;


    /**
     * 用户状态0正常，1删除，2禁止登录,3禁止交易
     */
    private Integer status;



    /**
     * 可用资金余额
     */
    private BigDecimal moneyUsable;
    /**
     * 冻结资金 --- 提现冻结资金
     */
    private BigDecimal moneyFrozen;
    /**
     * 可提现资金余额
     */
    private BigDecimal moneyDrawUsable;
    /**
     * 当前用户保证金余额 ---充值暂未通过金额
     */
    private BigDecimal margin;

    /**
     * 是否已设置交易密码
     */
    private Boolean payPassword;


    private String tokenName;
    private String tokenValue;

//    private String token1;
//    private String token2;

}
