package org.example.vo.dream.user;

import lombok.Data;
import lombok.NonNull;

@Data
public class DreamUUserLoginPwdReqVO {

    private Long id;

    @NonNull
    private String oldPwd;

    @NonNull
    private String newPwd;

    @NonNull
    private String newAgainPwd;

}
