package org.example.vo.dream.financeAcc;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.util.date.DateUtils;
import org.example.vo.base.PageReqVO;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;


@EqualsAndHashCode(callSuper = true)
@Data
public class DreamUFinanceAccReqVo extends PageReqVO {

    /**
     * 用户id,用用户表中的主键id作为本表中的userid
     */
    private Integer userId;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 身份证号
     */
    private String idCard;

    /**
     * 账户实名认证状态，0：未认证；1：已填写；2：已认证
     */
    private Integer idStatus;

    /**
     *
     */
    private Integer bankId;

    /**
     * 发卡行标识
     */
    private Integer issuingBank;

    /**
     * 发卡行名字
     */
    private String issuingBankName;

    /**
     * 银行卡号
     */
    private String cardNumber;

    /**
     * 银行卡绑定的手机号
     */
    private String cardPhone;

    /**
     * 银行卡的认证状态，0姓名、身份证已填写；1银行卡已填写；2已认证
     */
    private Integer cardState;


    @DateTimeFormat(pattern = DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @JsonFormat(pattern = DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;
    /**
     *
     */
    private Date updateTime;

    /**
     * 银行卡状态  0未绑定  1已填写  2已绑定
     */
    private Integer bindStatus;

    /**
     * 备注
     */
    private String remark;

}
