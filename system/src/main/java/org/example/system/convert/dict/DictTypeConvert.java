package org.example.system.convert.dict;

import org.example.po.system.DictTypeDO;
import org.example.pojo.PageResult;
import org.example.vo.system.dict.vo.type.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * The interface Dict type convert.
 */
@Mapper
public interface DictTypeConvert {

    /**
     * The constant INSTANCE.
     */
    DictTypeConvert INSTANCE = Mappers.getMapper(DictTypeConvert.class);

    /**
     * Convert page page result.
     *
     * @param bean the bean
     * @return the page result
     */
    PageResult<DictTypeRespVO> convertPage(PageResult<DictTypeDO> bean);

    /**
     * Convert dict type resp vo.
     *
     * @param bean the bean
     * @return the dict type resp vo
     */
    DictTypeRespVO convert(DictTypeDO bean);

    /**
     * Convert dict type do.
     *
     * @param bean the bean
     * @return the dict type do
     */
    DictTypeDO convert(DictTypeCreateReqVO bean);

    /**
     * Convert dict type do.
     *
     * @param bean the bean
     * @return the dict type do
     */
    DictTypeDO convert(DictTypeUpdateReqVO bean);

    /**
     * Convert list list.
     *
     * @param list the list
     * @return the list
     */
    List<DictTypeSimpleRespVO> convertList(List<DictTypeDO> list);

    /**
     * Convert list 02 list.
     *
     * @param list the list
     * @return the list
     */
    List<DictTypeExcelVO> convertList02(List<DictTypeDO> list);

}
