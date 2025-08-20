package org.example.vo.system.dict.vo;

import lombok.Data;

import java.util.List;

@Data
public class DictDataVO {


    /**
     * 类型
     */
    private String type;

    /**
     * 枚举值
     */
    private List<DictRspVo> value;
}
