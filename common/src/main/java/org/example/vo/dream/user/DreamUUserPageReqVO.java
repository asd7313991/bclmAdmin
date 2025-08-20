package org.example.vo.dream.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.util.date.DateUtils;
import org.example.vo.base.PageReqVO;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;


@EqualsAndHashCode(callSuper = true)
@Data
public class DreamUUserPageReqVO extends PageReqVO {


    @DateTimeFormat(pattern = DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @JsonFormat(pattern = DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;


    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 匿名
     */
    private String userName;

    /**
     * 电话号码
     */
    private String userPhone;

    /**
     * 用户密码
     */
    private String userPass;

    /**
     * 盐
     */
    private String passSalt;

    /**
     * 加密次数
     */
    private Integer passEncryptTimes;

    /**
     * 密码登陆输入错误次数
     */
    private Integer loginErrorNum;

    /**
     * 登陆时间
     */
    private Date lastLoginTime;

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
     * 用户是否已设置昵称
     */
    private Integer bisSetNickname;

    /**
     *
     */
    private Integer agencyId;

    /**
     * 推广员id
     */
    private Integer promoterId;

    /**
     * 用户状态0正常，1删除，2禁止登录,3禁止交易
     */
    private Integer status;

    /**
     * 设备ID
     */
    private String deviceId;

    /**
     * 设备标识0安卓,1IOS
     */
    private Integer platform;

    /**
     * 注册ip
     */
    private String registrationIp;

    /**
     * 登录ip
     */
    private String loginIp;

    /**
     * 渠道来源
     */
    private String source;

    /**
     * 用户所在地
     */
    private String land;

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
     * 用户微信openId
     */
    private String wxOpenid;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 实名认证状态  1认证 0未认证
     */
    private Integer certificationStatus;

    /**
     * 登录次数
     */
    private Integer loginNum;

    /**
     *
     */
    private Double longitude;

    /**
     *
     */
    private Double latitude;

    /**
     * 用户类型 0真实用户  1虚拟用户
     */
    private Integer userType;

    /**
     * 邀请人id
     */
    private Integer inviteUserId;

    /**
     * 是否已经奖赏 0,未奖赏,1,已经奖赏
     */
    private Integer moneyReward;

    /**
     * 奖励时间
     */
    private Date rewardTime;

    /**
     * 最后激活APP时间
     */
    private Date lastSensitizeTime;

    /**
     * 微信昵称
     */
    private String wxName;

    /**
     * 第一次登录送元宝的设备id
     */
    private String rewardDeviceId;

    /**
     * 用户拓展小姐姐id
     */
    private Integer customId;
}
