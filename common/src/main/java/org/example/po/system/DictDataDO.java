package org.example.po.system;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.po.BaseEntity;

/**
 * 字典数据表
 *
 * @author ruoyi
 */
@TableName("system_dict_data")
@Data
@EqualsAndHashCode(callSuper = true)
public class DictDataDO extends BaseEntity {


    /**
     * 字典排序
     */
    private Integer sort;
    /**
     * 字典标签
     */
    private String label;
    /**
     * 字典值
     */
    private String value;
    /**
     * 字典类型
     * <p>
     * 冗余 {@link DictDataDO#getDictType()}
     */
    private String dictType;
    /**
     * 状态
     * <p>
     * 枚举 {@link CommonStatusEnum}
     */
    private Integer status;
    /**
     * 颜色类型
     * <p>
     * 对应到 element-ui 为 default、primary、success、info、warning、danger
     */
    private String colorType;
    /**
     * css 样式
     */
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private String cssClass;
    /**
     * 备注
     */
    private String remark;

}
