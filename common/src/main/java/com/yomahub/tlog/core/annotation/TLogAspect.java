//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.yomahub.tlog.core.annotation;

import com.yomahub.tlog.core.convert.AspectLogConvert;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface TLogAspect {
    String[] value() default {};

    String str() default "";

    String joint() default ",";

    String pattern() default "[{}]";

    Class<? extends AspectLogConvert> convert() default AspectLogConvert.class;
}
