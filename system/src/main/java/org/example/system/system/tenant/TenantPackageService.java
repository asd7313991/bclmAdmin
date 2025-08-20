package org.example.system.system.tenant;


import org.example.pojo.PageResult;
import org.example.system.db.mysql.po.system.TenantPackageDO;
import org.example.system.vo.tenant.vo.packages.TenantPackageCreateReqVO;
import org.example.system.vo.tenant.vo.packages.TenantPackagePageReqVO;
import org.example.system.vo.tenant.vo.packages.TenantPackageUpdateReqVO;

import javax.validation.Valid;
import java.util.List;

/**
 * 租户套餐 Service 接口
 *
 * @author 后台源码
 */
public interface TenantPackageService {

    /**
     * 创建租户套餐
     *
     * @param createReqVO 创建信息
     * @return 编号 long
     */
    Long createTenantPackage(@Valid TenantPackageCreateReqVO createReqVO);

    /**
     * 更新租户套餐
     *
     * @param updateReqVO 更新信息
     */
    void updateTenantPackage(@Valid TenantPackageUpdateReqVO updateReqVO);

    /**
     * 删除租户套餐
     *
     * @param id 编号
     */
    void deleteTenantPackage(Long id);

    /**
     * 获得租户套餐
     *
     * @param id 编号
     * @return 租户套餐 tenant package
     */
    TenantPackageDO getTenantPackage(Long id);

    /**
     * 获得租户套餐分页
     *
     * @param pageReqVO 分页查询
     * @return 租户套餐分页 tenant package page
     */
    PageResult<TenantPackageDO> getTenantPackagePage(TenantPackagePageReqVO pageReqVO);

    /**
     * 校验租户套餐
     *
     * @param id 编号
     * @return 租户套餐 tenant package do
     */
    TenantPackageDO validTenantPackage(Long id);

    /**
     * 获得指定状态的租户套餐列表
     *
     * @param status 状态
     * @return 租户套餐 tenant package list by status
     */
    List<TenantPackageDO> getTenantPackageListByStatus(Integer status);

}
