package org.example.system.db.mysql.mapper.system;

import org.example.config.mybatisplus.BaseMapperX;
import org.example.config.mybatisplus.LambdaQueryWrapperX;
import org.example.pojo.PageResult;
import org.example.system.db.mysql.po.system.TenantPackageDO;
import org.example.system.vo.tenant.vo.packages.TenantPackagePageReqVO;

import java.util.List;

/**
 * The interface Tenant package mapper.
 *
 * @description 针对表 【system_tenant_package(租户套餐表)】的数据库操作Mapper
 * @createDate 2023 -09-10 11:27:18
 * @Entity generator.domain.SystemTenantPackage
 */
public interface TenantPackageMapper extends BaseMapperX<TenantPackageDO> {

    /**
     * Select page page result.
     *
     * @param reqVO the req vo
     * @return the page result
     */
    default PageResult<TenantPackageDO> selectPage(TenantPackagePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<TenantPackageDO>()
                .likeIfPresent(TenantPackageDO::getName, reqVO.getName())
                .eqIfPresent(TenantPackageDO::getStatus, reqVO.getStatus())
                .likeIfPresent(TenantPackageDO::getRemark, reqVO.getRemark())
                .betweenIfPresent(TenantPackageDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(TenantPackageDO::getId));
    }

    /**
     * Select list by status list.
     *
     * @param status the status
     * @return the list
     */
    default List<TenantPackageDO> selectListByStatus(Integer status) {
        return selectList(TenantPackageDO::getStatus, status);
    }

}
