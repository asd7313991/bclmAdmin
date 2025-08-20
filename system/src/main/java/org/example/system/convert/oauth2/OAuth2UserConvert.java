//package org.example.convert.oauth2;
//
//
//import org.example.po.AdminUserDO;
//import org.example.po.DeptDO;
//import org.example.po.PostDO;
//import org.example.vo.user.vo.profile.UserProfileUpdateReqVO;
//import org.mapstruct.Mapper;
//import org.mapstruct.factory.Mappers;
//
//import java.util.List;
//
//@Mapper
//public interface OAuth2UserConvert {
//
//    OAuth2UserConvert INSTANCE = Mappers.getMapper(OAuth2UserConvert.class);
//
//    OAuth2UserInfoRespVO convert(AdminUserDO bean);
//    OAuth2UserInfoRespVO.Dept convert(DeptDO dept);
//    List<OAuth2UserInfoRespVO.Post> convertList(List<PostDO> list);
//
//    UserProfileUpdateReqVO convert(OAuth2UserUpdateReqVO bean);
//
//}
