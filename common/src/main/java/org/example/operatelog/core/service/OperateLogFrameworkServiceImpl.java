//package org.example.operatelog.core.service;
//
//import cn.hutool.core.bean.BeanUtil;
//import lombok.RequiredArgsConstructor;
//import org.springframework.scheduling.annotation.Async;
//
///**
// * 操作日志 Framework Service 实现类
// *
// * 基于 {@link OperateLogApi} 实现，记录操作日志
// *
// * @author
// */
//@RequiredArgsConstructor
//public class OperateLogFrameworkServiceImpl implements OperateLogFrameworkService {
//
//    @Override
//    @Async
//    public void createOperateLog(OperateLogObj operateLog) {
//        OperateLogCreateReqDTO reqDTO = BeanUtil.copyProperties(operateLog, OperateLogCreateReqDTO.class);
//        operateLogApi.createOperateLog(reqDTO);
//    }
//
//}
