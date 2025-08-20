package org.example.service.dream.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import ma.glasnost.orika.MapperFacade;
import org.example.config.mybatisplus.LambdaQueryWrapperX;
import org.example.enums.CommonStatusEnum;
import org.example.po.dream.dream.DreamDepositTypeDO;
import org.example.po.dream.dream.DreamDepositTypeRuleDO;
import org.example.po.dream.dream.DreamDepositVirAddressDO;
import org.example.po.mapper.dream.DreamDepositTypeMapper;
import org.example.service.dream.DreamDepositTypeRuleService;
import org.example.service.dream.DreamDepositTypeService;
import org.example.service.dream.DreamDepositVirAddressService;
import org.example.vo.dream.deposit.DepositAddressVO;
import org.example.vo.dream.deposit.DepositTypeVO;
import org.example.vo.dream.deposit.DepositTypeRuleVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
* @author Administrator
* @description 针对表【dream_deposit_type】的数据库操作Service实现
* @createDate 2023-09-17 03:12:43
*/
@Service
public class DreamDepositTypeServiceImpl extends ServiceImpl<DreamDepositTypeMapper, DreamDepositTypeDO>
    implements DreamDepositTypeService{



    @Resource
    private DreamDepositTypeMapper depositTypeMapper;
    @Resource
    private DreamDepositVirAddressService depositVirAddressService;
    @Resource
    private DreamDepositTypeRuleService depositTypeRuleService;
    @Resource
    private MapperFacade mapperFacade;


    /**
     * 充值规则设置
     * @return
     */
    @Override
    public List<DepositTypeVO> getDepositMethod() {

        List<DreamDepositTypeDO> dreamDepositTypeDOS = depositTypeMapper.selectList( new LambdaQueryWrapperX<DreamDepositTypeDO>()
                .eq(DreamDepositTypeDO::getStatus, CommonStatusEnum.ENABLE)
                .orderByDesc(DreamDepositTypeDO::getSort)
                .orderByDesc(DreamDepositTypeDO::getCreateTime));

        if (CollectionUtil.isEmpty(dreamDepositTypeDOS)){
            return Lists.newArrayList();
        }

        List<Long> collect = dreamDepositTypeDOS.stream().map(DreamDepositTypeDO::getId).collect(Collectors.toList());

        List<DreamDepositTypeRuleDO> dreamDepositTypeRuleDOS = depositTypeRuleService.selectByDepositTypeIds(collect);

        Map<Long, DreamDepositTypeRuleDO> typeRuleDOMap = dreamDepositTypeRuleDOS.stream()
                .collect(Collectors.groupingBy(DreamDepositTypeRuleDO::getDepositTypeId, Collectors.collectingAndThen(Collectors.toList(), r -> r.get(0))));


        /**
         * 获取收款地址
         */

        List<DreamDepositVirAddressDO> dreamDepositVirAddressDOS = depositVirAddressService.selectByDepositTypeId(collect);
        Map<Long, List<DreamDepositVirAddressDO>> virAddress = dreamDepositVirAddressDOS.stream().collect(Collectors.groupingBy(DreamDepositVirAddressDO::getDepositTypeId));


        List<DepositTypeVO> depositTypeVOList = new ArrayList<>();
        for (DreamDepositTypeDO dreamDepositTypeDO : dreamDepositTypeDOS) {
            DepositTypeVO depositTypeVO = mapperFacade.map(dreamDepositTypeDO,DepositTypeVO.class);

            /**
             * 设置充值规则
             */
            Optional.ofNullable(typeRuleDOMap.get(depositTypeVO.getId())).ifPresent(dreamDepositTypeRuleDO -> {
                depositTypeVO.setRule( mapperFacade.map(dreamDepositTypeRuleDO, DepositTypeRuleVO.class));
            });

            // 设置虚拟币地址
            Optional.ofNullable(virAddress.get(depositTypeVO.getId())).ifPresent(dreamDepositVirAddressDOS1 -> {
                List<DepositAddressVO> depositAddressVOS = mapperFacade.mapAsList(dreamDepositVirAddressDOS1, DepositAddressVO.class);
                Map<String, List<DepositAddressVO>> addressVoMap = depositAddressVOS.stream().collect(Collectors.groupingBy(DepositAddressVO::getProtocol));
                depositTypeVO.setAddress(addressVoMap);
            });

            depositTypeVOList.add(depositTypeVO);
        }

        return depositTypeVOList;
    }

    @Override
    public DreamDepositTypeDO selectByDepositId(Long depositTypeId) {

        return  getBaseMapper().selectOne(new LambdaQueryWrapperX<DreamDepositTypeDO>()
                .eq(DreamDepositTypeDO::getId,depositTypeId)
                .eq(DreamDepositTypeDO::getStatus,CommonStatusEnum.ENABLE));
    }
}




