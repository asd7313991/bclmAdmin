package org.example.vo.system.dict.vo.data;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import org.example.enums.DictTypeConstants;
import org.example.excel.core.annotations.DictFormat;
import org.example.excel.core.convert.DictConvert;

/**
 * 字典数据 Excel 导出响应 VO
 */
@Data
public class DictDataExcelVO {

    @ExcelProperty("字典编码")
    private Long id;

    @ExcelProperty("字典排序")
    private Integer sort;

    @ExcelProperty("字典标签")
    private String label;

    @ExcelProperty("字典键值")
    private String value;

    @ExcelProperty("字典类型")
    private String dictType;

    @ExcelProperty(value = "状态", converter = DictConvert.class)
    @DictFormat(DictTypeConstants.COMMON_STATUS)
    private Integer status;

}
