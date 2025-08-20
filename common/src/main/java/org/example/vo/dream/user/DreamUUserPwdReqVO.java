package org.example.vo.dream.user;

import lombok.Data;

@Data
public class DreamUUserPwdReqVO {

    private Long id;

    private String oldPwd;

    private String newPwd;

}
