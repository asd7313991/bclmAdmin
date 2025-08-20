package org.example.player.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * @author D588
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DreamUUserPayPwdVO {

    private String oldPwd;

    @NonNull
    private String newPwd;

    @NonNull
    private String newAgainPwd;


}
