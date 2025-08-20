package org.example.vo.hf.gameIssue;


import lombok.Data;

import java.util.List;

@Data
public class KenoDrawThirdDTO {

    private Integer drawNbr;
    private String drawDate;
    private String drawTime;
    private List<Integer> drawNbrs;
    private Integer drawBonus;
}
