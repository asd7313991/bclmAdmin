package org.example.player.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthReqVO {

    /**
     * 用户名
     */
    private String uname;
    /**
     * 密码
     */
    private String password;

    /**
     * 验证吗
     */
    private String code;


    private Integer type;

}
