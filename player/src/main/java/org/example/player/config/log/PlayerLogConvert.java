package org.example.player.config.log;

import com.yomahub.tlog.core.convert.AspectLogConvert;
import lombok.extern.slf4j.Slf4j;
import org.example.player.config.sa.StpPlayerUtil;

@Slf4j
public class PlayerLogConvert implements AspectLogConvert {

    @Override
    public String convert(Object[] objects) {
        return "当前登录登录用户ID："+ StpPlayerUtil.getLoginIdAsString();
    }
}
