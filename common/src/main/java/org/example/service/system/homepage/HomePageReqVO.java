package org.example.service.system.homepage;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class HomePageReqVO {
    private Integer days;

    private String type;

    private LocalDateTime[] createTime;
}
