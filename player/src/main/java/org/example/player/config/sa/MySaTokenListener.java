package org.example.player.config.sa;

import cn.dev33.satoken.listener.SaTokenListener;
import cn.dev33.satoken.stp.SaLoginModel;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MySaTokenListener implements SaTokenListener {

    /** 每次登录时触发 */
    @Override
    public void doLogin(String loginType, Object loginId, String tokenValue, SaLoginModel loginModel) {
       log.info("---------- 自定义侦听器实现 每次登录时触发 doLogin,loginType:{},loginId:{},tokenValue:{},loginModel:{}",loginType,loginId.toString(),tokenValue, JSONUtil.toJsonStr(loginModel));
    }

    /** 每次注销时触发 */
    @Override
    public void doLogout(String loginType, Object loginId, String tokenValue) {
       log.info("---------- 自定义侦听器实现 每次注销时触发 doLogout ,loginType:{},loginId:{},tokenValue:{}",loginType,loginId.toString(),tokenValue);
    }

    /** 每次被踢下线时触发 */
    @Override
    public void doKickout(String loginType, Object loginId, String tokenValue) {
       log.info("---------- 自定义侦听器实现 每次被踢下线时触发 doKickout,loginType:{},loginId:{},tokenValue:{}",loginType,loginId.toString(),tokenValue);
    }

    /** 每次被顶下线时触发 */
    @Override
    public void doReplaced(String loginType, Object loginId, String tokenValue) {
       log.info("---------- 自定义侦听器实现 每次被顶下线时触发 doReplaced,loginType:{},loginId:{},tokenValue:{}",loginType,loginId.toString(),tokenValue);
    }

    /** 每次被封禁时触发 */
    @Override
    public void doDisable(String loginType, Object loginId, String service, int level, long disableTime) {
        log.info("---------- 自定义侦听器实现 doDisable,loginType:{},loginId:{},service:{},level:{},disableTime:{}", loginType, loginId.toString(), service, level, disableTime);
    }

    /** 每次被解封时触发 */
    @Override
    public void doUntieDisable(String loginType, Object loginId, String service) {
        log.info("---------- 自定义侦听器实现 doUntieDisable,loginType:{},loginId:{},service:{}", loginType, loginId, service);
    }

    /** 每次二级认证时触发 */
    @Override
    public void doOpenSafe(String loginType, String tokenValue, String service, long safeTime) {
        log.info("---------- 自定义侦听器实现 doOpenSafe,loginType:{},tokenValue:{},service:{},safeTime:{}", loginType, tokenValue, service, safeTime);
    }

    /** 每次退出二级认证时触发 */
    @Override
    public void doCloseSafe(String loginType, String tokenValue, String service) {
        log.info("---------- 自定义侦听器实现 doCloseSafe,loginType:{},tokenValue:{},service:{}", loginType, tokenValue, service);
    }

    /** 每次创建Session时触发 */
    @Override
    public void doCreateSession(String id) {
       log.info("---------- 自定义侦听器实现 每次创建Session时触发 doCreateSession,id:{}",id);
    }

    /** 每次注销Session时触发 */
    @Override
    public void doLogoutSession(String id) {
       log.info("---------- 自定义侦听器实现 每次注销Session时触发 doLogoutSession,id:{}",id);
    }

    /** 每次Token续期时触发 */
    @Override
    public void doRenewTimeout(String tokenValue, Object loginId, long timeout) {
       log.info("---------- 自定义侦听器实现 每次Token续期时触发 doRenewTimeout,tokenValue:{},loginId:{},timeout:{}",tokenValue,loginId.toString(),timeout);
    }

}
