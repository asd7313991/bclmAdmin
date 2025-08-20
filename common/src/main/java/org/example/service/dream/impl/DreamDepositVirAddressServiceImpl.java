package org.example.service.dream.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ma.glasnost.orika.MapperFacade;
import org.example.config.mybatisplus.LambdaQueryWrapperX;
import org.example.enums.CommonStatusEnum;
import org.example.exception.enums.GlobalErrorCodeConstants;
import org.example.po.dream.dream.DreamDepositVirAddressDO;
import org.example.po.mapper.dream.DreamDepositVirAddressMapper;
import org.example.pojo.PageResult;
import org.example.service.dream.DreamDepositVirAddressService;
import org.example.util.mybatis.MyBatisUtils;
import org.example.vo.DreamDepositVirAddressVO;
import org.example.vo.dream.DepositVirAddressVO;
import org.example.vo.dream.withdraw.DepositVirAddressReq;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import static org.example.exception.util.ServiceExceptionUtil.exception;

/**
 * @author Administrator
 * @description 针对表【dream_deposit_vir_address】的数据库操作Service实现
 * @createDate 2023-09-17 03:12:43
 */
@Service
public class DreamDepositVirAddressServiceImpl extends ServiceImpl<DreamDepositVirAddressMapper, DreamDepositVirAddressDO>
        implements DreamDepositVirAddressService {

    @Resource
    private MapperFacade mapperFacade;



    @Override
    public List<DreamDepositVirAddressDO> selectByDepositTypeId(List<Long> collect) {

        return getBaseMapper().selectList(new LambdaQueryWrapperX<DreamDepositVirAddressDO>()
                .in(DreamDepositVirAddressDO::getDepositTypeId, collect)
                .eq(DreamDepositVirAddressDO::getStatus, CommonStatusEnum.ENABLE)
                .orderByDesc(DreamDepositVirAddressDO::getStatus)
                .orderByDesc(DreamDepositVirAddressDO::getMaxMount)
                .orderByDesc(DreamDepositVirAddressDO::getCreateTime));

    }

    @Override
    public DreamDepositVirAddressDO selectByAddressId(Long addressId) {
        return getBaseMapper().selectOne(new LambdaQueryWrapperX<DreamDepositVirAddressDO>()
                .eq(DreamDepositVirAddressDO::getId, addressId)
                .eq(DreamDepositVirAddressDO::getStatus, CommonStatusEnum.ENABLE));
    }


    @Override
    public PageResult<DreamDepositVirAddressVO> pageByParams(DepositVirAddressVO depositVirAddressVO) {

        Page<DreamDepositVirAddressVO> objectPage = MyBatisUtils.buildPage(depositVirAddressVO);
        List<DreamDepositVirAddressVO> dreamDepositVirAddressDOS = getBaseMapper().selectPageNew(objectPage,depositVirAddressVO);
        PageResult<DreamDepositVirAddressVO> empty = PageResult.empty(objectPage.getTotal());
        empty.setList(mapperFacade.mapAsList(dreamDepositVirAddressDOS,DreamDepositVirAddressVO.class));
        return empty;
    }


    @Override
    public Boolean update(DepositVirAddressReq depositVirAddressVO) {
        DreamDepositVirAddressDO map = mapperFacade.map(depositVirAddressVO, DreamDepositVirAddressDO.class);
        return getBaseMapper().updateById(map)>0;
    }

    @Override
    public Boolean create(DepositVirAddressReq depositVirAddressVO) {
        DreamDepositVirAddressDO map = mapperFacade.map(depositVirAddressVO, DreamDepositVirAddressDO.class);
        return getBaseMapper().insert(map)>0;
    }
}




