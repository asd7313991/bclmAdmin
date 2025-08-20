package org.example.service.dream.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import org.example.config.mybatisplus.BaseMapperX;
import org.example.config.mybatisplus.LambdaQueryWrapperX;
import org.example.exception.enums.GlobalErrorCodeConstants;
import org.example.po.dream.dream.DreamUFinanceFlowDO;
import org.example.po.dream.dream.DreamUFinanceIoDO;
import org.example.po.mapper.dream.DreamUFinanceIoMapper;
import org.example.pojo.PageResult;
import org.example.service.dream.DreamUFinanceFlowService;
import org.example.service.dream.DreamUFinanceIoService;
import org.example.service.dream.DreamUFinanceMainService;
import org.example.service.dream.DreamUUserService;
import org.example.service.system.homepage.HomePageReqVO;
import org.example.util.collection.CollectionUtils;
import org.example.util.mybatis.MyBatisUtils;
import org.example.vo.dream.financeIo.DreamUFinanceIoAmountPerDayVO;
import org.example.vo.dream.financeIo.DreamUFinanceIoAmountVO;
import org.example.vo.dream.financeIo.DreamUFinanceIoReqVo;
import org.example.vo.dream.financeIo.DreamUFinanceIoVO;
import org.example.vo.dream.financeMain.DreamUFinanceMainVO;
import org.example.vo.dream.user.DreamUUserSimpleVO;
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
 * @description 针对表【dream_u_finance_io】的数据库操作Service实现
 * @createDate 2023-09-13 13:32:04
 */
@Slf4j
@Service
public class DreamUFinanceIoServiceImpl extends ServiceImpl<DreamUFinanceIoMapper, DreamUFinanceIoDO>
        implements DreamUFinanceIoService {


    @Resource
    private MapperFacade mapperFacade;
    @Resource
    private DreamUFinanceFlowService dreamUFinanceFlowService;
    @Resource
    private DreamUFinanceMainService dreamUFinanceMainService;
    @Resource
    private DreamUUserService dreamUUserService;

    @Override
    public PageResult<DreamUFinanceIoVO> pageByParams(DreamUFinanceIoReqVo pageVO) {
        IPage<DreamUFinanceIoDO> mpPage = MyBatisUtils.buildPage(pageVO);
        IPage<DreamUFinanceIoVO> dreamUFinanceIoDOIPage = getBaseMapper().selectPageNew(mpPage, pageVO);
        PageResult<DreamUFinanceIoVO> pageResult =  PageResult.empty(dreamUFinanceIoDOIPage.getTotal());

//        List<Long> collect = dreamUFinanceIoDOIPage.getRecords().stream().map(DreamUFinanceIoVO::getUserId).distinct().collect(Collectors.toList());
//        Map<Long, DreamUUserSimpleVO> longDreamUUserSimpleVOMap = dreamUUserService.selectMapByIdList(collect);
//
//        for (DreamUFinanceIoVO dreamUFinanceDepositVO : dreamUFinanceIoDOIPage.getRecords()) {
//            Optional.ofNullable(longDreamUUserSimpleVOMap.get(dreamUFinanceDepositVO.getUserId())).ifPresent(dreamUUserSimpleVO -> {
//                dreamUFinanceDepositVO.setUsername(dreamUUserSimpleVO.getUserName());
//            });
//            dreamUFinanceDepositVO.setIdStr(String.valueOf(dreamUFinanceDepositVO.getId()));
//        }
        pageResult.setList(dreamUFinanceIoDOIPage.getRecords());

        return pageResult;
    }

    @Override
    public List<DreamUFinanceIoAmountVO> sumAmount(HomePageReqVO reqVO) {
        return getBaseMapper().sumAmountGroupBy(reqVO);
    }

    @Override
    public List<DreamUFinanceIoAmountPerDayVO> sumPerDayAmount(HomePageReqVO reqVO) {
        return getBaseMapper().sumAmountPerDayGroupBy(reqVO);
    }


}




