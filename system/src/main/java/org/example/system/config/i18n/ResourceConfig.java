package org.example.system.config.i18n;

import org.springframework.context.annotation.PropertySource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;


/**
 * The type Resource config.
 */
@PropertySource(value = "classpath:i18n/message*.properties")
public class ResourceConfig {

    private static final Map<String, ResourceBundle> MESSAGES = new HashMap<>();


    /**
     * Gets message.
     *
     * @param key    the key
     * @param params the params
     * @return the message
     */
    public static String getMessage(String key, Object params) {
        // 从请求头拿到Accept_language中对应的地区信息
        Locale locale = LocaleContextHolder.getLocale();
        ResourceBundle message = MESSAGES.get(locale.getLanguage());
        if (message == null) {
            synchronized (MESSAGES) {
                message = ResourceBundle.getBundle("/i18n/messages", locale);
                MESSAGES.put(locale.getLanguage(), message);
            }
        }
        if (params != null) {
            return String.format(message.getString(key), params);
        }
        return message.getString(key);
    }


    /**
     * Flush message.
     */
    public void flushMessage() {
        MESSAGES.clear();
    }


}
