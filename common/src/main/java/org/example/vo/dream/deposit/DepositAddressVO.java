package org.example.vo.dream.deposit;


import lombok.Data;

/**
 * 收款地址
 */
@Data
public class DepositAddressVO {


    /**
     * addressId
     */
    private Long id;
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

    /**
     * 支付说明
     */
    private String label;

}
