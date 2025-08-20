package org.example.system.db.mysql.mapper.system;

import org.apache.ibatis.annotations.Mapper;
import org.example.config.mybatisplus.BaseMapperX;
import org.example.config.mybatisplus.LambdaQueryWrapperX;
import org.example.pojo.PageResult;
import org.example.system.db.mysql.po.system.AdminUserDO;
import org.example.system.vo.user.vo.user.UserExportReqVO;
import org.example.system.vo.user.vo.user.UserPageReqVO;

import java.util.Collection;
import java.util.List;

/**
 * The interface Admin user mapper.
 */
@Mapper
public interface AdminUserMapper extends BaseMapperX<AdminUserDO> {

    /**
     * Select by username admin user do.
     *
     * @param username the username
     * @return the admin user do
     */
    default AdminUserDO selectByUsername(String username) {
        return selectOne(AdminUserDO::getUsername, username);
    }

    /**
     * Select by email admin user do.
     *
     * @param email the email
     * @return the admin user do
     */
    default AdminUserDO selectByEmail(String email) {
        return selectOne(AdminUserDO::getEmail, email);
    }

    /**
     * Select by mobile admin user do.
     *
     * @param mobile the mobile
     * @return the admin user do
     */
    default AdminUserDO selectByMobile(String mobile) {
        return selectOne(AdminUserDO::getMobile, mobile);
    }

    /**
     * Select page page result.
     *
     * @param reqVO   the req vo
     * @param deptIds the dept ids
     * @return the page result
     */
    default PageResult<AdminUserDO> selectPage(UserPageReqVO reqVO, Collection<Long> deptIds) {
        return selectPage(reqVO, new LambdaQueryWrapperX<AdminUserDO>()
                .likeIfPresent(AdminUserDO::getUsername, reqVO.getUsername())
                .likeIfPresent(AdminUserDO::getMobile, reqVO.getMobile())
                .eqIfPresent(AdminUserDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(AdminUserDO::getCreateTime, reqVO.getCreateTime())
                .inIfPresent(AdminUserDO::getDeptId, deptIds)
                .orderByDesc(AdminUserDO::getId));
    }

    /**
     * Select list list.
     *
     * @param reqVO   the req vo
     * @param deptIds the dept ids
     * @return the list
     */
    default List<AdminUserDO> selectList(UserExportReqVO reqVO, Collection<Long> deptIds) {
        return selectList(new LambdaQueryWrapperX<AdminUserDO>()
                .likeIfPresent(AdminUserDO::getUsername, reqVO.getUsername())
                .likeIfPresent(AdminUserDO::getMobile, reqVO.getMobile())
                .eqIfPresent(AdminUserDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(AdminUserDO::getCreateTime, reqVO.getCreateTime())
                .inIfPresent(AdminUserDO::getDeptId, deptIds));
    }

    /**
     * Select list by nickname list.
     *
     * @param nickname the nickname
     * @return the list
     */
    default List<AdminUserDO> selectListByNickname(String nickname) {
        return selectList(new LambdaQueryWrapperX<AdminUserDO>().like(AdminUserDO::getNickname, nickname));
    }

    /**
     * Select list by status list.
     *
     * @param status the status
     * @return the list
     */
    default List<AdminUserDO> selectListByStatus(Integer status) {
        return selectList(AdminUserDO::getStatus, status);
    }

    /**
     * Select list by dept ids list.
     *
     * @param deptIds the dept ids
     * @return the list
     */
    default List<AdminUserDO> selectListByDeptIds(Collection<Long> deptIds) {
        return selectList(AdminUserDO::getDeptId, deptIds);
    }

}
