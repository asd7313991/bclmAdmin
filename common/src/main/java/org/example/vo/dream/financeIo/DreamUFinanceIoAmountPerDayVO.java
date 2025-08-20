package org.example.vo.dream.financeIo;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DreamUFinanceIoAmountPerDayVO {

    private Integer type;

    private Double amount = 0d;

    private LocalDate perDate;

    private Long counts;
}
