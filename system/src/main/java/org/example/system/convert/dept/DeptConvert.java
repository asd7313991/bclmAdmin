package org.example.system.convert.dept;

import org.example.system.db.mysql.po.system.DeptDO;
import org.example.system.vo.dept.DeptRespDTO;
import org.example.system.vo.dept.vo.dept.DeptCreateReqVO;
import org.example.system.vo.dept.vo.dept.DeptRespVO;
import org.example.system.vo.dept.vo.dept.DeptSimpleRespVO;
import org.example.system.vo.dept.vo.dept.DeptUpdateReqVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * The interface Dept convert.
 */
@Mapper
public interface DeptConvert {

    /**
     * The constant INSTANCE.
     */
    DeptConvert INSTANCE = Mappers.getMapper(DeptConvert.class);

    /**
     * Convert list list.
     *
     * @param list the list
     * @return the list
     */
    List<DeptRespVO> convertList(List<DeptDO> list);

    /**
     * Convert list 02 list.
     *
     * @param list the list
     * @return the list
     */
    List<DeptSimpleRespVO> convertList02(List<DeptDO> list);

    /**
     * Convert dept resp vo.
     *
     * @param bean the bean
     * @return the dept resp vo
     */
    DeptRespVO convert(DeptDO bean);

    /**
     * Convert dept do.
     *
     * @param bean the bean
     * @return the dept do
     */
    DeptDO convert(DeptCreateReqVO bean);

    /**
     * Convert dept do.
     *
     * @param bean the bean
     * @return the dept do
     */
    DeptDO convert(DeptUpdateReqVO bean);

    /**
     * Convert list 03 list.
     *
     * @param list the list
     * @return the list
     */
    List<DeptRespDTO> convertList03(List<DeptDO> list);

    /**
     * Convert 03 dept resp dto.
     *
     * @param bean the bean
     * @return the dept resp dto
     */
    DeptRespDTO convert03(DeptDO bean);

}
