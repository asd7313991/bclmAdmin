package org.example.service.dream.impl;

import cn.dev33.satoken.secure.BCrypt;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import org.apache.commons.lang3.StringUtils;
import org.example.config.mybatisplus.LambdaQueryWrapperX;
import org.example.exception.enums.GlobalErrorCodeConstants;
import org.example.po.dream.dream.DreamUFinanceMainDO;
import org.example.po.mapper.dream.DreamUFinanceMainMapper;
import org.example.pojo.PageResult;
import org.example.service.dream.DreamUFinanceMainService;
import org.example.service.dream.DreamUUserService;
import org.example.vo.dream.financeMain.DreamUFinanceMainVO;
import org.example.vo.dream.financeMain.DreamUFinancePageReqVO;
import org.example.vo.dream.user.DreamUUserPwdReqVO;
import org.example.vo.dream.user.DreamUUserSimpleVO;
import org.example.vo.dream.user.DreamUUserVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.example.exception.util.ServiceExceptionUtil.exception;

/**
 * @description 针对表【dream_u_finance_main】的数据库操作Service实现
 * @createDate 2023-09-13 13:32:04
 */
@Slf4j
@Service
public class DreamUFinanceMainServiceImpl extends ServiceImpl<DreamUFinanceMainMapper, DreamUFinanceMainDO>
        implements DreamUFinanceMainService {

    @Resource
    private MapperFacade mapperFacade;
    @Resource
    private DreamUUserService dreamUUserService;

    @Override
    public PageResult<DreamUFinanceMainVO> pageByParams(DreamUFinancePageReqVO pageVO) {
        PageResult<DreamUFinanceMainDO> dreamUFinanceMainDOPageResult = getBaseMapper().selectPage(pageVO);

        PageResult<DreamUFinanceMainVO> empty = PageResult.empty(dreamUFinanceMainDOPageResult.getTotal());
        List<DreamUFinanceMainVO> dreamUFinanceMainVOS = mapperFacade.mapAsList(dreamUFinanceMainDOPageResult.getList(), DreamUFinanceMainVO.class);


        if (CollectionUtil.isNotEmpty(dreamUFinanceMainVOS)) {
            List<Long> collect = dreamUFinanceMainVOS.stream().map(DreamUFinanceMainVO::getUserId).distinct().collect(Collectors.toList());
            Map<Long, DreamUUserSimpleVO> longDreamUUserSimpleVOMap = dreamUUserService.selectMapByIdList(collect);

            for (DreamUFinanceMainVO dreamUFinanceMainVO : dreamUFinanceMainVOS) {
                Optional.ofNullable(longDreamUUserSimpleVOMap.get(dreamUFinanceMainVO.getUserId())).ifPresent(dreamUUserSimpleVO -> {
                    dreamUFinanceMainVO.setUsername(dreamUUserSimpleVO.getUserName());
                });
            }
        }
        empty.setList(dreamUFinanceMainVOS);
        return empty;
    }


    /**
     * 返回资金信息
     * @param userId
     * @return
     */
    @Override
    public DreamUFinanceMainVO setectByUserId(Long userId) {
        DreamUFinanceMainDO dreamUFinanceMainDO = getBaseMapper().selectOne(new LambdaQueryWrapperX<DreamUFinanceMainDO>().eq(DreamUFinanceMainDO::getUserId,userId));
        if (dreamUFinanceMainDO != null){
            return mapperFacade.map(dreamUFinanceMainDO, DreamUFinanceMainVO.class);
        }else{
            DreamUFinanceMainVO dreamUFinanceMainVO = new DreamUFinanceMainVO();
            dreamUFinanceMainVO.setMargin(BigDecimal.valueOf(0));
            dreamUFinanceMainVO.setMoneyFrozen(BigDecimal.valueOf(0));
            dreamUFinanceMainVO.setMoneyUsable(BigDecimal.valueOf(0));
            dreamUFinanceMainVO.setMoneyDrawUsable(BigDecimal.valueOf(0));
            dreamUFinanceMainVO.setUserId(userId);
            return dreamUFinanceMainVO;
        }

    }

    @Override
    public boolean changePassword(DreamUUserVO updateReqVO) {
        log.info("修改交易密码：请求参数：{}",JSONUtil.toJsonStr(updateReqVO));
        DreamUFinanceMainDO dreamUFinanceMainDO = getBaseMapper().selectOne(new LambdaQueryWrapperX<DreamUFinanceMainDO>().eq(DreamUFinanceMainDO::getUserId, updateReqVO.getUserId()));

        if (dreamUFinanceMainDO == null) {
            throw exception(GlobalErrorCodeConstants.NOT_FOUND_DATA);
        }
        DreamUFinanceMainDO dreamUFinanceMainDO1 = new DreamUFinanceMainDO();
        //更新密码
        String gensalt = BCrypt.gensalt(10);
        String hashpw = BCrypt.hashpw(updateReqVO.getPassword(), gensalt);
        dreamUFinanceMainDO1.setPassword(hashpw);
        dreamUFinanceMainDO1.setSalt(gensalt);
//        dreamUFinanceMainDO1.setUserId(dreamUFinanceMainDO.getUserId());
        int update = getBaseMapper().update(dreamUFinanceMainDO1, new LambdaQueryWrapperX<DreamUFinanceMainDO>()
                .eq(DreamUFinanceMainDO::getUserId, dreamUFinanceMainDO.getUserId()));
        return update > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean changePayPassword(DreamUUserPwdReqVO updateReqVO) {
        DreamUFinanceMainDO dreamUFinanceMainDO = getBaseMapper().selectOne(new LambdaQueryWrapperX<DreamUFinanceMainDO>().eq(DreamUFinanceMainDO::getUserId, updateReqVO.getId()));


        if (StringUtils.isAnyBlank(updateReqVO.getOldPwd(),updateReqVO.getNewPwd(),updateReqVO.getNewPwd())){
            log.info("updateReqVO:{}", JSONUtil.toJsonStr(updateReqVO));
        }
        if (dreamUFinanceMainDO!= null &&StringUtils.isNotBlank(dreamUFinanceMainDO.getPassword())){
            if (!BCrypt.checkpw(updateReqVO.getOldPwd(),dreamUFinanceMainDO.getPassword())){
                throw exception(GlobalErrorCodeConstants.PASSWORD_IS_ERROR);
            }
        }



        String gensalt = BCrypt.gensalt(10);
        String hashpw = BCrypt.hashpw(updateReqVO.getNewPwd(), gensalt);

        if (dreamUFinanceMainDO == null) {
            dreamUFinanceMainDO = new DreamUFinanceMainDO();
            dreamUFinanceMainDO.setMargin(BigDecimal.ZERO);
            dreamUFinanceMainDO.setUserId(updateReqVO.getId());
            dreamUFinanceMainDO.setMoneyDrawUsable(BigDecimal.ZERO);
            dreamUFinanceMainDO.setMoneyFrozen(BigDecimal.ZERO);
            dreamUFinanceMainDO.setMoneyUsable(BigDecimal.ZERO);
        }

        if (StringUtils.isBlank(dreamUFinanceMainDO.getPassword())){
            dreamUFinanceMainDO.setPassword(hashpw);
            dreamUFinanceMainDO.setSalt(gensalt);
            getBaseMapper().saveOrUpdateBatch(Lists.newArrayList(dreamUFinanceMainDO));
            return Boolean.TRUE;
        }



        //更新密码
        DreamUFinanceMainDO dreamUFinanceMainDO1 = new DreamUFinanceMainDO();
        dreamUFinanceMainDO1.setPassword(hashpw);
        dreamUFinanceMainDO1.setSalt(gensalt);
        dreamUFinanceMainDO1.setUserId(updateReqVO.getId());
        int update = getBaseMapper().update(dreamUFinanceMainDO1, new LambdaQueryWrapperX<DreamUFinanceMainDO>()
                .eq(DreamUFinanceMainDO::getUserId, updateReqVO.getId()));
        return update > 0;
    }


    /**
     * 更新资金余额
     * @param dreamUFinanceMainVO
     */
    @Override
    public void update(DreamUFinanceMainVO dreamUFinanceMainVO) {
        DreamUFinanceMainDO map = mapperFacade.map(dreamUFinanceMainVO, DreamUFinanceMainDO.class);
        getBaseMapper().updateById(map);
    }


    /**
     * 更新资金余额
     *
     * @param dreamUFinanceMainVO
     */
    @Override
    public void update(DreamUFinanceMainDO dreamUFinanceMainVO) {
        getBaseMapper().updateById(dreamUFinanceMainVO);
    }

    @Override
    public DreamUFinanceMainDO findByUserId(long userId) {
        DreamUFinanceMainDO dreamUFinanceMainDO = getBaseMapper().selectOne(new LambdaQueryWrapperX<DreamUFinanceMainDO>().eq(DreamUFinanceMainDO::getUserId, userId));
        return  dreamUFinanceMainDO;
    }
}




