package org.example.vo.dream.financeMain;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;


@Data
public class DreamUFinanceMainSimpleVO {

    /**
     * 用户名
     */
    @TableId
    private Long userId;

    /**
     * 可用资金余额
     */
    private Double moneyUsable;
    /**
     * 冻结资金 --- 提现冻结资金
     */
    private Double moneyFrozen;
    /**
     * 可提现资金余额
     */
    private Double moneyDrawUsable;
    /**
     * 当前用户保证金余额 ---充值暂未通过金额
     */
    private Double margin;

}
