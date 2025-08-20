//package org.example.convert.logger;
//
//import org.example.po.system.LoginLogDO;
//import org.example.pojo.PageResult;
//import org.example.vo.logger.vo.loginlog.LoginLogExcelVO;
//import org.example.vo.logger.vo.loginlog.LoginLogRespVO;
//import org.example.vo.logger.vo.operatelog.LoginLogCreateReqDTO;
//import org.mapstruct.Mapper;
//import org.mapstruct.factory.Mappers;
//
//import java.util.List;
//
//@Mapper
//public interface LoginLogConvert {
//
//    LoginLogConvert INSTANCE = Mappers.getMapper(LoginLogConvert.class);
//
//    PageResult<LoginLogRespVO> convertPage(PageResult<LoginLogDO> page);
//
//    List<LoginLogExcelVO> convertList(List<LoginLogDO> list);
//
//    LoginLogDO convert(LoginLogCreateReqDTO bean);
//
//}
