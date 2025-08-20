package org.example.system.convert.user;


import org.example.system.db.mysql.po.system.AdminUserDO;
import org.example.system.db.mysql.po.system.DeptDO;
import org.example.system.db.mysql.po.system.PostDO;
import org.example.system.db.mysql.po.system.RoleDO;
import org.example.system.vo.user.AdminUserRespDTO;
import org.example.system.vo.user.vo.profile.UserProfileRespVO;
import org.example.system.vo.user.vo.profile.UserProfileUpdatePasswordReqVO;
import org.example.system.vo.user.vo.profile.UserProfileUpdateReqVO;
import org.example.system.vo.user.vo.user.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * The interface User convert.
 */
@Mapper
public interface UserConvert {

    /**
     * The constant INSTANCE.
     */
    UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);

    /**
     * Convert user page item resp vo.
     *
     * @param bean the bean
     * @return the user page item resp vo
     */
    UserPageItemRespVO convert(AdminUserDO bean);

    /**
     * Convert user page item resp vo . dept.
     *
     * @param bean the bean
     * @return the user page item resp vo . dept
     */
    UserPageItemRespVO.Dept convert(DeptDO bean);

    /**
     * Convert admin user do.
     *
     * @param bean the bean
     * @return the admin user do
     */
    AdminUserDO convert(UserCreateReqVO bean);

    /**
     * Convert admin user do.
     *
     * @param bean the bean
     * @return the admin user do
     */
    AdminUserDO convert(UserUpdateReqVO bean);

    /**
     * Convert 02 user excel vo.
     *
     * @param bean the bean
     * @return the user excel vo
     */
    UserExcelVO convert02(AdminUserDO bean);

    /**
     * Convert admin user do.
     *
     * @param bean the bean
     * @return the admin user do
     */
    AdminUserDO convert(UserImportExcelVO bean);

    /**
     * Convert 03 user profile resp vo.
     *
     * @param bean the bean
     * @return the user profile resp vo
     */
    UserProfileRespVO convert03(AdminUserDO bean);

    /**
     * Convert list list.
     *
     * @param list the list
     * @return the list
     */
    List<UserProfileRespVO.Role> convertList(List<RoleDO> list);

    /**
     * Convert 02 user profile resp vo . dept.
     *
     * @param bean the bean
     * @return the user profile resp vo . dept
     */
    UserProfileRespVO.Dept convert02(DeptDO bean);

    /**
     * Convert admin user do.
     *
     * @param bean the bean
     * @return the admin user do
     */
    AdminUserDO convert(UserProfileUpdateReqVO bean);

    /**
     * Convert admin user do.
     *
     * @param bean the bean
     * @return the admin user do
     */
    AdminUserDO convert(UserProfileUpdatePasswordReqVO bean);

    /**
     * Convert list 02 list.
     *
     * @param list the list
     * @return the list
     */
    List<UserProfileRespVO.Post> convertList02(List<PostDO> list);

//    List<UserProfileRespVO.SocialUser> convertList03(List<SocialUserDO> list);

    /**
     * Convert list 04 list.
     *
     * @param list the list
     * @return the list
     */
    List<UserSimpleRespVO> convertList04(List<AdminUserDO> list);

    /**
     * Convert 4 admin user resp dto.
     *
     * @param bean the bean
     * @return the admin user resp dto
     */
    AdminUserRespDTO convert4(AdminUserDO bean);

    /**
     * Convert list 4 list.
     *
     * @param users the users
     * @return the list
     */
    List<AdminUserRespDTO> convertList4(List<AdminUserDO> users);

}
