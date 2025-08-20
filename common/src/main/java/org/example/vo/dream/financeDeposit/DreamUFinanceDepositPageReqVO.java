package org.example.vo.dream.financeDeposit;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.util.date.DateUtils;
import org.example.vo.base.PageReqVO;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
public class DreamUFinanceDepositPageReqVO extends PageReqVO {

    /**
     * 充值id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 充值类型1微信充值2支付宝充值3银行卡充值
     */
    private Integer type;

    /**
     * 描述
     */
    private String remark;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 充值金额
     */
    private Double money;

    /**
     * 内部订单id
     */
    private String submitOrderId;

    /**
     * 流水id
     */
    private Integer ioId;

    /**
     * 回调描述
     */
    private String comments;

    /**
     * 1刚刚发起 2充值成功 3充值失败  4交易不存在
     */
    private Integer status;


    @DateTimeFormat(pattern = DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @JsonFormat(pattern = DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @DateTimeFormat(pattern = DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @JsonFormat(pattern = DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    /**
     * 创建时间
     */
    private LocalDateTime[] updateTime;

}
