package org.example.system.db.mysql.mapper.system;

import org.example.config.mybatisplus.BaseMapperX;
import org.example.config.mybatisplus.LambdaQueryWrapperX;
import org.example.pojo.PageResult;
import org.example.system.db.mysql.po.system.BannerDO;
import org.example.system.vo.banner.BannerPageReqVO;

/**
 * The interface Banner mapper.
 */
public interface BannerMapper extends BaseMapperX<BannerDO> {


    /**
     * Select page page result.
     *
     * @param reqVO the req vo
     * @return the page result
     */
    default PageResult<BannerDO> selectPage(BannerPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<BannerDO>()
                .likeIfPresent(BannerDO::getTitle, reqVO.getTitle())
                .eqIfPresent(BannerDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(BannerDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(BannerDO::getSort));
    }

}
