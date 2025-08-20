package org.example.system.system.member;

/**
 * Member Service 接口
 *
 * @author 后台源码
 */
public interface MemberService {

    /**
     * 获得会员用户的手机号码
     *
     * @param id 会员用户编号
     * @return 手机号码 member user mobile
     */
    String getMemberUserMobile(Long id);

    /**
     * 获得会员用户的邮箱
     *
     * @param id 会员用户编号
     * @return 邮箱 member user email
     */
    String getMemberUserEmail(Long id);

}
