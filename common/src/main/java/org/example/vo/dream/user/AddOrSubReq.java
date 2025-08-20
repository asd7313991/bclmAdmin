package org.example.vo.dream.user;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AddOrSubReq {


    private Integer type;
    private BigDecimal money;


    private Long userId;

    private Long orderId;

    private String remark;


}
