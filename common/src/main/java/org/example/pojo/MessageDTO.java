package org.example.pojo;

import lombok.Data;

import java.util.Map;

@Data
public class MessageDTO {
    private Long userId;
    private String templateCode;

    private Map<String,Object> extraParameters;
}
