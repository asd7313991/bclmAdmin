package org.example.po.dream.dream;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @TableName dream_u_user
 */
@TableName(value = "dream_u_user")
@Data
public class DreamUUserDO implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
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
     *
     */
    private Date createTime;

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

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        DreamUUserDO other = (DreamUUserDO) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getUserName() == null ? other.getUserName() == null : this.getUserName().equals(other.getUserName()))
                && (this.getUserPhone() == null ? other.getUserPhone() == null : this.getUserPhone().equals(other.getUserPhone()))
                && (this.getUserPass() == null ? other.getUserPass() == null : this.getUserPass().equals(other.getUserPass()))
                && (this.getPassSalt() == null ? other.getPassSalt() == null : this.getPassSalt().equals(other.getPassSalt()))
                && (this.getPassEncryptTimes() == null ? other.getPassEncryptTimes() == null : this.getPassEncryptTimes().equals(other.getPassEncryptTimes()))
                && (this.getLoginErrorNum() == null ? other.getLoginErrorNum() == null : this.getLoginErrorNum().equals(other.getLoginErrorNum()))
                && (this.getLastLoginTime() == null ? other.getLastLoginTime() == null : this.getLastLoginTime().equals(other.getLastLoginTime()))
                && (this.getUserSex() == null ? other.getUserSex() == null : this.getUserSex().equals(other.getUserSex()))
                && (this.getUserEmail() == null ? other.getUserEmail() == null : this.getUserEmail().equals(other.getUserEmail()))
                && (this.getUserPortrait() == null ? other.getUserPortrait() == null : this.getUserPortrait().equals(other.getUserPortrait()))
                && (this.getUserSign() == null ? other.getUserSign() == null : this.getUserSign().equals(other.getUserSign()))
                && (this.getBisSetNickname() == null ? other.getBisSetNickname() == null : this.getBisSetNickname().equals(other.getBisSetNickname()))
                && (this.getAgencyId() == null ? other.getAgencyId() == null : this.getAgencyId().equals(other.getAgencyId()))
                && (this.getPromoterId() == null ? other.getPromoterId() == null : this.getPromoterId().equals(other.getPromoterId()))
                && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
                && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
                && (this.getDeviceId() == null ? other.getDeviceId() == null : this.getDeviceId().equals(other.getDeviceId()))
                && (this.getPlatform() == null ? other.getPlatform() == null : this.getPlatform().equals(other.getPlatform()))
                && (this.getRegistrationIp() == null ? other.getRegistrationIp() == null : this.getRegistrationIp().equals(other.getRegistrationIp()))
                && (this.getLoginIp() == null ? other.getLoginIp() == null : this.getLoginIp().equals(other.getLoginIp()))
                && (this.getSource() == null ? other.getSource() == null : this.getSource().equals(other.getSource()))
                && (this.getLand() == null ? other.getLand() == null : this.getLand().equals(other.getLand()))
                && (this.getIntroduction() == null ? other.getIntroduction() == null : this.getIntroduction().equals(other.getIntroduction()))
                && (this.getBirthday() == null ? other.getBirthday() == null : this.getBirthday().equals(other.getBirthday()))
                && (this.getSourceBackup() == null ? other.getSourceBackup() == null : this.getSourceBackup().equals(other.getSourceBackup()))
                && (this.getWxOpenid() == null ? other.getWxOpenid() == null : this.getWxOpenid().equals(other.getWxOpenid()))
                && (this.getAge() == null ? other.getAge() == null : this.getAge().equals(other.getAge()))
                && (this.getCertificationStatus() == null ? other.getCertificationStatus() == null : this.getCertificationStatus().equals(other.getCertificationStatus()))
                && (this.getLoginNum() == null ? other.getLoginNum() == null : this.getLoginNum().equals(other.getLoginNum()))
                && (this.getLongitude() == null ? other.getLongitude() == null : this.getLongitude().equals(other.getLongitude()))
                && (this.getLatitude() == null ? other.getLatitude() == null : this.getLatitude().equals(other.getLatitude()))
                && (this.getUserType() == null ? other.getUserType() == null : this.getUserType().equals(other.getUserType()))
                && (this.getInviteUserId() == null ? other.getInviteUserId() == null : this.getInviteUserId().equals(other.getInviteUserId()))
                && (this.getMoneyReward() == null ? other.getMoneyReward() == null : this.getMoneyReward().equals(other.getMoneyReward()))
                && (this.getRewardTime() == null ? other.getRewardTime() == null : this.getRewardTime().equals(other.getRewardTime()))
                && (this.getLastSensitizeTime() == null ? other.getLastSensitizeTime() == null : this.getLastSensitizeTime().equals(other.getLastSensitizeTime()))
                && (this.getWxName() == null ? other.getWxName() == null : this.getWxName().equals(other.getWxName()))
                && (this.getRewardDeviceId() == null ? other.getRewardDeviceId() == null : this.getRewardDeviceId().equals(other.getRewardDeviceId()))
                && (this.getCustomId() == null ? other.getCustomId() == null : this.getCustomId().equals(other.getCustomId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUserName() == null) ? 0 : getUserName().hashCode());
        result = prime * result + ((getUserPhone() == null) ? 0 : getUserPhone().hashCode());
        result = prime * result + ((getUserPass() == null) ? 0 : getUserPass().hashCode());
        result = prime * result + ((getPassSalt() == null) ? 0 : getPassSalt().hashCode());
        result = prime * result + ((getPassEncryptTimes() == null) ? 0 : getPassEncryptTimes().hashCode());
        result = prime * result + ((getLoginErrorNum() == null) ? 0 : getLoginErrorNum().hashCode());
        result = prime * result + ((getLastLoginTime() == null) ? 0 : getLastLoginTime().hashCode());
        result = prime * result + ((getUserSex() == null) ? 0 : getUserSex().hashCode());
        result = prime * result + ((getUserEmail() == null) ? 0 : getUserEmail().hashCode());
        result = prime * result + ((getUserPortrait() == null) ? 0 : getUserPortrait().hashCode());
        result = prime * result + ((getUserSign() == null) ? 0 : getUserSign().hashCode());
        result = prime * result + ((getBisSetNickname() == null) ? 0 : getBisSetNickname().hashCode());
        result = prime * result + ((getAgencyId() == null) ? 0 : getAgencyId().hashCode());
        result = prime * result + ((getPromoterId() == null) ? 0 : getPromoterId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getDeviceId() == null) ? 0 : getDeviceId().hashCode());
        result = prime * result + ((getPlatform() == null) ? 0 : getPlatform().hashCode());
        result = prime * result + ((getRegistrationIp() == null) ? 0 : getRegistrationIp().hashCode());
        result = prime * result + ((getLoginIp() == null) ? 0 : getLoginIp().hashCode());
        result = prime * result + ((getSource() == null) ? 0 : getSource().hashCode());
        result = prime * result + ((getLand() == null) ? 0 : getLand().hashCode());
        result = prime * result + ((getIntroduction() == null) ? 0 : getIntroduction().hashCode());
        result = prime * result + ((getBirthday() == null) ? 0 : getBirthday().hashCode());
        result = prime * result + ((getSourceBackup() == null) ? 0 : getSourceBackup().hashCode());
        result = prime * result + ((getWxOpenid() == null) ? 0 : getWxOpenid().hashCode());
        result = prime * result + ((getAge() == null) ? 0 : getAge().hashCode());
        result = prime * result + ((getCertificationStatus() == null) ? 0 : getCertificationStatus().hashCode());
        result = prime * result + ((getLoginNum() == null) ? 0 : getLoginNum().hashCode());
        result = prime * result + ((getLongitude() == null) ? 0 : getLongitude().hashCode());
        result = prime * result + ((getLatitude() == null) ? 0 : getLatitude().hashCode());
        result = prime * result + ((getUserType() == null) ? 0 : getUserType().hashCode());
        result = prime * result + ((getInviteUserId() == null) ? 0 : getInviteUserId().hashCode());
        result = prime * result + ((getMoneyReward() == null) ? 0 : getMoneyReward().hashCode());
        result = prime * result + ((getRewardTime() == null) ? 0 : getRewardTime().hashCode());
        result = prime * result + ((getLastSensitizeTime() == null) ? 0 : getLastSensitizeTime().hashCode());
        result = prime * result + ((getWxName() == null) ? 0 : getWxName().hashCode());
        result = prime * result + ((getRewardDeviceId() == null) ? 0 : getRewardDeviceId().hashCode());
        result = prime * result + ((getCustomId() == null) ? 0 : getCustomId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userName=").append(userName);
        sb.append(", userPhone=").append(userPhone);
        sb.append(", userPass=").append(userPass);
        sb.append(", passSalt=").append(passSalt);
        sb.append(", passEncryptTimes=").append(passEncryptTimes);
        sb.append(", loginErrorNum=").append(loginErrorNum);
        sb.append(", lastLoginTime=").append(lastLoginTime);
        sb.append(", userSex=").append(userSex);
        sb.append(", userEmail=").append(userEmail);
        sb.append(", userPortrait=").append(userPortrait);
        sb.append(", userSign=").append(userSign);
        sb.append(", bisSetNickname=").append(bisSetNickname);
        sb.append(", agencyId=").append(agencyId);
        sb.append(", promoterId=").append(promoterId);
        sb.append(", createTime=").append(createTime);
        sb.append(", status=").append(status);
        sb.append(", deviceId=").append(deviceId);
        sb.append(", platform=").append(platform);
        sb.append(", registrationIp=").append(registrationIp);
        sb.append(", loginIp=").append(loginIp);
        sb.append(", source=").append(source);
        sb.append(", land=").append(land);
        sb.append(", introduction=").append(introduction);
        sb.append(", birthday=").append(birthday);
        sb.append(", sourceBackup=").append(sourceBackup);
        sb.append(", wxOpenid=").append(wxOpenid);
        sb.append(", age=").append(age);
        sb.append(", certificationStatus=").append(certificationStatus);
        sb.append(", loginNum=").append(loginNum);
        sb.append(", longitude=").append(longitude);
        sb.append(", latitude=").append(latitude);
        sb.append(", userType=").append(userType);
        sb.append(", inviteUserId=").append(inviteUserId);
        sb.append(", moneyReward=").append(moneyReward);
        sb.append(", rewardTime=").append(rewardTime);
        sb.append(", lastSensitizeTime=").append(lastSensitizeTime);
        sb.append(", wxName=").append(wxName);
        sb.append(", rewardDeviceId=").append(rewardDeviceId);
        sb.append(", customId=").append(customId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
