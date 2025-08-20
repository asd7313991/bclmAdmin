package org.example.vo.dream.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;


@Data
public class DreamUUserSimpleVO {
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
     * 电话号码
     */
    private String userPhone;

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
}
