package org.example.service.dream.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ma.glasnost.orika.MapperFacade;
import org.example.po.dream.dream.DreamUFinanceAccDO;
import org.example.po.mapper.dream.DreamUFinanceAccMapper;
import org.example.pojo.PageResult;
import org.example.service.dream.DreamUFinanceAccService;
import org.example.vo.dream.financeAcc.DreamUFinanceAccReqVo;
import org.example.vo.dream.financeAcc.DreamUFinanceAccVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @description 针对表【dream_u_finance_acc】的数据库操作Service实现
 * @createDate 2023-09-13 13:32:04
 */
@Service
public class DreamUFinanceAccServiceImpl extends ServiceImpl<DreamUFinanceAccMapper, DreamUFinanceAccDO>
        implements DreamUFinanceAccService {

    @Resource
    private DreamUFinanceAccMapper dreamUFinanceAccMapper;
    @Resource
    private MapperFacade mapperFacade;

    @Override
    public PageResult<DreamUFinanceAccDO> pageByParams(DreamUFinanceAccReqVo pageVO) {
        return dreamUFinanceAccMapper.pageByParams(pageVO);
    }


    @Override
    public DreamUFinanceAccVO selectByUserId(Long id) {
        DreamUFinanceAccDO userId = getBaseMapper().selectOne("user_id", id);
        return mapperFacade.map(userId, DreamUFinanceAccVO.class);
    }
}




