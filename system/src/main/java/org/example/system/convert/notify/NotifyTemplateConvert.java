package org.example.system.convert.notify;

import org.example.po.system.NotifyTemplateDO;
import org.example.pojo.PageResult;
import org.example.vo.system.dict.vo.template.NotifyTemplateCreateReqVO;
import org.example.vo.system.dict.vo.template.NotifyTemplateRespVO;
import org.example.vo.system.dict.vo.template.NotifyTemplateUpdateReqVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 站内信模版 Convert
 *
 * @author xrcoder
 */
@Mapper
public interface NotifyTemplateConvert {

    /**
     * The constant INSTANCE.
     */
    NotifyTemplateConvert INSTANCE = Mappers.getMapper(NotifyTemplateConvert.class);

    /**
     * Convert notify template do.
     *
     * @param bean the bean
     * @return the notify template do
     */
    NotifyTemplateDO convert(NotifyTemplateCreateReqVO bean);

    /**
     * Convert notify template do.
     *
     * @param bean the bean
     * @return the notify template do
     */
    NotifyTemplateDO convert(NotifyTemplateUpdateReqVO bean);

    /**
     * Convert notify template resp vo.
     *
     * @param bean the bean
     * @return the notify template resp vo
     */
    NotifyTemplateRespVO convert(NotifyTemplateDO bean);

    /**
     * Convert list list.
     *
     * @param list the list
     * @return the list
     */
    List<NotifyTemplateRespVO> convertList(List<NotifyTemplateDO> list);

    /**
     * Convert page page result.
     *
     * @param page the page
     * @return the page result
     */
    PageResult<NotifyTemplateRespVO> convertPage(PageResult<NotifyTemplateDO> page);

}
