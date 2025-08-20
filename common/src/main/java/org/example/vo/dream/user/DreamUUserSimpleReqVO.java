package org.example.vo.dream.user;


import lombok.Data;

@Data
public class DreamUUserSimpleReqVO {


    private Long id;

    /**
     * 匿名
     */
//    private String userName;

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
//    private Integer certificationStatus;

    /**
     * 个人简介
     */
//    private String introduction;

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
}
