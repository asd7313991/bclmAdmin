//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.example.config.satoken;

import cn.dev33.satoken.SaManager;
import cn.dev33.satoken.fun.SaFunction;
import cn.dev33.satoken.listener.SaTokenEventCenter;
import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.session.TokenSign;
import cn.dev33.satoken.stp.SaLoginModel;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpLogic;

import java.util.List;

/**
 * The type Stp system util.
 */
public class StpSystemUtil {

    /**
     * The constant TYPE.
     */
    public static final String TYPE = "system";
    /**
     * The constant stpLogic.
     */
    public static StpLogic stpLogic = new StpLogic(TYPE){
        @Override
        public String splicingKeyTokenName() {
            return super.splicingKeyTokenName()+"-system";
        }
    };

    private StpSystemUtil() {
    }

    /**
     * Gets login type.
     *
     * @return the login type
     */
    public static String getLoginType() {
        return stpLogic.getLoginType();
    }

    /**
     * Sets stp logic.
     *
     * @param newStpLogic the new stp logic
     */
    public static void setStpLogic(StpLogic newStpLogic) {
        stpLogic = newStpLogic;
        SaManager.putStpLogic(newStpLogic);
        SaTokenEventCenter.doSetStpLogic(stpLogic);
    }


    /**
     * Gets stp logic.
     *
     * @return the stp logic
     */
    public static StpLogic getStpLogic() {
        return stpLogic;
    }

    /**
     * Gets token name.
     *
     * @return the token name
     */
    public static String getTokenName() {
        return stpLogic.getTokenName();
    }

    /**
     * Sets token value.
     *
     * @param tokenValue the token value
     */
    public static void setTokenValue(String tokenValue) {
        stpLogic.setTokenValue(tokenValue);
    }

    /**
     * Sets token value.
     *
     * @param tokenValue    the token value
     * @param cookieTimeout the cookie timeout
     */
    public static void setTokenValue(String tokenValue, int cookieTimeout) {
        stpLogic.setTokenValue(tokenValue, cookieTimeout);
    }

    /**
     * Sets token value.
     *
     * @param tokenValue the token value
     * @param loginModel the login model
     */
    public static void setTokenValue(String tokenValue, SaLoginModel loginModel) {
        stpLogic.setTokenValue(tokenValue, loginModel);
    }

    /**
     * Gets token value.
     *
     * @return the token value
     */
    public static String getTokenValue() {
        return stpLogic.getTokenValue();
    }

    /**
     * Gets token value not cut.
     *
     * @return the token value not cut
     */
    public static String getTokenValueNotCut() {
        return stpLogic.getTokenValueNotCut();
    }

    /**
     * Gets token info.
     *
     * @return the token info
     */
    public static SaTokenInfo getTokenInfo() {
        return stpLogic.getTokenInfo();
    }

    /**
     * Login.
     *
     * @param id the id
     */
    public static void login(Object id) {
        stpLogic.login(id);
    }

    /**
     * Login.
     *
     * @param id     the id
     * @param device the device
     */
    public static void login(Object id, String device) {
        stpLogic.login(id, device);
    }

    /**
     * Login.
     *
     * @param id              the id
     * @param isLastingCookie the is lasting cookie
     */
    public static void login(Object id, boolean isLastingCookie) {
        stpLogic.login(id, isLastingCookie);
    }

    /**
     * Login.
     *
     * @param id      the id
     * @param timeout the timeout
     */
    public static void login(Object id, long timeout) {
        stpLogic.login(id, timeout);
    }

    /**
     * Login.
     *
     * @param id         the id
     * @param loginModel the login model
     */
    public static void login(Object id, SaLoginModel loginModel) {
        stpLogic.login(id, loginModel);
    }

    /**
     * Create login session string.
     *
     * @param id the id
     * @return the string
     */
    public static String createLoginSession(Object id) {
        return stpLogic.createLoginSession(id);
    }

    /**
     * Create login session string.
     *
     * @param id         the id
     * @param loginModel the login model
     * @return the string
     */
    public static String createLoginSession(Object id, SaLoginModel loginModel) {
        return stpLogic.createLoginSession(id, loginModel);
    }

    /**
     * Logout.
     */
    public static void logout() {
        stpLogic.logout();
    }

    /**
     * Logout.
     *
     * @param loginId the login id
     */
    public static void logout(Object loginId) {
        stpLogic.logout(loginId);
    }

    /**
     * Logout.
     *
     * @param loginId the login id
     * @param device  the device
     */
    public static void logout(Object loginId, String device) {
        stpLogic.logout(loginId, device);
    }

    /**
     * Logout by token value.
     *
     * @param tokenValue the token value
     */
    public static void logoutByTokenValue(String tokenValue) {
        stpLogic.logoutByTokenValue(tokenValue);
    }

    /**
     * Kickout.
     *
     * @param loginId the login id
     */
    public static void kickout(Object loginId) {
        stpLogic.kickout(loginId);
    }

    /**
     * Kickout.
     *
     * @param loginId the login id
     * @param device  the device
     */
    public static void kickout(Object loginId, String device) {
        stpLogic.kickout(loginId, device);
    }

    /**
     * Kickout by token value.
     *
     * @param tokenValue the token value
     */
    public static void kickoutByTokenValue(String tokenValue) {
        stpLogic.kickoutByTokenValue(tokenValue);
    }

    /**
     * Replaced.
     *
     * @param loginId the login id
     * @param device  the device
     */
    public static void replaced(Object loginId, String device) {
        stpLogic.replaced(loginId, device);
    }

    /**
     * Is login boolean.
     *
     * @return the boolean
     */
    public static boolean isLogin() {
        return stpLogic.isLogin();
    }

    /**
     * Is login boolean.
     *
     * @param loginId the login id
     * @return the boolean
     */
    public static boolean isLogin(Object loginId) {
        return stpLogic.isLogin(loginId);
    }

    /**
     * Check login.
     */
    public static void checkLogin() {
        stpLogic.checkLogin();
    }

    /**
     * Gets login id.
     *
     * @return the login id
     */
    public static Object getLoginId() {
        return stpLogic.getLoginId();
    }

    /**
     * Gets login id.
     *
     * @param <T>          the type parameter
     * @param defaultValue the default value
     * @return the login id
     */
    public static <T> T getLoginId(T defaultValue) {
        return stpLogic.getLoginId(defaultValue);
    }

    /**
     * Gets login id default null.
     *
     * @return the login id default null
     */
    public static Object getLoginIdDefaultNull() {
        return stpLogic.getLoginIdDefaultNull();
    }

    /**
     * Gets login id as string.
     *
     * @return the login id as string
     */
    public static String getLoginIdAsString() {
        return stpLogic.getLoginIdAsString();
    }

    /**
     * Gets login id as int.
     *
     * @return the login id as int
     */
    public static int getLoginIdAsInt() {
        return stpLogic.getLoginIdAsInt();
    }

    /**
     * Gets login id as long.
     *
     * @return the login id as long
     */
    public static long getLoginIdAsLong() {
        return stpLogic.getLoginIdAsLong();
    }

    /**
     * Gets login id by token.
     *
     * @param tokenValue the token value
     * @return the login id by token
     */
    public static Object getLoginIdByToken(String tokenValue) {
        return stpLogic.getLoginIdByToken(tokenValue);
    }

    /**
     * Gets extra.
     *
     * @param key the key
     * @return the extra
     */
    public static Object getExtra(String key) {
        return stpLogic.getExtra(key);
    }

    /**
     * Gets extra.
     *
     * @param tokenValue the token value
     * @param key        the key
     * @return the extra
     */
    public static Object getExtra(String tokenValue, String key) {
        return stpLogic.getExtra(tokenValue, key);
    }

    /**
     * Gets session by login id.
     *
     * @param loginId  the login id
     * @param isCreate the is create
     * @return the session by login id
     */
    public static SaSession getSessionByLoginId(Object loginId, boolean isCreate) {
        return stpLogic.getSessionByLoginId(loginId, isCreate);
    }

    /**
     * Gets session by session id.
     *
     * @param sessionId the session id
     * @return the session by session id
     */
    public static SaSession getSessionBySessionId(String sessionId) {
        return stpLogic.getSessionBySessionId(sessionId);
    }

    /**
     * Gets session by login id.
     *
     * @param loginId the login id
     * @return the session by login id
     */
    public static SaSession getSessionByLoginId(Object loginId) {
        return stpLogic.getSessionByLoginId(loginId);
    }

    /**
     * Gets session.
     *
     * @param isCreate the is create
     * @return the session
     */
    public static SaSession getSession(boolean isCreate) {
        return stpLogic.getSession(isCreate);
    }

    /**
     * Gets session.
     *
     * @return the session
     */
    public static SaSession getSession() {
        return stpLogic.getSession();
    }

    /**
     * Gets token session by token.
     *
     * @param tokenValue the token value
     * @return the token session by token
     */
    public static SaSession getTokenSessionByToken(String tokenValue) {
        return stpLogic.getTokenSessionByToken(tokenValue);
    }

    /**
     * Gets token session.
     *
     * @return the token session
     */
    public static SaSession getTokenSession() {
        return stpLogic.getTokenSession();
    }

    /**
     * Gets anon token session.
     *
     * @return the anon token session
     */
    public static SaSession getAnonTokenSession() {
        return stpLogic.getAnonTokenSession();
    }

    /**
     * Update last active to now.
     */
    public static void updateLastActiveToNow() {
        stpLogic.updateLastActiveToNow();
    }

    /**
     * Check active timeout.
     */
    public static void checkActiveTimeout() {
        stpLogic.checkActiveTimeout();
    }

    /**
     * Gets token timeout.
     *
     * @return the token timeout
     */
    public static long getTokenTimeout() {
        return stpLogic.getTokenTimeout();
    }

    /**
     * Gets token timeout.
     *
     * @param token the token
     * @return the token timeout
     */
    public static long getTokenTimeout(String token) {
        return stpLogic.getTokenTimeout(token);
    }

    /**
     * Gets session timeout.
     *
     * @return the session timeout
     */
    public static long getSessionTimeout() {
        return stpLogic.getSessionTimeout();
    }

    /**
     * Gets token session timeout.
     *
     * @return the token session timeout
     */
    public static long getTokenSessionTimeout() {
        return stpLogic.getTokenSessionTimeout();
    }

    /**
     * Gets token active timeout.
     *
     * @return the token active timeout
     */
    public static long getTokenActiveTimeout() {
        return stpLogic.getTokenActiveTimeout();
    }

    /**
     * Renew timeout.
     *
     * @param timeout the timeout
     */
    public static void renewTimeout(long timeout) {
        stpLogic.renewTimeout(timeout);
    }

    /**
     * Renew timeout.
     *
     * @param tokenValue the token value
     * @param timeout    the timeout
     */
    public static void renewTimeout(String tokenValue, long timeout) {
        stpLogic.renewTimeout(tokenValue, timeout);
    }

    /**
     * Gets role list.
     *
     * @return the role list
     */
    public static List<String> getRoleList() {
        return stpLogic.getRoleList();
    }

    /**
     * Gets role list.
     *
     * @param loginId the login id
     * @return the role list
     */
    public static List<String> getRoleList(Object loginId) {
        return stpLogic.getRoleList(loginId);
    }

    /**
     * Has role boolean.
     *
     * @param role the role
     * @return the boolean
     */
    public static boolean hasRole(String role) {
        return stpLogic.hasRole(role);
    }

    /**
     * Has role boolean.
     *
     * @param loginId the login id
     * @param role    the role
     * @return the boolean
     */
    public static boolean hasRole(Object loginId, String role) {
        return stpLogic.hasRole(loginId, role);
    }

    /**
     * Has role and boolean.
     *
     * @param roleArray the role array
     * @return the boolean
     */
    public static boolean hasRoleAnd(String... roleArray) {
        return stpLogic.hasRoleAnd(roleArray);
    }

    /**
     * Has role or boolean.
     *
     * @param roleArray the role array
     * @return the boolean
     */
    public static boolean hasRoleOr(String... roleArray) {
        return stpLogic.hasRoleOr(roleArray);
    }

    /**
     * Check role.
     *
     * @param role the role
     */
    public static void checkRole(String role) {
        stpLogic.checkRole(role);
    }

    /**
     * Check role and.
     *
     * @param roleArray the role array
     */
    public static void checkRoleAnd(String... roleArray) {
        stpLogic.checkRoleAnd(roleArray);
    }

    /**
     * Check role or.
     *
     * @param roleArray the role array
     */
    public static void checkRoleOr(String... roleArray) {
        stpLogic.checkRoleOr(roleArray);
    }

    /**
     * Gets permission list.
     *
     * @return the permission list
     */
    public static List<String> getPermissionList() {
        return stpLogic.getPermissionList();
    }

    /**
     * Gets permission list.
     *
     * @param loginId the login id
     * @return the permission list
     */
    public static List<String> getPermissionList(Object loginId) {
        return stpLogic.getPermissionList(loginId);
    }

    /**
     * Has permission boolean.
     *
     * @param permission the permission
     * @return the boolean
     */
    public static boolean hasPermission(String permission) {
        return stpLogic.hasPermission(permission);
    }

    /**
     * Has permission boolean.
     *
     * @param loginId    the login id
     * @param permission the permission
     * @return the boolean
     */
    public static boolean hasPermission(Object loginId, String permission) {
        return stpLogic.hasPermission(loginId, permission);
    }

    /**
     * Has permission and boolean.
     *
     * @param permissionArray the permission array
     * @return the boolean
     */
    public static boolean hasPermissionAnd(String... permissionArray) {
        return stpLogic.hasPermissionAnd(permissionArray);
    }

    /**
     * Has permission or boolean.
     *
     * @param permissionArray the permission array
     * @return the boolean
     */
    public static boolean hasPermissionOr(String... permissionArray) {
        return stpLogic.hasPermissionOr(permissionArray);
    }

    /**
     * Check permission.
     *
     * @param permission the permission
     */
    public static void checkPermission(String permission) {
        stpLogic.checkPermission(permission);
    }

    /**
     * Check permission and.
     *
     * @param permissionArray the permission array
     */
    public static void checkPermissionAnd(String... permissionArray) {
        stpLogic.checkPermissionAnd(permissionArray);
    }

    /**
     * Check permission or.
     *
     * @param permissionArray the permission array
     */
    public static void checkPermissionOr(String... permissionArray) {
        stpLogic.checkPermissionOr(permissionArray);
    }

    /**
     * Gets token value by login id.
     *
     * @param loginId the login id
     * @return the token value by login id
     */
    public static String getTokenValueByLoginId(Object loginId) {
        return stpLogic.getTokenValueByLoginId(loginId);
    }

    /**
     * Gets token value by login id.
     *
     * @param loginId the login id
     * @param device  the device
     * @return the token value by login id
     */
    public static String getTokenValueByLoginId(Object loginId, String device) {
        return stpLogic.getTokenValueByLoginId(loginId, device);
    }

    /**
     * Gets token value list by login id.
     *
     * @param loginId the login id
     * @return the token value list by login id
     */
    public static List<String> getTokenValueListByLoginId(Object loginId) {
        return stpLogic.getTokenValueListByLoginId(loginId);
    }

    /**
     * Gets token value list by login id.
     *
     * @param loginId the login id
     * @param device  the device
     * @return the token value list by login id
     */
    public static List<String> getTokenValueListByLoginId(Object loginId, String device) {
        return stpLogic.getTokenValueListByLoginId(loginId, device);
    }

    /**
     * Gets token sign list by login id.
     *
     * @param loginId the login id
     * @param device  the device
     * @return the token sign list by login id
     */
    public static List<TokenSign> getTokenSignListByLoginId(Object loginId, String device) {
        return stpLogic.getTokenSignListByLoginId(loginId, device);
    }

    /**
     * Gets login device.
     *
     * @return the login device
     */
    public static String getLoginDevice() {
        return stpLogic.getLoginDevice();
    }

    /**
     * Search token value list.
     *
     * @param keyword  the keyword
     * @param start    the start
     * @param size     the size
     * @param sortType the sort type
     * @return the list
     */
    public static List<String> searchTokenValue(String keyword, int start, int size, boolean sortType) {
        return stpLogic.searchTokenValue(keyword, start, size, sortType);
    }

    /**
     * Search session id list.
     *
     * @param keyword  the keyword
     * @param start    the start
     * @param size     the size
     * @param sortType the sort type
     * @return the list
     */
    public static List<String> searchSessionId(String keyword, int start, int size, boolean sortType) {
        return stpLogic.searchSessionId(keyword, start, size, sortType);
    }

    /**
     * Search token session id list.
     *
     * @param keyword  the keyword
     * @param start    the start
     * @param size     the size
     * @param sortType the sort type
     * @return the list
     */
    public static List<String> searchTokenSessionId(String keyword, int start, int size, boolean sortType) {
        return stpLogic.searchTokenSessionId(keyword, start, size, sortType);
    }

    /**
     * Disable.
     *
     * @param loginId the login id
     * @param time    the time
     */
    public static void disable(Object loginId, long time) {
        stpLogic.disable(loginId, time);
    }

    /**
     * Is disable boolean.
     *
     * @param loginId the login id
     * @return the boolean
     */
    public static boolean isDisable(Object loginId) {
        return stpLogic.isDisable(loginId);
    }

    /**
     * Check disable.
     *
     * @param loginId the login id
     */
    public static void checkDisable(Object loginId) {
        stpLogic.checkDisable(loginId);
    }

    /**
     * Gets disable time.
     *
     * @param loginId the login id
     * @return the disable time
     */
    public static long getDisableTime(Object loginId) {
        return stpLogic.getDisableTime(loginId);
    }

    /**
     * Untie disable.
     *
     * @param loginId the login id
     */
    public static void untieDisable(Object loginId) {
        stpLogic.untieDisable(loginId);
    }

    /**
     * Disable.
     *
     * @param loginId the login id
     * @param service the service
     * @param time    the time
     */
    public static void disable(Object loginId, String service, long time) {
        stpLogic.disable(loginId, service, time);
    }

    /**
     * Is disable boolean.
     *
     * @param loginId the login id
     * @param service the service
     * @return the boolean
     */
    public static boolean isDisable(Object loginId, String service) {
        return stpLogic.isDisable(loginId, service);
    }

    /**
     * Check disable.
     *
     * @param loginId  the login id
     * @param services the services
     */
    public static void checkDisable(Object loginId, String... services) {
        stpLogic.checkDisable(loginId, services);
    }

    /**
     * Gets disable time.
     *
     * @param loginId the login id
     * @param service the service
     * @return the disable time
     */
    public static long getDisableTime(Object loginId, String service) {
        return stpLogic.getDisableTime(loginId, service);
    }

    /**
     * Untie disable.
     *
     * @param loginId  the login id
     * @param services the services
     */
    public static void untieDisable(Object loginId, String... services) {
        stpLogic.untieDisable(loginId, services);
    }

    /**
     * Disable level.
     *
     * @param loginId the login id
     * @param level   the level
     * @param time    the time
     */
    public static void disableLevel(Object loginId, int level, long time) {
        stpLogic.disableLevel(loginId, level, time);
    }

    /**
     * Disable level.
     *
     * @param loginId the login id
     * @param service the service
     * @param level   the level
     * @param time    the time
     */
    public static void disableLevel(Object loginId, String service, int level, long time) {
        stpLogic.disableLevel(loginId, service, level, time);
    }

    /**
     * Is disable level boolean.
     *
     * @param loginId the login id
     * @param level   the level
     * @return the boolean
     */
    public static boolean isDisableLevel(Object loginId, int level) {
        return stpLogic.isDisableLevel(loginId, level);
    }

    /**
     * Is disable level boolean.
     *
     * @param loginId the login id
     * @param service the service
     * @param level   the level
     * @return the boolean
     */
    public static boolean isDisableLevel(Object loginId, String service, int level) {
        return stpLogic.isDisableLevel(loginId, service, level);
    }

    /**
     * Check disable level.
     *
     * @param loginId the login id
     * @param level   the level
     */
    public static void checkDisableLevel(Object loginId, int level) {
        stpLogic.checkDisableLevel(loginId, level);
    }

    /**
     * Check disable level.
     *
     * @param loginId the login id
     * @param service the service
     * @param level   the level
     */
    public static void checkDisableLevel(Object loginId, String service, int level) {
        stpLogic.checkDisableLevel(loginId, service, level);
    }

    /**
     * Gets disable level.
     *
     * @param loginId the login id
     * @return the disable level
     */
    public static int getDisableLevel(Object loginId) {
        return stpLogic.getDisableLevel(loginId);
    }

    /**
     * Gets disable level.
     *
     * @param loginId the login id
     * @param service the service
     * @return the disable level
     */
    public static int getDisableLevel(Object loginId, String service) {
        return stpLogic.getDisableLevel(loginId, service);
    }

    /**
     * Switch to.
     *
     * @param loginId the login id
     */
    public static void switchTo(Object loginId) {
        stpLogic.switchTo(loginId);
    }

    /**
     * End switch.
     */
    public static void endSwitch() {
        stpLogic.endSwitch();
    }

    /**
     * Is switch boolean.
     *
     * @return the boolean
     */
    public static boolean isSwitch() {
        return stpLogic.isSwitch();
    }

    /**
     * Switch to.
     *
     * @param loginId  the login id
     * @param function the function
     */
    public static void switchTo(Object loginId, SaFunction function) {
        stpLogic.switchTo(loginId, function);
    }

    /**
     * Open safe.
     *
     * @param safeTime the safe time
     */
    public static void openSafe(long safeTime) {
        stpLogic.openSafe(safeTime);
    }

    /**
     * Open safe.
     *
     * @param service  the service
     * @param safeTime the safe time
     */
    public static void openSafe(String service, long safeTime) {
        stpLogic.openSafe(service, safeTime);
    }

    /**
     * Is safe boolean.
     *
     * @return the boolean
     */
    public static boolean isSafe() {
        return stpLogic.isSafe();
    }

    /**
     * Is safe boolean.
     *
     * @param service the service
     * @return the boolean
     */
    public static boolean isSafe(String service) {
        return stpLogic.isSafe(service);
    }

    /**
     * Is safe boolean.
     *
     * @param tokenValue the token value
     * @param service    the service
     * @return the boolean
     */
    public static boolean isSafe(String tokenValue, String service) {
        return stpLogic.isSafe(tokenValue, service);
    }

    /**
     * Check safe.
     */
    public static void checkSafe() {
        stpLogic.checkSafe();
    }

    /**
     * Check safe.
     *
     * @param service the service
     */
    public static void checkSafe(String service) {
        stpLogic.checkSafe(service);
    }

    /**
     * Gets safe time.
     *
     * @return the safe time
     */
    public static long getSafeTime() {
        return stpLogic.getSafeTime();
    }

    /**
     * Gets safe time.
     *
     * @param service the service
     * @return the safe time
     */
    public static long getSafeTime(String service) {
        return stpLogic.getSafeTime(service);
    }

    /**
     * Close safe.
     */
    public static void closeSafe() {
        stpLogic.closeSafe();
    }

    /**
     * Close safe.
     *
     * @param service the service
     */
    public static void closeSafe(String service) {
        stpLogic.closeSafe(service);
    }
}
