package org.example.service.dream;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.po.dream.dream.DreamUUserDO;
import org.example.pojo.PageResult;
import org.example.service.system.homepage.HomePageReqVO;
import org.example.vo.dream.user.*;

import java.util.List;
import java.util.Map;

/**
 * @description 针对表【dream_u_user】的数据库操作Service
 * @createDate 2023-09-13 13:32:04
 */
public interface DreamUUserService extends IService<DreamUUserDO> {

    PageResult<DreamUUserDO> pageByParams(DreamUUserPageReqVO pageVO);

    /**
     * 根据userId 返回基本数据
     *
     * @param collect
     * @return
     */
    List<DreamUUserSimpleVO> selectByIdList(List<Long> collect);

    Map<Long, DreamUUserSimpleVO> selectMapByIdList(List<Long> collect);

    DreamUUserInfoVO getInfoById(Long id);

    boolean changePassword(DreamUUserVO dreamUUserVO);

    boolean createVipUser(DreamUUserVO updateReqVO);

    DreamUUserDO selectByUserName(String uname);

    DreamUUserDO selectById(Long userId);

    Boolean updateUserInfo(DreamUUserSimpleReqVO updateReqVO);

    Boolean changeLoginPassword(DreamUUserPwdReqVO updateReqVO);

    Boolean register(DreamUUserDO dreamUUserDO);

    /**
     * 获取验证码，缓存userId+type 5*60s过期
     *
     * @param type
     * @return
     */
    String getCaptchaNeedLoginByUserId(Integer type, Long userId);

    /**
     * 获取验证码，缓存uname+type 30s过期
     *
     * @param type
     * @param uname
     * @return
     */
    String getCaptchaNoLoginByUserName(Integer type, String uname);

    List<DreamUUserCountVO> countUserGroupBy(HomePageReqVO reqVO);

    List<DreamUUserCountPerDayVO> countUserPerDayGroupBy(HomePageReqVO reqVO);

    void login(DreamUUserDO dreamUUserDO);

    void updateUserInfoByAdmin(DreamUUserVO updateReqVO);

    void loginChain(DreamUUserDO dreamUUserDO, Boolean aFalse, Exception exception);
}
