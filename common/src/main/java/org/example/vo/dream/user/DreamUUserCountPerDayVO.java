package org.example.vo.dream.user;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DreamUUserCountPerDayVO {
    private Integer type;

    private LocalDate perDate;

    private Long counts;
}
