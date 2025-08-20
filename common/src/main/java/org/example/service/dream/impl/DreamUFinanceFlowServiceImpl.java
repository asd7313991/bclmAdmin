package org.example.service.dream.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ma.glasnost.orika.MapperFacade;
import org.example.po.dream.dream.DreamUFinanceFlowDO;
import org.example.po.mapper.dream.DreamUFinanceFlowMapper;
import org.example.pojo.PageResult;
import org.example.service.dream.DreamUFinanceFlowService;
import org.example.service.dream.DreamUUserService;
import org.example.vo.dream.financeFlow.DreamUFinanceFlowReqVo;
import org.example.vo.dream.financeFlow.DreamUFinanceFlowVO;
import org.example.vo.dream.user.DreamUUserSimpleVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @description 针对表【dream_u_finance_flow】的数据库操作Service实现
 * @createDate 2023-09-13 13:32:04
 */
@Service
public class DreamUFinanceFlowServiceImpl extends ServiceImpl<DreamUFinanceFlowMapper, DreamUFinanceFlowDO>
        implements DreamUFinanceFlowService {

    @Resource
    private MapperFacade mapperFacade;
    @Resource
    private DreamUUserService dreamUUserService;


    @Override
    public PageResult<DreamUFinanceFlowVO> pageByParams(DreamUFinanceFlowReqVo pageVO) {
        PageResult<DreamUFinanceFlowDO> pageResult = getBaseMapper().selectPage(pageVO);
        PageResult<DreamUFinanceFlowVO> empty = PageResult.empty(pageResult.getTotal());
        if (CollectionUtil.isEmpty(pageResult.getList())) {
            return empty;
        }

        List<DreamUFinanceFlowVO> dreamUFinanceFlowVOList = mapperFacade.mapAsList(pageResult.getList(), DreamUFinanceFlowVO.class);
        List<Long> collect = dreamUFinanceFlowVOList.stream().map(DreamUFinanceFlowVO::getUserId).distinct().collect(Collectors.toList());
        Map<Long, DreamUUserSimpleVO> longDreamUUserSimpleVOMap = dreamUUserService.selectMapByIdList(collect);

        for (DreamUFinanceFlowVO dreamUFinanceDepositVO : dreamUFinanceFlowVOList) {
            Optional.ofNullable(longDreamUUserSimpleVOMap.get(dreamUFinanceDepositVO.getUserId())).ifPresent(dreamUUserSimpleVO -> {
                dreamUFinanceDepositVO.setUsername(dreamUUserSimpleVO.getUserName());
            });
        }
        empty.setList(dreamUFinanceFlowVOList);
        return empty;
    }

    @Override
    public List<DreamUFinanceFlowVO> listByParams(DreamUFinanceFlowReqVo pageVO) {
        List<DreamUFinanceFlowDO> dreamUFinanceFlowDOS = getBaseMapper().selectList(DreamUFinanceFlowDO::getIoOrderId, pageVO.getIoOrderId());
        return mapperFacade.mapAsList(dreamUFinanceFlowDOS,DreamUFinanceFlowVO.class);
    }
}




