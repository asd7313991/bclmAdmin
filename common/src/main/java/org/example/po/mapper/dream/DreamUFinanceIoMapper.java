package org.example.po.mapper.dream;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import org.example.config.mybatisplus.BaseMapperX;
import org.example.config.mybatisplus.LambdaQueryWrapperX;
import org.example.po.dream.dream.DreamUFinanceIoDO;
import org.example.pojo.PageResult;
import org.example.service.system.homepage.HomePageReqVO;
import org.example.vo.dream.financeIo.DreamUFinanceIoAmountPerDayVO;
import org.example.vo.dream.financeIo.DreamUFinanceIoAmountVO;
import org.example.vo.dream.financeIo.DreamUFinanceIoReqVo;
import org.example.vo.dream.financeIo.DreamUFinanceIoVO;
import org.example.vo.dream.user.DreamUUserCountPerDayVO;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @description 针对表【dream_u_finance_io】的数据库操作Mapper
 * @createDate 2023-09-13 13:32:04
 * @Entity generator.domain.DreamUFinanceIoDO
 */
public interface DreamUFinanceIoMapper extends BaseMapperX<DreamUFinanceIoDO> {

    default PageResult<DreamUFinanceIoDO> selectPage(DreamUFinanceIoReqVo pageVO) {
        DateTime offset = null;
        if (pageVO.getDays() != null){
            offset = DateUtil.offset(DateUtil.parseDate(DateUtil.now()).toJdkDate(), DateField.DAY_OF_YEAR,  - pageVO.getDays());
        }

        return selectPage(pageVO, new LambdaQueryWrapperX<DreamUFinanceIoDO>()
                .eqIfPresent(DreamUFinanceIoDO::getId, pageVO.getId())
                .eqIfPresent(DreamUFinanceIoDO::getUserId, pageVO.getUserId())
                .eqIfPresent(DreamUFinanceIoDO::getType, pageVO.getType())
                .eqIfPresent(DreamUFinanceIoDO::getPlatformId, pageVO.getPlatformId())
                .eqIfPresent(DreamUFinanceIoDO::getStatus, pageVO.getStatus())
                .eqIfPresent(DreamUFinanceIoDO::getSelfOrderId, pageVO.getSelfOrderId())
                .eqIfPresent(DreamUFinanceIoDO::getThirdOrderId, pageVO.getThirdOrderId())
                .eqIfPresent(DreamUFinanceIoDO::getSubmitOrderId, pageVO.getSubmitOrderId())
                .geIfPresent(DreamUFinanceIoDO::getCreateTime, offset)
                .betweenIfPresent(DreamUFinanceIoDO::getCreateTime, pageVO.getCreateTime())
                .orderByDesc(DreamUFinanceIoDO::getCreateTime));
    }


    IPage<DreamUFinanceIoVO> selectPageNew(IPage<DreamUFinanceIoDO> mpPage, @Param("pageVo") DreamUFinanceIoReqVo pageVO) ;

    List<DreamUFinanceIoAmountVO> sumAmountGroupBy(@Param("reqVO") HomePageReqVO reqVO);

    List<DreamUFinanceIoAmountPerDayVO> sumAmountPerDayGroupBy(@Param("reqVO") HomePageReqVO reqVO);
}




