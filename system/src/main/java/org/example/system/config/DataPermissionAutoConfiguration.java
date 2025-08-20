package org.example.system.config;

import org.example.system.config.aop.DataPermissionAnnotationAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;


/**
 * The type Data permission auto configuration.
 */
@Configuration
public class DataPermissionAutoConfiguration {

    /**
     * Data permission rule factory data permission rule factory.
     *
     * @param rules the rules
     * @return the data permission rule factory
     */
    @Bean
    public DataPermissionRuleFactory dataPermissionRuleFactory(List<DataPermissionRule> rules) {
        return new DataPermissionRuleFactoryImpl(rules);
    }

//    @Bean
//    public DataPermissionDatabaseInterceptor dataPermissionDatabaseInterceptor(MybatisPlusInterceptor interceptor,
//                                                                               DataPermissionRuleFactory ruleFactory) {
//        // 创建 DataPermissionDatabaseInterceptor 拦截器
//        DataPermissionDatabaseInterceptor inner = new DataPermissionDatabaseInterceptor(ruleFactory);
//        // 添加到 interceptor 中
//        // 需要加在首个，主要是为了在分页插件前面。这个是 MyBatis Plus 的规定
//        MyBatisUtils.addInterceptor(interceptor, inner, 0);
//        return inner;
//    }

    /**
     * Data permission annotation advisor data permission annotation advisor.
     *
     * @return the data permission annotation advisor
     */
    @Bean
    public DataPermissionAnnotationAdvisor dataPermissionAnnotationAdvisor() {
        return new DataPermissionAnnotationAdvisor();
    }

}
