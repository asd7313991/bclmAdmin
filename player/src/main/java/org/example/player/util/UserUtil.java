package org.example.player.util;

import cn.hutool.extra.spring.SpringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author Boyce 下午4:00:21
 * 本来考虑是否为UserUtil实现存放用户attr的功能，后来考虑分布式情况下，很容易userId和userAttr的失效时间不一致。
 * 且在分布式情况下，很少需要session来工作，故而放弃
 */
@Component("userUtil")
public class UserUtil implements ApplicationContextAware {
    public static final String SESSION_USER_ID_PREFIX = "_session_user:";
    public static final String SESSION_USER_BIND = "_session_user_bind";


    private static ThreadLocal<Integer> userIds = new ThreadLocal<>();


    public static RedisTemplate<String, Object> getRedisTemplate() {
        return   SpringUtil.getBean("redisTemplateByGame",RedisTemplate.class);
    }

    static int expiredSeconds = 7 * 24 * 3600;


    public static Integer getUserId() {
        return userIds.get();
    }


    void recordUser(int userId, String token) {
        putToken(token, userId, false);
    }

    public static void injectToken(String token) {
        Integer userId = parseInteger(getRedisTemplate().opsForValue().get(SESSION_USER_ID_PREFIX + token));
        if (userId != null) {
            putToken(token, userId, true);
        }
    }



    private static void putToken(String token, int userId, boolean flush) {
        String key = SESSION_USER_ID_PREFIX + token;

        userIds.set(userId);

        if (!flush) {
            getRedisTemplate().opsForValue().set(key, userId, expiredSeconds, TimeUnit.SECONDS);

//            if (AuthInteceptor.getServletEntry() != null &&  !IpTools.isPc(AuthInteceptor.getServletEntry().getRequest())) {
            String oldToken = parseString(getRedisTemplate().opsForHash().get(SESSION_USER_BIND, String.valueOf(userId)));

            if (StringUtils.isNotEmpty(oldToken)) {
                getRedisTemplate().delete(SESSION_USER_ID_PREFIX + oldToken);
            }
            getRedisTemplate().opsForHash().put(SESSION_USER_BIND, String.valueOf(userId), token);
//            }

        } else {
            getRedisTemplate().expire(key, expiredSeconds, TimeUnit.SECONDS);

//            if (AuthInteceptor.getServletEntry() != null && !IpTools.isPc(AuthInteceptor.getServletEntry().getRequest())) {
            String oldToken = parseString(getRedisTemplate().opsForHash().get(SESSION_USER_BIND, String.valueOf(userId)));

            if (StringUtils.isEmpty(oldToken) || !oldToken.equals(token)) {
                if (StringUtils.isNotEmpty(oldToken)) {
                    getRedisTemplate().delete(SESSION_USER_ID_PREFIX + oldToken);
                }
                getRedisTemplate().opsForHash().put(SESSION_USER_BIND, String.valueOf(userId), token);
            }
//            }

        }


    }

    private static Integer parseInteger(Object object) {
        if (object == null) {
            return null;
        }
        if (object instanceof Integer) {
            return (Integer) object;
        } else {
            return Integer.parseInt(object.toString());
        }
    }

    private static String parseString(Object object) {
        if (object == null) {
            return null;
        }
        if (object instanceof String) {
            return (String) object;
        } else {
            return object.toString();
        }
    }


    public static void flush() {
        userIds.remove();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        //Ignore
    }
}
