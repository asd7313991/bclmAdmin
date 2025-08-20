package org.example.player.config;


import org.example.player.util.RedisJsonSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {


    @Bean("redisTemplateByGame")
//    @ConditionalOnMissingBean(name = "redisTemplate")
    public RedisTemplate<String, Object> redisTemplateByGame(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);
        //String的序列化方式
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();

        RedisJsonSerializer redisJsonSerializer = new RedisJsonSerializer();
        //key序列化方式采用String类型
        template.setKeySerializer(stringRedisSerializer);
        //value序列化方式采用jackson类型
        template.setValueSerializer(redisJsonSerializer);
        //hash的key序列化方式也是采用String类型
        template.setHashKeySerializer(stringRedisSerializer);
        //hash的value也是采用jackson类型
        template.setHashValueSerializer(redisJsonSerializer);
        template.afterPropertiesSet();
        return template;
    }

}
