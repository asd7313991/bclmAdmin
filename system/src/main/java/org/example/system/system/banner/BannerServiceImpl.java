package org.example.system.system.banner;


import ma.glasnost.orika.MapperFacade;
import org.example.pojo.PageResult;
import org.example.system.db.mysql.mapper.system.BannerMapper;
import org.example.system.db.mysql.po.system.BannerDO;
import org.example.system.vo.banner.BannerCreateReqVO;
import org.example.system.vo.banner.BannerPageReqVO;
import org.example.system.vo.banner.BannerUpdateReqVO;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.List;

import static org.example.enums.ErrorCodeConstants.BANNER_NOT_EXISTS;
import static org.example.exception.util.ServiceExceptionUtil.exception;

/**
 * 首页 banner 实现类
 *
 * @author xia
 */
@Service
@Validated
public class BannerServiceImpl implements BannerService {

    @Resource
    private BannerMapper bannerMapper;
    @Resource
    private MapperFacade mapperFacade;


    @Override
    public Long createBanner(BannerCreateReqVO createReqVO) {
        // 插入
        BannerDO banner = mapperFacade.map(createReqVO,BannerDO.class);
        bannerMapper.insert(banner);
        // 返回
        return banner.getId();
    }

    @Override
    public void updateBanner(BannerUpdateReqVO updateReqVO) {
        // 校验存在
        this.validateBannerExists(updateReqVO.getId());
        // 更新
        BannerDO updateObj = mapperFacade.map(updateReqVO,BannerDO.class);
        bannerMapper.updateById(updateObj);
    }

    @Override
    public void deleteBanner(Long id) {
        // 校验存在
        this.validateBannerExists(id);
        // 删除
        bannerMapper.deleteById(id);
    }

    private void validateBannerExists(Long id) {
        if (bannerMapper.selectById(id) == null) {
            throw exception(BANNER_NOT_EXISTS);
        }
    }

    @Override
    public BannerDO getBanner(Long id) {
        return bannerMapper.selectById(id);
    }

    @Override
    public List<BannerDO> getBannerList() {
        return bannerMapper.selectList();
    }

    @Override
    public PageResult<BannerDO> getBannerPage(BannerPageReqVO pageReqVO) {
        return bannerMapper.selectPage(pageReqVO);
    }

}
