package org.example.vo.system.dict.vo;

import lombok.Data;

import java.util.List;

@Data
public class DictReq {


    private List<String> dictCodes;

    private String dictCodesStr;
}
