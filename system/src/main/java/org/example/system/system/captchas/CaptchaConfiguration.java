package org.example.system.system.captchas;

import com.xingyuv.captcha.properties.AjCaptchaProperties;
import com.xingyuv.captcha.service.CaptchaCacheService;
import com.xingyuv.captcha.service.impl.CaptchaServiceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;

/**
 * The type Captcha configuration.
 */
public class CaptchaConfiguration {


    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * Captcha cache service captcha cache service.
     *
     * @param config the config
     * @return the captcha cache service
     */
    @Bean
    public CaptchaCacheService captchaCacheService(AjCaptchaProperties config) {
        // 缓存类型 redis/local/....
        CaptchaCacheService ret = CaptchaServiceFactory.getCache(config.getCacheType().name());
        if (ret instanceof RedisCaptchaServiceImpl) {
            ((RedisCaptchaServiceImpl) ret).setStringRedisTemplate(stringRedisTemplate);
        }
        return ret;
    }

}
