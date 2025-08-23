package org.example.game.game.vo;

import lombok.Data;

@Data
public class GameBetItemReqVO {
    private Long totalAmount;    // 每个注项金额
    private Integer numberRecord; // 下注号码/选项
}
