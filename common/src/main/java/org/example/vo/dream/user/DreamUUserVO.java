package org.example.vo.dream.user;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.po.dream.dream.DreamUUserDO;

@EqualsAndHashCode(callSuper = true)
@Data
public class DreamUUserVO extends DreamUUserDO {


    private String password;
    private String againPassword;
    private Integer type;

    private String userId;


    private Long id;


}
