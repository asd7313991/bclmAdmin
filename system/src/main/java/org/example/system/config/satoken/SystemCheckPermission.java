package org.example.system.config.satoken;


import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaMode;
import com.yomahub.tlog.core.convert.AspectLogConvert;
import org.example.config.log.TLogAspectExt;
import org.example.config.satoken.StpSystemUtil;
import org.example.operatelog.core.enums.OperateTypeEnum;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The interface System check permission.
 */
@TLogAspectExt()
@SaCheckPermission(type = StpSystemUtil.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.TYPE})
public @interface SystemCheckPermission {

    /**
     * Type string.
     *
     * @return the string
     */
    @AliasFor(annotation = SaCheckPermission.class)
    String type() default "system";

    /**
     * Value string [ ].
     *
     * @return the string [ ]
     */
    @AliasFor(annotation = SaCheckPermission.class)
    String[] value() default {};

    /**
     * Mode sa mode.
     *
     * @return the sa mode
     */
    @AliasFor(annotation = SaCheckPermission.class)
    SaMode mode() default SaMode.AND;

    /**
     * Or role string [ ].
     *
     * @return the string [ ]
     */
    @AliasFor(annotation = SaCheckPermission.class)
    String[] orRole() default {};
//    @AliasFor(annotation = TLogAspect.class)
//    String[] value() default {};


    /**
     * Str string.
     *
     * @return the string
     */
    @AliasFor(annotation = TLogAspectExt.class)
    String str() default "";

    /**
     * Joint string.
     *
     * @return the string
     */
    @AliasFor(annotation = TLogAspectExt.class)
    String joint() default ",";

    /**
     * Pattern string.
     *
     * @return the string
     */
    @AliasFor(annotation = TLogAspectExt.class)
    String pattern() default "[{}]";

    /**
     * Convert class.
     *
     * @return the class
     */
    @AliasFor(annotation = TLogAspectExt.class)
    Class<? extends AspectLogConvert> convert() default AspectLogConvert.class;


    /**
     * 操作模块
     *
     * @return string
     */
    @AliasFor(annotation = TLogAspectExt.class)
    String moduleName() default "";

    /**
     * 操作分类
     * <p>
     * 实际并不是数组，因为枚举不能设置 null 作为默认值
     *
     * @return the operate type enum [ ]
     */
    @AliasFor(annotation = TLogAspectExt.class,attribute = "type")
    OperateTypeEnum[] operateType() default {};

    /**
     * 是否记录操作日志
     *
     * @return the boolean
     */
    @AliasFor(annotation = TLogAspectExt.class)
    boolean enable() default true;

    /**
     * 是否记录方法参数
     *
     * @return the boolean
     */
    @AliasFor(annotation = TLogAspectExt.class)
    boolean logArgs() default true;

    /**
     * 是否记录方法结果的数据
     *
     * @return the boolean
     */
    @AliasFor(annotation = TLogAspectExt.class)
    boolean logResultData() default true;



//    @AliasFor(annotation = TLogAspect.class)
//    String loginId() default "";
}
