package org.example.vo.dream.virAddress;

import lombok.Data;

@Data
public class AddVirAddressReq {


    private Long virAddressId;
    /**
     * 地址名称
     */
    private String name;

    /**
     * 虚拟币币种
     */
    private String currency;

    /**
     * 虚拟币协议
     */
    private String protocol;

    /**
     * 虚拟币地址
     */
    private String address;


    /**
     * 是否默认 0：非默认，1：默认
     */
    private Integer defaulted;

    private String password;
}
