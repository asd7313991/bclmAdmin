package org.example.system.convert.dict;


import org.example.po.system.DictDataDO;
import org.example.pojo.PageResult;
import org.example.vo.system.dict.DictDataRespDTO;
import org.example.vo.system.dict.vo.data.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * The interface Dict data convert.
 */
@Mapper
public interface DictDataConvert {

    /**
     * The constant INSTANCE.
     */
    DictDataConvert INSTANCE = Mappers.getMapper(DictDataConvert.class);

    /**
     * Convert list list.
     *
     * @param list the list
     * @return the list
     */
    List<DictDataSimpleRespVO> convertList(List<DictDataDO> list);

    /**
     * Convert dict data resp vo.
     *
     * @param bean the bean
     * @return the dict data resp vo
     */
    DictDataRespVO convert(DictDataDO bean);

    /**
     * Convert page page result.
     *
     * @param page the page
     * @return the page result
     */
    PageResult<DictDataRespVO> convertPage(PageResult<DictDataDO> page);

    /**
     * Convert dict data do.
     *
     * @param bean the bean
     * @return the dict data do
     */
    DictDataDO convert(DictDataUpdateReqVO bean);

    /**
     * Convert dict data do.
     *
     * @param bean the bean
     * @return the dict data do
     */
    DictDataDO convert(DictDataCreateReqVO bean);

    /**
     * Convert list 02 list.
     *
     * @param bean the bean
     * @return the list
     */
    List<DictDataExcelVO> convertList02(List<DictDataDO> bean);

    /**
     * Convert 02 dict data resp dto.
     *
     * @param bean the bean
     * @return the dict data resp dto
     */
    DictDataRespDTO convert02(DictDataDO bean);

}
