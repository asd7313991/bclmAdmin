//package org.example.player.config.log;
//
//
//import com.yomahub.tlog.core.annotation.TLogAspect;
//import com.yomahub.tlog.core.convert.AspectLogConvert;
//import org.example.operatelog.core.enums.OperateTypeEnum;
//import org.springframework.core.annotation.AliasFor;
//
//import java.lang.annotation.*;
//
//
//@TLogAspect
//@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
//@Retention(RetentionPolicy.RUNTIME)
//@Documented
//public @interface TLogAspectExt {
//
//
//    @AliasFor(annotation = TLogAspect.class)
//    String[] value() default {};
//
//    @AliasFor(annotation = TLogAspect.class)
//    String str() default "";
//
//    @AliasFor(annotation = TLogAspect.class)
//    String joint() default ",";
//
//    @AliasFor(annotation = TLogAspect.class)
//    String pattern() default "[{}]";
//
//    @AliasFor(annotation = TLogAspect.class)
//    Class<? extends AspectLogConvert> convert() default AspectLogConvert.class;
//
//
//    /**
//     * 操作模块
//     *
//     * @return
//     */
//    String moduleName() default "";
//
//    /**
//     * 操作分类
//     * <p>
//     * 实际并不是数组，因为枚举不能设置 null 作为默认值
//     */
//    OperateTypeEnum[] type() default {};
//
//    /**
//     * 是否记录操作日志
//     */
//    boolean enable() default true;
//
//    /**
//     * 是否记录方法参数
//     */
//    boolean logArgs() default true;
//
//    /**
//     * 是否记录方法结果的数据
//     */
//    boolean logResultData() default true;
//
//}
