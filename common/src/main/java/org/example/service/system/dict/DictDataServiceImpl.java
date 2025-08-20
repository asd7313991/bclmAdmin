package org.example.service.system.dict;

import cn.hutool.core.collection.CollUtil;
import com.google.common.annotations.VisibleForTesting;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import org.example.config.mybatisplus.LambdaQueryWrapperX;
import org.example.enums.CommonStatusEnum;
import org.example.enums.ErrorCodeConstants;
import org.example.exception.util.ServiceExceptionUtil;
import org.example.po.mapper.system.DictDataMapper;
import org.example.po.system.DictDataDO;
import org.example.po.system.DictTypeDO;
import org.example.pojo.PageResult;
import org.example.util.collection.CollectionUtils;
import org.example.vo.system.dict.vo.data.DictDataCreateReqVO;
import org.example.vo.system.dict.vo.data.DictDataExportReqVO;
import org.example.vo.system.dict.vo.data.DictDataPageReqVO;
import org.example.vo.system.dict.vo.data.DictDataUpdateReqVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;


/**
 * 字典数据 Service 实现类
 *
 * @author ruoyi
 */
@Service
@Slf4j
public class DictDataServiceImpl implements DictDataService {

    /**
     * 排序 dictType > sort
     */
    private static final Comparator<DictDataDO> COMPARATOR_TYPE_AND_SORT = Comparator
            .comparing(DictDataDO::getDictType)
            .thenComparingInt(DictDataDO::getSort);

    @Resource
    private DictTypeService dictTypeService;
    @Resource
    private DictDataMapper dictDataMapper;
    @Resource
    private MapperFacade mapperFacade;

    @Override
    public List<DictDataDO> getDictDataList() {
        List<DictDataDO> list = dictDataMapper.selectList();
        list.sort(COMPARATOR_TYPE_AND_SORT);
        return list;
    }

    @Override
    public PageResult<DictDataDO> getDictDataPage(DictDataPageReqVO reqVO) {
        return dictDataMapper.selectPage(reqVO);
    }

    @Override
    public List<DictDataDO> getDictDataList(DictDataExportReqVO reqVO) {
        List<DictDataDO> list = dictDataMapper.selectList(reqVO);
        list.sort(COMPARATOR_TYPE_AND_SORT);
        return list;
    }

    @Override
    public DictDataDO getDictData(Long id) {
        return dictDataMapper.selectById(id);
    }

    @Override
    public Long createDictData(DictDataCreateReqVO reqVO) {
        // 校验正确性
        validateDictDataForCreateOrUpdate(null, reqVO.getValue(), reqVO.getDictType());

        // 插入字典类型

        DictDataDO dictData = mapperFacade.map(reqVO, DictDataDO.class);
        dictDataMapper.insert(dictData);
        return dictData.getId();
    }

    @Override
    public void updateDictData(DictDataUpdateReqVO reqVO) {
        // 校验正确性
        validateDictDataForCreateOrUpdate(reqVO.getId(), reqVO.getValue(), reqVO.getDictType());

        // 更新字典类型
        DictDataDO updateObj = mapperFacade.map(reqVO, DictDataDO.class);
        dictDataMapper.updateById(updateObj);
    }

    @Override
    public void deleteDictData(Long id) {
        // 校验是否存在
        validateDictDataExists(id);

        // 删除字典数据
        dictDataMapper.deleteById(id);
    }

    @Override
    public long countByDictType(String dictType) {
        return dictDataMapper.selectCountByDictType(dictType);
    }

    private void validateDictDataForCreateOrUpdate(Long id, String value, String dictType) {
        // 校验自己存在
        validateDictDataExists(id);
        // 校验字典类型有效
        validateDictTypeExists(dictType);
        // 校验字典数据的值的唯一性
        validateDictDataValueUnique(id, dictType, value);
    }

    @VisibleForTesting
    public void validateDictDataValueUnique(Long id, String dictType, String value) {
        DictDataDO dictData = dictDataMapper.selectByDictTypeAndValue(dictType, value);
        if (dictData == null) {
            return;
        }
        // 如果 id 为空，说明不用比较是否为相同 id 的字典数据
        if (id == null) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.DICT_DATA_VALUE_DUPLICATE);
        }
        if (!dictData.getId().equals(id)) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.DICT_DATA_VALUE_DUPLICATE);
        }
    }

    @VisibleForTesting
    public void validateDictDataExists(Long id) {
        if (id == null) {
            return;
        }
        DictDataDO dictData = dictDataMapper.selectById(id);
        if (dictData == null) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.DICT_DATA_NOT_EXISTS);
        }
    }

    @VisibleForTesting
    public void validateDictTypeExists(String type) {
        DictTypeDO dictType = dictTypeService.getDictType(type);
        if (dictType == null) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.DICT_TYPE_NOT_EXISTS);
        }
        if (!CommonStatusEnum.ENABLE.getStatus().equals(dictType.getStatus())) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.DICT_TYPE_NOT_ENABLE);
        }
    }

    @Override
    public void validateDictDataList(String dictType, Collection<String> values) {
        if (CollUtil.isEmpty(values)) {
            return;
        }
        Map<String, DictDataDO> dictDataMap = CollectionUtils.convertMap(
                dictDataMapper.selectByDictTypeAndValues(dictType, values), DictDataDO::getValue);
        // 校验
        values.forEach(value -> {
            DictDataDO dictData = dictDataMap.get(value);
            if (dictData == null) {
                throw ServiceExceptionUtil.exception(ErrorCodeConstants.DICT_DATA_NOT_EXISTS);
            }
            if (!CommonStatusEnum.ENABLE.getStatus().equals(dictData.getStatus())) {
                throw ServiceExceptionUtil.exception(ErrorCodeConstants.DICT_DATA_NOT_ENABLE, dictData.getLabel());
            }
        });
    }

    @Override
    public DictDataDO getDictData(String dictType, String value) {
        return dictDataMapper.selectByDictTypeAndValue(dictType, value);
    }

    @Override
    public DictDataDO parseDictData(String dictType, String label) {
        return dictDataMapper.selectByDictTypeAndLabel(dictType, label);
    }

    @Override
    public List<DictDataDO> selectByTypeList(List<String> collect) {
        return dictDataMapper.selectList(new LambdaQueryWrapperX<DictDataDO>()
                .in(DictDataDO::getDictType, collect)
                .eq(DictDataDO::getStatus, CommonStatusEnum.ENABLE));
    }

}
