package org.example.system.convert.errorcode;


import org.example.pojo.PageResult;
import org.example.system.db.mysql.po.system.ErrorCodeDO;
import org.example.system.vo.errorcode.ErrorCodeAutoGenerateReqDTO;
import org.example.system.vo.errorcode.ErrorCodeRespDTO;
import org.example.system.vo.errorcode.vo.ErrorCodeCreateReqVO;
import org.example.system.vo.errorcode.vo.ErrorCodeExcelVO;
import org.example.system.vo.errorcode.vo.ErrorCodeRespVO;
import org.example.system.vo.errorcode.vo.ErrorCodeUpdateReqVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 错误码 Convert
 *
 * @author 后台源码
 */
@Mapper
public interface ErrorCodeConvert {

    /**
     * The constant INSTANCE.
     */
    ErrorCodeConvert INSTANCE = Mappers.getMapper(ErrorCodeConvert.class);

    /**
     * Convert error code do.
     *
     * @param bean the bean
     * @return the error code do
     */
    ErrorCodeDO convert(ErrorCodeCreateReqVO bean);

    /**
     * Convert error code do.
     *
     * @param bean the bean
     * @return the error code do
     */
    ErrorCodeDO convert(ErrorCodeUpdateReqVO bean);

    /**
     * Convert error code resp vo.
     *
     * @param bean the bean
     * @return the error code resp vo
     */
    ErrorCodeRespVO convert(ErrorCodeDO bean);

    /**
     * Convert list list.
     *
     * @param list the list
     * @return the list
     */
    List<ErrorCodeRespVO> convertList(List<ErrorCodeDO> list);

    /**
     * Convert page page result.
     *
     * @param page the page
     * @return the page result
     */
    PageResult<ErrorCodeRespVO> convertPage(PageResult<ErrorCodeDO> page);

    /**
     * Convert list 02 list.
     *
     * @param list the list
     * @return the list
     */
    List<ErrorCodeExcelVO> convertList02(List<ErrorCodeDO> list);

    /**
     * Convert error code do.
     *
     * @param bean the bean
     * @return the error code do
     */
    ErrorCodeDO convert(ErrorCodeAutoGenerateReqDTO bean);

    /**
     * Convert list 03 list.
     *
     * @param list the list
     * @return the list
     */
    List<ErrorCodeRespDTO> convertList03(List<ErrorCodeDO> list);

}
