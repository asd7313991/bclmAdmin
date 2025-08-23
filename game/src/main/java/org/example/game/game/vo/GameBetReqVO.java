package org.example.game.game.vo;

import lombok.Data;
import java.util.List;


@Data
public class GameBetReqVO {
    private String issueCode;
    private String lotteryId;
    private Long totalAmount;           // 总金额（可与明细核对）
    private List<GameBetItemReqVO> gameItem; // 明细数组
}