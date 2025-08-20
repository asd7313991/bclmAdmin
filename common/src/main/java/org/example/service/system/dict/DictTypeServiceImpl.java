package org.example.service.system.dict;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.Lists;
import ma.glasnost.orika.MapperFacade;
import org.apache.commons.lang3.StringUtils;
import org.example.config.mybatisplus.LambdaQueryWrapperX;
import org.example.enums.CommonStatusEnum;
import org.example.enums.ErrorCodeConstants;
import org.example.exception.util.ServiceExceptionUtil;
import org.example.po.mapper.system.DictTypeMapper;
import org.example.po.system.DictDataDO;
import org.example.po.system.DictTypeDO;
import org.example.pojo.PageResult;
import org.example.util.date.LocalDateTimeUtils;
import org.example.vo.system.dict.vo.DictDataVO;
import org.example.vo.system.dict.vo.DictReq;
import org.example.vo.system.dict.vo.DictRspVo;
import org.example.vo.system.dict.vo.type.DictTypeCreateReqVO;
import org.example.vo.system.dict.vo.type.DictTypeExportReqVO;
import org.example.vo.system.dict.vo.type.DictTypePageReqVO;
import org.example.vo.system.dict.vo.type.DictTypeUpdateReqVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * 字典类型 Service 实现类
 *
 * @author 后台源码
 */
@Service
public class DictTypeServiceImpl implements DictTypeService {

    @Resource
    private DictDataService dictDataService;
    @Resource
    private MapperFacade mapperFacade;
    @Resource
    private DictTypeMapper dictTypeMapper;

    @Override
    public PageResult<DictTypeDO> getDictTypePage(DictTypePageReqVO reqVO) {
        return dictTypeMapper.selectPage(reqVO);
    }

    @Override
    public List<DictTypeDO> getDictTypeList(DictTypeExportReqVO reqVO) {
        return dictTypeMapper.selectList(reqVO);
    }

    @Override
    public DictTypeDO getDictType(Long id) {
        return dictTypeMapper.selectById(id);
    }

    @Override
    public DictTypeDO getDictType(String type) {
        return dictTypeMapper.selectByType(type);
    }

    @Override
    public Long createDictType(DictTypeCreateReqVO reqVO) {
        // 校验正确性
        validateDictTypeForCreateOrUpdate(null, reqVO.getName(), reqVO.getType());

        // 插入字典类型
        DictTypeDO dictType = mapperFacade.map(reqVO, DictTypeDO.class);
        dictType.setDeletedTime(LocalDateTimeUtils.EMPTY); // 唯一索引，避免 null 值
        dictTypeMapper.insert(dictType);
        return dictType.getId();
    }

    @Override
    public void updateDictType(DictTypeUpdateReqVO reqVO) {
        // 校验正确性
        validateDictTypeForCreateOrUpdate(reqVO.getId(), reqVO.getName(), null);

        // 更新字典类型
        DictTypeDO updateObj = mapperFacade.map(reqVO, DictTypeDO.class);
        dictTypeMapper.updateById(updateObj);
    }

    @Override
    public void deleteDictType(Long id) {
        // 校验是否存在
        DictTypeDO dictType = validateDictTypeExists(id);
        // 校验是否有字典数据
        if (dictDataService.countByDictType(dictType.getType()) > 0) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.DICT_TYPE_HAS_CHILDREN);
        }
        // 删除字典类型
        dictTypeMapper.updateToDelete(id, LocalDateTime.now());
    }

    @Override
    public List<DictTypeDO> getDictTypeList() {
        return dictTypeMapper.selectList();
    }

    @Override
    public List<DictDataVO> getDictListByType(DictReq dictReq) {
        List<String> collect = new ArrayList<>();
        if (StringUtils.isNotBlank(dictReq.getDictCodesStr())){
            String[] split = dictReq.getDictCodesStr().split(",");
            collect = Arrays.stream(split).collect(Collectors.toList());
        }
        List<DictTypeDO> dictTypeDOS = dictTypeMapper.selectList(new LambdaQueryWrapperX<DictTypeDO>()
                .inIfPresent(DictTypeDO::getType, dictReq.getDictCodes())
                .inIfPresent(DictTypeDO::getType, collect )
                .eq(DictTypeDO::getStatus, CommonStatusEnum.ENABLE));

        if (CollectionUtil.isEmpty(dictTypeDOS)) {
            return Lists.newArrayList();
        }
        List<String> dictTypeList = dictTypeDOS.stream().map(DictTypeDO::getType).distinct().collect(Collectors.toList());

        List<DictDataDO> dictDataDOS = dictDataService.selectByTypeList(dictTypeList);
        if (CollectionUtil.isEmpty(dictDataDOS)) {
            return Lists.newArrayList();
        }
        Map<String, List<DictDataDO>> dictDataMap =
                dictDataDOS.stream().collect(Collectors.groupingBy(DictDataDO::getDictType));

        List<DictDataVO> dictDataVOS = new ArrayList<>();

        for (String dictTypeCode : dictTypeList) {
            DictDataVO dictDataVO = new DictDataVO();
            dictDataVO.setType(dictTypeCode);
            dictDataVO.setValue(mapperFacade.mapAsList(dictDataMap.get(dictTypeCode), DictRspVo.class));
            dictDataVOS.add(dictDataVO);
        }

        return dictDataVOS;
    }

    private void validateDictTypeForCreateOrUpdate(Long id, String name, String type) {
        // 校验自己存在
        validateDictTypeExists(id);
        // 校验字典类型的名字的唯一性
        validateDictTypeNameUnique(id, name);
        // 校验字典类型的类型的唯一性
        validateDictTypeUnique(id, type);
    }

    @VisibleForTesting
    void validateDictTypeNameUnique(Long id, String name) {
        DictTypeDO dictType = dictTypeMapper.selectByName(name);
        if (dictType == null) {
            return;
        }
        // 如果 id 为空，说明不用比较是否为相同 id 的字典类型
        if (id == null) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.DICT_TYPE_NAME_DUPLICATE);
        }
        if (!dictType.getId().equals(id)) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.DICT_TYPE_NAME_DUPLICATE);
        }
    }

    @VisibleForTesting
    void validateDictTypeUnique(Long id, String type) {
        if (StrUtil.isEmpty(type)) {
            return;
        }
        DictTypeDO dictType = dictTypeMapper.selectByType(type);
        if (dictType == null) {
            return;
        }
        // 如果 id 为空，说明不用比较是否为相同 id 的字典类型
        if (id == null) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.DICT_TYPE_TYPE_DUPLICATE);
        }
        if (!dictType.getId().equals(id)) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.DICT_TYPE_TYPE_DUPLICATE);
        }
    }

    @VisibleForTesting
    DictTypeDO validateDictTypeExists(Long id) {
        if (id == null) {
            return null;
        }
        DictTypeDO dictType = dictTypeMapper.selectById(id);
        if (dictType == null) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.DICT_TYPE_NOT_EXISTS);
        }
        return dictType;
    }


}
