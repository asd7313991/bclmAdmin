package org.example.vo.dream.withdraw;


import lombok.Data;

@Data
public class DepositVirAddressReq {


    private Long id;


    /**
     *
     */
    private Long depositTypeId;

    /**
     * 协议
     */
    private String protocol;

    /**
     * 收款地址
     */
    private String address;

    /**
     * 备注
     */
    private String remark;

    /**
     * 最大限额
     */
    private String maxMount;

    /**
     * 排序值
     */
    private Integer sort;

    /**
     *
     */
    private String name;

    /**
     * 收款码
     */
    private String qrImage;

    private String label;


    /**
     *
     */
    private Integer status;
}
