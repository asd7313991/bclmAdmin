package org.example.player.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VirAddressVO {


    private Long id;

    private Long accountId;
    /**
     * 充值或提款 账户
     */
    private String account;
}
