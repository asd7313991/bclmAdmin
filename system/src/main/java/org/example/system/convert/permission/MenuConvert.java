package org.example.system.convert.permission;

import org.example.system.db.mysql.po.system.MenuDO;
import org.example.system.vo.permission.vo.menu.MenuCreateReqVO;
import org.example.system.vo.permission.vo.menu.MenuRespVO;
import org.example.system.vo.permission.vo.menu.MenuSimpleRespVO;
import org.example.system.vo.permission.vo.menu.MenuUpdateReqVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * The interface Menu convert.
 */
@Mapper
public interface MenuConvert {

    /**
     * The constant INSTANCE.
     */
    MenuConvert INSTANCE = Mappers.getMapper(MenuConvert.class);

    /**
     * Convert list list.
     *
     * @param list the list
     * @return the list
     */
    List<MenuRespVO> convertList(List<MenuDO> list);

    /**
     * Convert menu do.
     *
     * @param bean the bean
     * @return the menu do
     */
    MenuDO convert(MenuCreateReqVO bean);

    /**
     * Convert menu do.
     *
     * @param bean the bean
     * @return the menu do
     */
    MenuDO convert(MenuUpdateReqVO bean);

    /**
     * Convert menu resp vo.
     *
     * @param bean the bean
     * @return the menu resp vo
     */
    MenuRespVO convert(MenuDO bean);

    /**
     * Convert list 02 list.
     *
     * @param list the list
     * @return the list
     */
    List<MenuSimpleRespVO> convertList02(List<MenuDO> list);

}
