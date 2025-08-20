package org.example.system.system.banner;


import org.example.pojo.PageResult;
import org.example.system.db.mysql.po.system.BannerDO;
import org.example.system.vo.banner.BannerCreateReqVO;
import org.example.system.vo.banner.BannerPageReqVO;
import org.example.system.vo.banner.BannerUpdateReqVO;

import javax.validation.Valid;
import java.util.List;

/**
 * 首页 Banner Service 接口
 *
 * @author xia
 */
public interface BannerService {

    /**
     * 创建 Banner
     *
     * @param createReqVO 创建信息
     * @return 编号 long
     */
    Long createBanner(@Valid BannerCreateReqVO createReqVO);

    /**
     * 更新 Banner
     *
     * @param updateReqVO 更新信息
     */
    void updateBanner(@Valid BannerUpdateReqVO updateReqVO);

    /**
     * 删除 Banner
     *
     * @param id 编号
     */
    void deleteBanner(Long id);

    /**
     * 获得 Banner
     *
     * @param id 编号
     * @return Banner banner
     */
    BannerDO getBanner(Long id);

    /**
     * 获得所有 Banner列表
     *
     * @return Banner列表 banner list
     */
    List<BannerDO> getBannerList();

    /**
     * 获得 Banner 分页
     *
     * @param pageReqVO 分页查询
     * @return Banner分页 banner page
     */
    PageResult<BannerDO> getBannerPage(BannerPageReqVO pageReqVO);

}
