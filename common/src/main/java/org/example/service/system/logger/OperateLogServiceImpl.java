package org.example.service.system.logger;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import org.example.po.mapper.system.OperateLogMapper;
import org.example.po.system.OperateLogDO;
import org.example.pojo.PageResult;
import org.example.util.string.StrUtils;
import org.example.vo.system.operatelog.OperateLogCreateReqDTO;
import org.example.vo.system.operatelog.OperateLogExportReqVO;
import org.example.vo.system.operatelog.OperateLogPageReqVO;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

import static org.example.po.system.OperateLogDO.JAVA_METHOD_ARGS_MAX_LENGTH;
import static org.example.po.system.OperateLogDO.RESULT_MAX_LENGTH;


@Service("operateLogServiceImpl")
@Validated
@Slf4j
public class OperateLogServiceImpl implements OperateLogService {

    @Resource
    private OperateLogMapper operateLogMapper;
    @Resource
    private MapperFacade mapperFacade;

    @Override
    @Async
    public void saveOperateLog(OperateLogCreateReqDTO operateLogCreateReqDTO){
        this.createOperateLog(operateLogCreateReqDTO);
    }



    @Override
    public void createOperateLog(OperateLogCreateReqDTO createReqDTO) {
        OperateLogDO logDO = mapperFacade.map(createReqDTO, OperateLogDO.class);
        logDO.setJavaMethodArgs(StrUtils.maxLength(logDO.getJavaMethodArgs(), JAVA_METHOD_ARGS_MAX_LENGTH));
        logDO.setResultData(StrUtils.maxLength(logDO.getResultData(), RESULT_MAX_LENGTH));
        operateLogMapper.insert(logDO);
    }

    @Override
    public PageResult<OperateLogDO> getOperateLogPage(OperateLogPageReqVO reqVO) {
        // 处理基于用户昵称的查询
        Collection<Long> userIds = null;
//        if (StrUtil.isNotEmpty(reqVO.getUserNickname())) {
//            userIds = ;
//            if ( CollUtil.isEmpty(userIds)) {
//                return PageResult.empty();
//            }
//        }
        // 查询分页
        return operateLogMapper.selectPage(reqVO, userIds);
    }

    @Override
    public List<OperateLogDO> getOperateLogList(OperateLogExportReqVO reqVO) {
        // 处理基于用户昵称的查询
        Collection<Long> userIds = null;
        if (StrUtil.isNotEmpty(reqVO.getUserNickname())) {
//            userIds = null;
//            if (CollUtil.isEmpty(userIds)) {
//                return Collections.emptyList();
//            }
        }
        // 查询列表
        return operateLogMapper.selectList(reqVO, userIds);
    }

}
