package org.example.service.dream.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.config.mybatisplus.LambdaQueryWrapperX;
import org.example.enums.CommonStatusEnum;
import org.example.exception.enums.GlobalErrorCodeConstants;
import org.example.po.dream.dream.DreamDepositVirAddressDO;
import org.example.po.dream.dream.DreamUVirAddressDO;
import org.example.po.mapper.dream.DreamUVirAddressMapper;
import org.example.pojo.PageResult;
import org.example.service.dream.DreamUVirAddressService;
import org.example.vo.dream.DepositVirAddressVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.example.enums.DreamUVirAddressStatusEnum.REVIEWED;
import static org.example.exception.util.ServiceExceptionUtil.exception;

/**
 * @author Administrator
 * @description 针对表【dream_u_vir_address】的数据库操作Service实现
 * @createDate 2023-09-16 04:20:53
 */
@Service
public class DreamUVirAddressServiceImpl extends ServiceImpl<DreamUVirAddressMapper, DreamUVirAddressDO>
        implements DreamUVirAddressService {


    @Override
    public List<DreamUVirAddressDO> setectByUserId(long loginIdAsLong) {
        return getBaseMapper().selectList(new LambdaQueryWrapperX<DreamUVirAddressDO>()
                .eq(DreamUVirAddressDO::getUserId, loginIdAsLong)
                .ge(DreamUVirAddressDO::getStatus, -1)
                .eq(DreamUVirAddressDO::getDeleted, CommonStatusEnum.ENABLE)
                .orderByDesc(DreamUVirAddressDO::getDefaulted).orderByDesc(DreamUVirAddressDO::getCreateTime));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean addVirAddress(DreamUVirAddressDO dreamUVirAddressDO) {
        dreamUVirAddressDO.setStatus(REVIEWED.getType());
        boolean save = this.save(dreamUVirAddressDO);

        if (dreamUVirAddressDO.getDefaulted() == 1){
            DreamUVirAddressDO dreamUVirAddressDO1 = new DreamUVirAddressDO();
            dreamUVirAddressDO1.setDefaulted(0);
            getBaseMapper().update(dreamUVirAddressDO1,new LambdaQueryWrapperX<DreamUVirAddressDO>()
                    .eq(DreamUVirAddressDO::getDefaulted,1)
                    .eq(DreamUVirAddressDO::getUserId,dreamUVirAddressDO.getUserId())
                    .ne(DreamUVirAddressDO::getId,dreamUVirAddressDO.getId()));
        }
        return save;
    }

    @Override
    public DreamUVirAddressDO getOneReviewedBy(Long id, Long userId) {
        LambdaQueryWrapper<DreamUVirAddressDO> wrapper = new LambdaQueryWrapperX<DreamUVirAddressDO>()
                .eq(DreamUVirAddressDO::getId, id)
                .eq(DreamUVirAddressDO::getUserId, userId)
                .ge(DreamUVirAddressDO::getStatus, REVIEWED.getType())
                .eq(DreamUVirAddressDO::getDeleted, 0);
        return this.getOne(wrapper);
    }

    @Override
    public DreamUVirAddressDO getOneNotDefaultedBy(Long id, Long userId) {
        LambdaQueryWrapper<DreamUVirAddressDO> wrapper = new LambdaQueryWrapperX<DreamUVirAddressDO>()
                .eq(DreamUVirAddressDO::getId, id)
                .eq(DreamUVirAddressDO::getUserId, userId)
//                .eq(DreamUVirAddressDO::getDeleted, 0)
//                .eq(DreamUVirAddressDO::getDefaulted, 0)
                ;
        return this.getOne(wrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean changeVirAddressDefaulted(Long id, Long userId) {
        //取消默认 以前的默认虚拟钱包地址
        LambdaQueryWrapper<DreamUVirAddressDO> wrapper = new LambdaQueryWrapperX<DreamUVirAddressDO>()
                .eq(DreamUVirAddressDO::getUserId, userId)
                .eq(DreamUVirAddressDO::getDeleted, 0)
                .eq(DreamUVirAddressDO::getDefaulted, 1);
        DreamUVirAddressDO updateDO1 = new DreamUVirAddressDO();
        updateDO1.setDefaulted(0);
        baseMapper.update(updateDO1, wrapper);

        //更新新地址默认
        DreamUVirAddressDO updateDO2 = new DreamUVirAddressDO();
        updateDO2.setDefaulted(1);
        updateDO2.setId(id);

        return this.updateById(updateDO2);
    }

    @Override
    public Boolean deleteVirAddress(Long id) {
        DreamUVirAddressDO updateDO = new DreamUVirAddressDO();
        updateDO.setDeleted(1);
        updateDO.setId(id);
        return this.updateById(updateDO);
    }


    @Override
    public PageResult<DreamUVirAddressDO> pageByParams(DepositVirAddressVO depositVirAddressVO) {
        return getBaseMapper().pageByParams(depositVirAddressVO);
    }

    @Override
    public Boolean audit(DepositVirAddressVO depositVirAddressVO) {
        DreamUVirAddressDO dreamUVirAddressDO = getBaseMapper().selectById(depositVirAddressVO.getId());
        if (dreamUVirAddressDO == null){
            throw exception(GlobalErrorCodeConstants.NOT_FOUND_DATA);
        }

        dreamUVirAddressDO.setStatus(depositVirAddressVO.getStatus());
        getBaseMapper().updateById(dreamUVirAddressDO);

        if (dreamUVirAddressDO.getDefaulted() == 1){
            DreamUVirAddressDO dreamUVirAddressDO1 = new DreamUVirAddressDO();
            dreamUVirAddressDO1.setDefaulted(0);
            getBaseMapper().update(dreamUVirAddressDO1,new LambdaQueryWrapperX<DreamUVirAddressDO>()
                    .eq(DreamUVirAddressDO::getDefaulted,1)
                    .ne(DreamUVirAddressDO::getId,dreamUVirAddressDO.getId()));
        }
        return Boolean.TRUE;
    }
}




