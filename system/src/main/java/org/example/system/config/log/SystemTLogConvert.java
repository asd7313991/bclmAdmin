package org.example.system.config.log;

import com.yomahub.tlog.core.convert.AspectLogConvert;
import lombok.extern.slf4j.Slf4j;
import org.example.config.satoken.StpSystemUtil;

/**
 * The type T log convert.
 */
@Slf4j
public class SystemTLogConvert implements AspectLogConvert {

    @Override
    public String convert(Object[] objects) {
        return "当前登录登录用户ID："+ StpSystemUtil.getLoginIdAsString();
    }
}
