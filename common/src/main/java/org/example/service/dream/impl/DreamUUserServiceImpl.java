package org.example.service.dream.impl;

import cn.dev33.satoken.secure.BCrypt;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yomahub.liteflow.core.FlowExecutor;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import org.example.config.mybatisplus.LambdaQueryWrapperX;
import org.example.exception.enums.GlobalErrorCodeConstants;
import org.example.po.dream.dream.DreamUUserDO;
import org.example.po.mapper.dream.DreamUUserMapper;
import org.example.pojo.PageResult;
import org.example.service.dream.DreamUFinanceAccService;
import org.example.service.dream.DreamUFinanceMainService;
import org.example.service.dream.DreamUUserService;
import org.example.service.system.homepage.HomePageReqVO;
import org.example.util.RandomUtil;
import org.example.vo.dream.financeAcc.DreamUFinanceAccVO;
import org.example.vo.dream.financeMain.DreamUFinanceMainVO;
import org.example.vo.dream.user.*;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static org.example.enums.RedisConstants.REDIS_CACHE_CAPTCHA_CODE;
import static org.example.exception.util.ServiceExceptionUtil.exception;

/**
 * @description 针对表【dream_u_user】的数据库操作Service实现
 * @createDate 2023-09-13 13:32:04
 */

@Slf4j
@Service
public class DreamUUserServiceImpl extends ServiceImpl<DreamUUserMapper, DreamUUserDO>
        implements DreamUUserService {

    @Resource
    private DreamUUserMapper dreamUUserMapper;
    @Resource
    private MapperFacade mapperFacade;
    @Resource
    private DreamUFinanceAccService dreamUFinanceAccService;
    @Resource
    private DreamUFinanceMainService dreamUFinanceMainService;
    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private FlowExecutor flowExecutor;

    @Override
    public PageResult<DreamUUserDO> pageByParams(DreamUUserPageReqVO pageVO) {
        return dreamUUserMapper.selectPage(pageVO);
    }

    @Override
    public List<DreamUUserSimpleVO> selectByIdList(List<Long> collect) {
        List<DreamUUserDO> dreamUUserDOS = dreamUUserMapper.selectList(DreamUUserDO::getId, collect);
        return mapperFacade.mapAsList(dreamUUserDOS, DreamUUserSimpleVO.class);
    }

    @Override
    public Map<Long, DreamUUserSimpleVO> selectMapByIdList(List<Long> collect) {
        List<DreamUUserSimpleVO> dreamUUserSimpleVOS = this.selectByIdList(collect);
        if (CollectionUtil.isEmpty(dreamUUserSimpleVOS)) {
            return MapUtil.empty();
        }

        Map<Long, DreamUUserSimpleVO> map = dreamUUserSimpleVOS.stream().collect(Collectors.groupingBy(DreamUUserSimpleVO::getId, Collectors.collectingAndThen(Collectors.toList(), s -> s.get(0))));
        return map;
    }


    @Override
    public DreamUUserInfoVO getInfoById(Long userId) {
        DreamUUserDO byId = this.getById(userId);
        if (byId == null) {
            throw exception(GlobalErrorCodeConstants.NOT_FOUND_DATA);
        }
        DreamUUserInfoVO dreamUUserInfoVO = new DreamUUserInfoVO();
        DreamUUserVO dreamUUserVO = mapperFacade.map(byId, DreamUUserVO.class);
        dreamUUserInfoVO.setDreamUUserVO(dreamUUserVO);
        DreamUFinanceAccVO dreamUFinanceAccVO = dreamUFinanceAccService.selectByUserId(userId);
        dreamUUserInfoVO.setDreamUFinanceAccVO(dreamUFinanceAccVO == null ? new DreamUFinanceAccVO() : dreamUFinanceAccVO);
        DreamUFinanceMainVO dreamUFinanceMainVO = dreamUFinanceMainService.setectByUserId(userId);
        dreamUUserInfoVO.setDreamUFinanceMainVO(dreamUFinanceMainVO == null ? new DreamUFinanceMainVO() : dreamUFinanceMainVO);
        return dreamUUserInfoVO;
    }

    @Override
    public boolean changePassword(DreamUUserVO dreamUUserVO) {
        DreamUUserDO dreamUUserDO = getBaseMapper().selectById(dreamUUserVO.getUserId());

        if (dreamUUserDO == null) {
            throw exception(GlobalErrorCodeConstants.NOT_FOUND_DATA);
        }

        //更新密码
        String gensalt = BCrypt.gensalt(10);
        String hashpw = BCrypt.hashpw(dreamUUserVO.getPassword(), gensalt);
        DreamUUserDO newDreamUUserDO = new DreamUUserDO();
        newDreamUUserDO.setUserPass(hashpw);
        newDreamUUserDO.setPassSalt(gensalt);
        newDreamUUserDO.setPassEncryptTimes(10);
        newDreamUUserDO.setId(dreamUUserDO.getId());
        int i = getBaseMapper().updateById(newDreamUUserDO);
        return i > 0;
    }


    @Override
    public boolean createVipUser(DreamUUserVO createVipUser) {
        DreamUUserDO map = mapperFacade.map(createVipUser, DreamUUserDO.class);
        map.setUserPass(createVipUser.getPassword());
        map.setUserType(createVipUser.getUserType());
        return this.register(map);
    }


    @Override
    public DreamUUserDO selectByUserName(String uname) {
        return getBaseMapper().selectOne(new LambdaQueryWrapper<DreamUUserDO>()
                .eq(DreamUUserDO::getUserName, uname));
    }


    @Override
    public DreamUUserDO selectById(Long userId) {
        return getBaseMapper().selectOne(new LambdaQueryWrapperX<DreamUUserDO>()
                .eq(DreamUUserDO::getId, userId));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateUserInfo(DreamUUserSimpleReqVO updateReq) {
        DreamUUserDO dreamUUserDO = mapperFacade.map(updateReq, DreamUUserDO.class);
        return this.updateById(dreamUUserDO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean changeLoginPassword(DreamUUserPwdReqVO updateReqVO) {
        DreamUUserDO dreamUUserDO = getBaseMapper().selectById(updateReqVO.getId());

        if (dreamUUserDO == null) {
            throw exception(GlobalErrorCodeConstants.NOT_FOUND_DATA);
        }

        //验证旧密码是否一致
        if (!BCrypt.hashpw(updateReqVO.getOldPwd(), dreamUUserDO.getPassSalt()).equals(dreamUUserDO.getUserPass())) {
            throw exception(GlobalErrorCodeConstants.LOGIN_PWD_NOT_SAME);
        }

        //更新密码
        String gensalt = BCrypt.gensalt(10);
        String hashpw = BCrypt.hashpw(updateReqVO.getNewPwd(), gensalt);
        DreamUUserDO newDreamUUserDO = new DreamUUserDO();
        newDreamUUserDO.setUserPass(hashpw);
        newDreamUUserDO.setPassSalt(gensalt);
        newDreamUUserDO.setPassEncryptTimes(10);
        newDreamUUserDO.setId(dreamUUserDO.getId());
        int i = getBaseMapper().updateById(newDreamUUserDO);
        return i > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean register(DreamUUserDO dreamUUserDO) {

        log.info("注册：{}", JSONUtil.toJsonStr(dreamUUserDO));

        DreamUUserDO dreamUUserDO1 = getBaseMapper().selectOne(DreamUUserDO::getUserName, dreamUUserDO.getUserName());

        if (dreamUUserDO1 != null) {
            throw exception(GlobalErrorCodeConstants.USER_HAS_EXIT);
        }

        DreamUUserDO newDreamUUserDO = mapperFacade.map(dreamUUserDO, DreamUUserDO.class);
        String gensalt = BCrypt.gensalt(10);
        String hashpw = BCrypt.hashpw(dreamUUserDO.getUserPass(), gensalt);
        newDreamUUserDO.setUserPass(hashpw);
        newDreamUUserDO.setPassSalt(gensalt);
        newDreamUUserDO.setPassEncryptTimes(10);
//        newDreamUUserDO.setRewardTime();
        boolean save = this.save(newDreamUUserDO);
        if (!save) {
            throw exception(GlobalErrorCodeConstants.REGISTER_ADD_INFO_FAIL);
        }
        // 异步处理
        flowExecutor.execute2Future("register",newDreamUUserDO,DreamUUserDO.class);
        return save;
    }

    @Override
    public String getCaptchaNeedLoginByUserId(Integer type, Long userId) {
        String key = REDIS_CACHE_CAPTCHA_CODE + type + ":" + userId;
        Object value = redisTemplate.opsForValue().get(key);
        //缓存有直接返回
        if (ObjectUtil.isNotEmpty(value)) {
            return (String) value;
        }

        String generateCode6 = RandomUtil.generateCode6();
        redisTemplate.opsForValue().set(key, generateCode6, 5 * 60, TimeUnit.SECONDS);
        return generateCode6;
    }

    @Override
    public String getCaptchaNoLoginByUserName(Integer type, String uname) {
        String key = REDIS_CACHE_CAPTCHA_CODE + type + ":" + uname;
        Object value = redisTemplate.opsForValue().get(key);
        //缓存有直接返回
        if (ObjectUtil.isNotEmpty(value)) {
            return (String) value;
        }

        String generateCode6 = RandomUtil.generateCode6();
        redisTemplate.opsForValue().set(key, generateCode6, 30, TimeUnit.SECONDS);
        return generateCode6;
    }

    @Override
    public List<DreamUUserCountVO> countUserGroupBy(HomePageReqVO reqVO) {
        return dreamUUserMapper.countUserGroupBy(reqVO);
    }

    @Override
    public List<DreamUUserCountPerDayVO> countUserPerDayGroupBy(HomePageReqVO reqVO) {
        return dreamUUserMapper.countUserPerDayGroupBy(reqVO);
    }


    @Override
    public void login(DreamUUserDO dreamUUserDO) {

    }


    @Override
    public void updateUserInfoByAdmin(DreamUUserVO updateReqVO) {
        this.updateById(updateReqVO);
    }


//    public void updateUserStatus(DreamUUserVO updateReqVO) {
//
//    }


    @Async
    @Override
    public void loginChain(DreamUUserDO dreamUUserDO, Boolean aFalse, Exception exception) {
        if (aFalse) {
            // 异步通过流程引擎执行 登录后续的逻辑
            flowExecutor.execute2Future("loginChain", dreamUUserDO, DreamUUserDO.class);
        } else {
            flowExecutor.execute2Future("loginChain", dreamUUserDO, DreamUUserDO.class);
        }
    }
}




