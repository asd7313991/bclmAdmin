//package org.example.convert.logger;
//
//import org.example.db.mysql.po.system.AdminUserDO;
//import org.example.po.system.OperateLogDO;
//import org.example.pojo.PageResult;
//import org.example.util.collection.MapUtils;
//import org.example.vo.logger.vo.operatelog.OperateLogExcelVO;
//import org.example.vo.logger.vo.operatelog.OperateLogRespVO;
//import org.mapstruct.Mapper;
//import org.mapstruct.factory.Mappers;
//
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//import static org.example.enums.LoginResultEnum.SUCCESS;
//
//
//@Mapper
//public interface OperateLogConvert {
//
//    OperateLogConvert INSTANCE = Mappers.getMapper(OperateLogConvert.class);
//
//    OperateLogDO convert(OperateLogCreateReqDTO bean);
//
//    PageResult<OperateLogRespVO> convertPage(PageResult<OperateLogDO> page);
//
//    OperateLogRespVO convert(OperateLogDO bean);
//
//    default List<OperateLogExcelVO> convertList(List<OperateLogDO> list, Map<Long, AdminUserDO> userMap) {
//        return list.stream().map(operateLog -> {
//            OperateLogExcelVO excelVO = convert02(operateLog);
//            MapUtils.findAndThen(userMap, operateLog.getUserId(), user -> excelVO.setUserNickname(user.getNickname()));
//            excelVO.setSuccessStr(SUCCESS.equals(operateLog.getResultCode()) ? "成功" : "失败");
//            return excelVO;
//        }).collect(Collectors.toList());
//    }
//
//    OperateLogExcelVO convert02(OperateLogDO bean);
//
//}
