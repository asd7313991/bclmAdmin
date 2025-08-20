package org.example.system.convert.dept;

import org.example.pojo.PageResult;
import org.example.system.db.mysql.po.system.PostDO;
import org.example.system.vo.dept.vo.post.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * The interface Post convert.
 */
@Mapper
public interface PostConvert {

    /**
     * The constant INSTANCE.
     */
    PostConvert INSTANCE = Mappers.getMapper(PostConvert.class);

    /**
     * Convert list 02 list.
     *
     * @param list the list
     * @return the list
     */
    List<PostSimpleRespVO> convertList02(List<PostDO> list);

    /**
     * Convert page page result.
     *
     * @param page the page
     * @return the page result
     */
    PageResult<PostRespVO> convertPage(PageResult<PostDO> page);

    /**
     * Convert post resp vo.
     *
     * @param id the id
     * @return the post resp vo
     */
    PostRespVO convert(PostDO id);

    /**
     * Convert post do.
     *
     * @param bean the bean
     * @return the post do
     */
    PostDO convert(PostCreateReqVO bean);

    /**
     * Convert post do.
     *
     * @param reqVO the req vo
     * @return the post do
     */
    PostDO convert(PostUpdateReqVO reqVO);

    /**
     * Convert list 03 list.
     *
     * @param list the list
     * @return the list
     */
    List<PostExcelVO> convertList03(List<PostDO> list);

}
