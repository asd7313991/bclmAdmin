package org.example.system.db.mysql.mapper.system;

import org.apache.ibatis.annotations.Mapper;
import org.example.config.mybatisplus.BaseMapperX;
import org.example.config.mybatisplus.LambdaQueryWrapperX;
import org.example.po.BaseEntity;
import org.example.pojo.PageResult;
import org.example.system.db.mysql.po.system.RoleDO;
import org.example.system.vo.permission.vo.role.RoleExportReqVO;
import org.example.system.vo.permission.vo.role.RolePageReqVO;
import org.springframework.lang.Nullable;

import java.util.Collection;
import java.util.List;

/**
 * The interface Role mapper.
 */
@Mapper
public interface RoleMapper extends BaseMapperX<RoleDO> {

    /**
     * Select page page result.
     *
     * @param reqVO the req vo
     * @return the page result
     */
    default PageResult<RoleDO> selectPage(RolePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<RoleDO>()
                .likeIfPresent(RoleDO::getName, reqVO.getName())
                .likeIfPresent(RoleDO::getCode, reqVO.getCode())
                .eqIfPresent(RoleDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(BaseEntity::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(RoleDO::getId));
    }

    /**
     * Select list list.
     *
     * @param reqVO the req vo
     * @return the list
     */
    default List<RoleDO> selectList(RoleExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<RoleDO>()
                .likeIfPresent(RoleDO::getName, reqVO.getName())
                .likeIfPresent(RoleDO::getCode, reqVO.getCode())
                .eqIfPresent(RoleDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(BaseEntity::getCreateTime, reqVO.getCreateTime()));
    }

    /**
     * Select by name role do.
     *
     * @param name the name
     * @return the role do
     */
    default RoleDO selectByName(String name) {
        return selectOne(RoleDO::getName, name);
    }

    /**
     * Select by code role do.
     *
     * @param code the code
     * @return the role do
     */
    default RoleDO selectByCode(String code) {
        return selectOne(RoleDO::getCode, code);
    }

    /**
     * Select list by status list.
     *
     * @param statuses the statuses
     * @return the list
     */
    default List<RoleDO> selectListByStatus(@Nullable Collection<Integer> statuses) {
        return selectList(RoleDO::getStatus, statuses);
    }

//    default List<RoleDO> selectListByTenantId(Long tenantId){
//        return selectList(new LambdaQueryWrapperX<RoleDO>().eq(RoleDO::getTenantId, tenantId));
//    }
}
