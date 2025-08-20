package org.example.system.convert.permission;

import org.example.system.db.mysql.po.system.RoleDO;
import org.example.system.system.permission.bo.RoleCreateReqBO;
import org.example.system.vo.permission.vo.role.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * The interface Role convert.
 */
@Mapper
public interface RoleConvert {

    /**
     * The constant INSTANCE.
     */
    RoleConvert INSTANCE = Mappers.getMapper(RoleConvert.class);

    /**
     * Convert role do.
     *
     * @param bean the bean
     * @return the role do
     */
    RoleDO convert(RoleUpdateReqVO bean);

    /**
     * Convert role resp vo.
     *
     * @param bean the bean
     * @return the role resp vo
     */
    RoleRespVO convert(RoleDO bean);

    /**
     * Convert role do.
     *
     * @param bean the bean
     * @return the role do
     */
    RoleDO convert(RoleCreateReqVO bean);

    /**
     * Convert list 02 list.
     *
     * @param list the list
     * @return the list
     */
    List<RoleSimpleRespVO> convertList02(List<RoleDO> list);

    /**
     * Convert list 03 list.
     *
     * @param list the list
     * @return the list
     */
    List<RoleExcelVO> convertList03(List<RoleDO> list);

    /**
     * Convert role do.
     *
     * @param bean the bean
     * @return the role do
     */
    RoleDO convert(RoleCreateReqBO bean);

}
