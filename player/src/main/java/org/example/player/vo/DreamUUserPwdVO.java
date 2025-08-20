package org.example.player.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DreamUUserPwdVO {

    private Long id;

    @NonNull
    private String oldPwd;

    @NonNull
    private String newPwd;

    @NonNull
    private String newAgainPwd;

}
