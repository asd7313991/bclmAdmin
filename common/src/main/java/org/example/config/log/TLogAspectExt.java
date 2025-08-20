package org.example.config.log;


import com.yomahub.tlog.core.annotation.TLogAspect;
import com.yomahub.tlog.core.convert.AspectLogConvert;
import org.example.operatelog.core.enums.OperateTypeEnum;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;


/**
 * The interface T log aspect ext.
 */
@TLogAspect
@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TLogAspectExt {


    /**
     * Value string [ ].
     *
     * @return the string [ ]
     */
    @AliasFor(annotation = TLogAspect.class)
    String[] value() default {};

    /**
     * Str string.
     *
     * @return the string
     */
    @AliasFor(annotation = TLogAspect.class)
    String str() default "";

    /**
     * Joint string.
     *
     * @return the string
     */
    @AliasFor(annotation = TLogAspect.class)
    String joint() default ",";

    /**
     * Pattern string.
     *
     * @return the string
     */
    @AliasFor(annotation = TLogAspect.class)
    String pattern() default "[{}]";

    /**
     * Convert class.
     *
     * @return the class
     */
    @AliasFor(annotation = TLogAspect.class)
    Class<? extends AspectLogConvert> convert() default AspectLogConvert.class;


    /**
     * 操作模块
     *
     * @return string
     */
    String moduleName() default "";

    /**
     * 操作分类
     * <p>
     * 实际并不是数组，因为枚举不能设置 null 作为默认值
     *
     * @return the operate type enum [ ]
     */
    OperateTypeEnum[] type() default {};

    /**
     * 是否记录操作日志
     *
     * @return the boolean
     */
    boolean enable() default true;

    /**
     * 是否记录方法参数
     *
     * @return the boolean
     */
    boolean logArgs() default true;

    /**
     * 是否记录方法结果的数据
     *
     * @return the boolean
     */
    boolean logResultData() default true;

}
