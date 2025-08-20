package org.example.vo.dream.financeIo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.po.dream.dream.DreamUFinanceIoDO;
import org.example.util.date.DateUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class DreamUFinanceIoAmountVO {

    private Integer type;

    private Double amount;

    private LocalDateTime[] createTime;
}
