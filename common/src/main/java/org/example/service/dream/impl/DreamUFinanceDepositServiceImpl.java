package org.example.service.dream.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ma.glasnost.orika.MapperFacade;
import org.example.po.dream.dream.DreamUFinanceDepositDO;
import org.example.po.mapper.dream.DreamUFinanceDepositMapper;
import org.example.pojo.PageResult;
import org.example.service.dream.DreamUFinanceDepositService;
import org.example.service.dream.DreamUUserService;
import org.example.vo.dream.financeDeposit.DreamUFinanceDepositPageReqVO;
import org.example.vo.dream.financeDeposit.DreamUFinanceDepositVO;
import org.example.vo.dream.user.DreamUUserSimpleVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @description 针对表【dream_u_finance_deposit】的数据库操作Service实现
 * @createDate 2023-09-13 13:32:04
 */
@Service
public class DreamUFinanceDepositServiceImpl extends ServiceImpl<DreamUFinanceDepositMapper, DreamUFinanceDepositDO>
        implements DreamUFinanceDepositService {

    @Resource
    private MapperFacade mapperFacade;
    @Resource
    private DreamUUserService dreamUUserService;


    @Override
    public PageResult<DreamUFinanceDepositVO> pageByParams(DreamUFinanceDepositPageReqVO pageVO) {
        PageResult<DreamUFinanceDepositDO> pageResult = getBaseMapper().selectPage(pageVO);
        PageResult<DreamUFinanceDepositVO> empty = PageResult.empty(pageResult.getTotal());
        if (CollectionUtil.isEmpty(pageResult.getList())) {
            return empty;
        }

        List<DreamUFinanceDepositVO> dreamUFinanceDepositVOS = mapperFacade.mapAsList(pageResult.getList(), DreamUFinanceDepositVO.class);
        List<Long> collect = dreamUFinanceDepositVOS.stream().map(DreamUFinanceDepositDO::getUserId).distinct().collect(Collectors.toList());
        Map<Long, DreamUUserSimpleVO> longDreamUUserSimpleVOMap = dreamUUserService.selectMapByIdList(collect);

        for (DreamUFinanceDepositVO dreamUFinanceDepositVO : dreamUFinanceDepositVOS) {
            Optional.ofNullable(longDreamUUserSimpleVOMap.get(dreamUFinanceDepositVO.getUserId())).ifPresent(dreamUUserSimpleVO -> {
                dreamUFinanceDepositVO.setUsername(dreamUUserSimpleVO.getUserName());
            });
        }
        empty.setList(dreamUFinanceDepositVOS);
        return empty;
    }
}




