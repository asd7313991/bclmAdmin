package org.example.vo.dream.withdraw;

import lombok.Data;
import org.example.po.dream.dream.DreamUVirAddressDO;
import org.example.vo.dream.deposit.DepositAddressVO;

import java.util.List;
import java.util.Map;


@Data
public class WithdrawTypeVO {


    /**
     * 支付方式ID
     */
    private Long id;

    /**
     * 支付方式编号
     */
    private String code;

    /**
     * 支付方式名称
     */
    private String name;

    /**
     * 支付类型 目前不启用
     */
    private String type;


    /**
     * 支付图标
     */
    private String icon;

    /**
     * 排序值
     */
    private Integer sort;

    /**
     * 教程
     */
    private String teachUrl;

    private WithdrawTypeRuleVO rule;

    /**
     * 二级分类名称
     */
    private String label;

    /**
     *
     */
    private Map<String,List<DreamUVirAddressDO>> address;

}
