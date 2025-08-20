package org.example.service.dream.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import ma.glasnost.orika.MapperFacade;
import org.example.config.mybatisplus.LambdaQueryWrapperX;
import org.example.enums.CommonStatusEnum;
import org.example.po.dream.dream.DreamUVirAddressDO;
import org.example.po.dream.dream.DreamWithdrawTypeDO;
import org.example.po.dream.dream.DreamWithdrawTypeRuleDO;
import org.example.po.mapper.dream.DreamWithdrawTypeMapper;
import org.example.po.mapper.dream.DreamWithdrawTypeRuleMapper;
import org.example.service.dream.DreamUVirAddressService;
import org.example.service.dream.DreamWithdrawTypeService;
import org.example.vo.dream.withdraw.WithdrawTypeRuleVO;
import org.example.vo.dream.withdraw.WithdrawTypeVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author a5.0
 * @description 针对表【dream_withdraw_type】的数据库操作Service实现
 * @createDate 2023-09-17 09:51:06
 */
@Service
public class DreamWithdrawTypeServiceImpl extends ServiceImpl<DreamWithdrawTypeMapper, DreamWithdrawTypeDO>
        implements DreamWithdrawTypeService {

    @Resource
    private DreamWithdrawTypeMapper dreamWithdrawTypeMapper;
    @Resource
    private DreamWithdrawTypeRuleMapper dreamWithdrawTypeRuleMapper;
    @Resource
    private MapperFacade mapperFacade;
    @Resource
    private DreamUVirAddressService dreamUVirAddressService;


    @Override
    public List<WithdrawTypeVO> getWithdrawMethod(Long userId) {

        List<DreamWithdrawTypeDO> withdrawTypeDOS = dreamWithdrawTypeMapper.selectList(new LambdaQueryWrapperX<DreamWithdrawTypeDO>()
                .eq(DreamWithdrawTypeDO::getStatus, CommonStatusEnum.ENABLE)
                .orderByDesc(DreamWithdrawTypeDO::getSort)
                .orderByDesc(DreamWithdrawTypeDO::getCreateTime));

        if (CollectionUtil.isEmpty(withdrawTypeDOS)) {
            return Lists.newArrayList();
        }

        List<Long> collect = withdrawTypeDOS.stream().map(DreamWithdrawTypeDO::getId).collect(Collectors.toList());

        List<DreamWithdrawTypeRuleDO> dreamDepositTypeRuleDOS = dreamWithdrawTypeRuleMapper.selectByWithdrawTypeIds(collect);

        Map<Long, DreamWithdrawTypeRuleDO> typeRuleDOMap = dreamDepositTypeRuleDOS.stream()
                .collect(Collectors.groupingBy(DreamWithdrawTypeRuleDO::getWithdrawTypeId, Collectors.collectingAndThen(Collectors.toList(), r -> r.get(0))));
        Map<String, List<DreamUVirAddressDO>> uVirAddress = new HashMap<>();

        if (userId != null) {
            List<DreamUVirAddressDO> dreamUVirAddressDOS = dreamUVirAddressService.setectByUserId(userId);
            if (CollectionUtil.isNotEmpty(dreamUVirAddressDOS)){
                uVirAddress = dreamUVirAddressDOS.stream().collect(Collectors.groupingBy(DreamUVirAddressDO::getProtocol));
            }
        }


        List<WithdrawTypeVO> withdrawTypeVOS = new ArrayList<>();
        for (DreamWithdrawTypeDO withdrawTypeDO : withdrawTypeDOS) {
            WithdrawTypeVO withdrawTypeVO = mapperFacade.map(withdrawTypeDO, WithdrawTypeVO.class);

            /**
             * 设置充值规则
             */
            Map<String, List<DreamUVirAddressDO>> finalUVirAddress = uVirAddress;
            Optional.ofNullable(typeRuleDOMap.get(withdrawTypeDO.getId())).ifPresent(withdrawTypeRuleDO -> {
                withdrawTypeVO.setRule(mapperFacade.map(withdrawTypeRuleDO, WithdrawTypeRuleVO.class));
                Map<String,List<DreamUVirAddressDO>> withdrawType = new HashMap<>();
                withdrawType.put("TRC20", finalUVirAddress.get("TRC20"));
                withdrawType.put("ERC20",finalUVirAddress.get("ERC20"));
                withdrawTypeVO.setAddress(withdrawType);
            });
            withdrawTypeVOS.add(withdrawTypeVO);
        }

        return withdrawTypeVOS;
    }


    @Override
    public DreamWithdrawTypeDO selectByWithdrawTypeId(Long withdrawId) {
        DreamWithdrawTypeDO withdrawTypeDO = dreamWithdrawTypeMapper.selectOne(new LambdaQueryWrapperX<DreamWithdrawTypeDO>()
                .eq(DreamWithdrawTypeDO::getId, withdrawId)
                .eq(DreamWithdrawTypeDO::getStatus, CommonStatusEnum.ENABLE));

        return withdrawTypeDO;
    }
}




