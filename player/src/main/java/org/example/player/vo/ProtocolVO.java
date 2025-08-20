package org.example.player.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProtocolVO {


    /**
     * 协议
     */
    private String protocol;


    private List<VirAddressVO> address;
}
