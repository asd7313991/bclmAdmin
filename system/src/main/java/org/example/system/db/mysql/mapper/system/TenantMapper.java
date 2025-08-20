package org.example.system.db.mysql.mapper.system;

import org.example.config.mybatisplus.BaseMapperX;
import org.example.config.mybatisplus.LambdaQueryWrapperX;
import org.example.pojo.PageResult;
import org.example.system.db.mysql.po.system.TenantDO;
import org.example.system.vo.tenant.vo.tenant.TenantExportReqVO;
import org.example.system.vo.tenant.vo.tenant.TenantPageReqVO;

import java.util.List;

/**
 * The interface Tenant mapper.
 *
 * @description 针对表 【system_tenant(租户表)】的数据库操作Mapper
 * @createDate 2023 -09-10 11:25:33
 * @Entity generator.domain.SystemTenantPO
 */
public interface TenantMapper extends BaseMapperX<TenantDO> {

    /**
     * Select page page result.
     *
     * @param reqVO the req vo
     * @return the page result
     */
    default PageResult<TenantDO> selectPage(TenantPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<TenantDO>()
                .likeIfPresent(TenantDO::getName, reqVO.getName())
                .likeIfPresent(TenantDO::getContactName, reqVO.getContactName())
                .likeIfPresent(TenantDO::getContactMobile, reqVO.getContactMobile())
                .eqIfPresent(TenantDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(TenantDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(TenantDO::getId));
    }

    /**
     * Select list list.
     *
     * @param reqVO the req vo
     * @return the list
     */
    default List<TenantDO> selectList(TenantExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<TenantDO>()
                .likeIfPresent(TenantDO::getName, reqVO.getName())
                .likeIfPresent(TenantDO::getContactName, reqVO.getContactName())
                .likeIfPresent(TenantDO::getContactMobile, reqVO.getContactMobile())
                .eqIfPresent(TenantDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(TenantDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(TenantDO::getId));
    }

    /**
     * Select by name tenant do.
     *
     * @param name the name
     * @return the tenant do
     */
    default TenantDO selectByName(String name) {
        return selectOne(TenantDO::getName, name);
    }

    /**
     * Select count by package id long.
     *
     * @param packageId the package id
     * @return the long
     */
    default Long selectCountByPackageId(Long packageId) {
        return selectCount(TenantDO::getPackageId, packageId);
    }

    /**
     * Select list by package id list.
     *
     * @param packageId the package id
     * @return the list
     */
    default List<TenantDO> selectListByPackageId(Long packageId) {
        return selectList(TenantDO::getPackageId, packageId);
    }

}
