package org.example.system.convert.notice;

import org.example.po.system.NoticeDO;
import org.example.pojo.PageResult;
import org.example.vo.system.notice.NoticeCreateReqVO;
import org.example.vo.system.notice.NoticeRespVO;
import org.example.vo.system.notice.NoticeUpdateReqVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * The interface Notice convert.
 */
@Mapper
public interface NoticeConvert {

    /**
     * The constant INSTANCE.
     */
    NoticeConvert INSTANCE = Mappers.getMapper(NoticeConvert.class);

    /**
     * Convert page page result.
     *
     * @param page the page
     * @return the page result
     */
    PageResult<NoticeRespVO> convertPage(PageResult<NoticeDO> page);

    /**
     * Convert notice resp vo.
     *
     * @param bean the bean
     * @return the notice resp vo
     */
    NoticeRespVO convert(NoticeDO bean);

    /**
     * Convert notice do.
     *
     * @param bean the bean
     * @return the notice do
     */
    NoticeDO convert(NoticeUpdateReqVO bean);

    /**
     * Convert notice do.
     *
     * @param bean the bean
     * @return the notice do
     */
    NoticeDO convert(NoticeCreateReqVO bean);

}
