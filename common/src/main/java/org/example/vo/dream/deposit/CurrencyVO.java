package org.example.vo.dream.deposit;

import lombok.Data;

import java.util.List;

@Data
public class CurrencyVO {


    /**
     * 币种
     */
    private String currency;

    /**
     *  预留扩展参数
     */
    private String params;

    /**
     * 协议
     */
//    private List<ProtocolVO> protocols;

}
