package org.example.system.db.mysql.po.system;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import org.example.po.BaseEntity;

/**
 * The type Banner do.
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value = "system_banner", autoResultMap = true) // 由于 SQL Server 的 system_user 是关键字，所以使用 system_users
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BannerDO extends BaseEntity {


    /**
     * 标题
     */
    private String title;
    /**
     * 跳转链接
     */
    private String url;
    /**
     * 图片链接
     */
    private String picUrl;
    /**
     * 排序
     */
    private Integer sort;

    /**
     * 状态 {@link cn.iocoder.yudao.framework.common.enums.CommonStatusEnum}
     */
    private Integer status;
    /**
     * 备注
     */
    private String remark;

}
