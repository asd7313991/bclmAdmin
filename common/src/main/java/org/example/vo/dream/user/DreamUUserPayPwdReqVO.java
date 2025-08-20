package org.example.vo.dream.user;

import lombok.Data;
import lombok.NonNull;

@Data
public class DreamUUserPayPwdReqVO {

    @NonNull
    private String newPwd;

    @NonNull
    private String newAgainPwd;

}
