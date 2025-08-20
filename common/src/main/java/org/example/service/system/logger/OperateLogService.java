package org.example.service.system.logger;



import org.example.po.system.OperateLogDO;
import org.example.pojo.PageResult;
import org.example.vo.system.operatelog.OperateLogCreateReqDTO;
import org.example.vo.system.operatelog.OperateLogExportReqVO;
import org.example.vo.system.operatelog.OperateLogPageReqVO;
import org.springframework.scheduling.annotation.Async;

import java.util.List;

/**
 * 操作日志 Service 接口
 *
 * @author 后台源码
 */
public interface OperateLogService {

    @Async
    void  saveOperateLog(OperateLogCreateReqDTO operateLogCreateReqDTO);

    /**
     * 记录操作日志
     *
     * @param createReqDTO 操作日志请求
     */
    void createOperateLog(OperateLogCreateReqDTO createReqDTO);

    /**
     * 获得操作日志分页列表
     *
     * @param reqVO 分页条件
     * @return 操作日志分页列表
     */
    PageResult<OperateLogDO> getOperateLogPage(OperateLogPageReqVO reqVO);

    /**
     * 获得操作日志列表
     *
     * @param reqVO 列表条件
     * @return 日志列表
     */
    List<OperateLogDO> getOperateLogList(OperateLogExportReqVO reqVO);

}
