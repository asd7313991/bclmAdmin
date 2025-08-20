package org.example.system.convert.notify;

import org.example.po.system.NotifyMessageDO;
import org.example.pojo.PageResult;
import org.example.vo.system.dict.vo.message.NotifyMessageRespVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 站内信 Convert
 *
 * @author xrcoder
 */
@Mapper
public interface NotifyMessageConvert {

    /**
     * The constant INSTANCE.
     */
    NotifyMessageConvert INSTANCE = Mappers.getMapper(NotifyMessageConvert.class);

    /**
     * Convert notify message resp vo.
     *
     * @param bean the bean
     * @return the notify message resp vo
     */
    NotifyMessageRespVO convert(NotifyMessageDO bean);

    /**
     * Convert list list.
     *
     * @param list the list
     * @return the list
     */
    List<NotifyMessageRespVO> convertList(List<NotifyMessageDO> list);

    /**
     * Convert page page result.
     *
     * @param page the page
     * @return the page result
     */
    PageResult<NotifyMessageRespVO> convertPage(PageResult<NotifyMessageDO> page);


}
