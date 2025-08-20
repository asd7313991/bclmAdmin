package org.example.po.mapper.dream;

import org.apache.ibatis.annotations.Param;
import org.example.config.mybatisplus.BaseMapperX;
import org.example.config.mybatisplus.LambdaQueryWrapperX;
import org.example.po.dream.dream.DreamUUserDO;
import org.example.pojo.PageResult;
import org.example.service.system.homepage.HomePageReqVO;
import org.example.vo.dream.user.DreamUUserCountPerDayVO;
import org.example.vo.dream.user.DreamUUserCountVO;
import org.example.vo.dream.user.DreamUUserPageReqVO;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @description 针对表【dream_u_user】的数据库操作Mapper
 * @createDate 2023-09-13 13:32:04
 * @Entity generator.domain.DreamUUserDO
 */
public interface DreamUUserMapper extends BaseMapperX<DreamUUserDO> {

    default PageResult<DreamUUserDO> selectPage(DreamUUserPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<DreamUUserDO>()
                .likeIfPresent(DreamUUserDO::getUserName, reqVO.getUserName())
                .eqIfPresent(DreamUUserDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(DreamUUserDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(DreamUUserDO::getCreateTime));
    }

    List<DreamUUserCountVO> countUserGroupBy(@Param("reqVO") HomePageReqVO reqVO);

    List<DreamUUserCountPerDayVO> countUserPerDayGroupBy(@Param("reqVO") HomePageReqVO reqVO);
}




