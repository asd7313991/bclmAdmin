package org.example.po.mapper.system;

import org.apache.ibatis.annotations.Mapper;
import org.example.config.mybatisplus.BaseMapperX;
import org.example.config.mybatisplus.LambdaQueryWrapperX;
import org.example.exception.enums.GlobalErrorCodeConstants;
import org.example.po.system.OperateLogDO;
import org.example.pojo.PageResult;
import org.example.vo.system.operatelog.OperateLogExportReqVO;
import org.example.vo.system.operatelog.OperateLogPageReqVO;

import java.util.Collection;
import java.util.List;

/**
 * @author D588
 */
@Mapper
public interface OperateLogMapper extends BaseMapperX<OperateLogDO> {

    default PageResult<OperateLogDO> selectPage(OperateLogPageReqVO reqVO, Collection<Long> userIds) {
        return selectPage(reqVO, buildQuery(reqVO, userIds));
    }

    default List<OperateLogDO> selectList(OperateLogExportReqVO reqVO, Collection<Long> userIds) {
        return selectList(this.buildQuery(reqVO, userIds));
    }


    public default LambdaQueryWrapperX<OperateLogDO> buildQuery(OperateLogPageReqVO reqVO, Collection<Long> userIds) {
        LambdaQueryWrapperX<OperateLogDO> query = new LambdaQueryWrapperX<OperateLogDO>()
                .likeIfPresent(OperateLogDO::getModule, reqVO.getModule())
                .inIfPresent(OperateLogDO::getUserId, userIds)
                .eqIfPresent(OperateLogDO::getType, reqVO.getType())
                .betweenIfPresent(OperateLogDO::getStartTime, reqVO.getStartTime());
        if (Boolean.TRUE.equals(reqVO.getSuccess())) {
            query.eq(OperateLogDO::getResultCode, GlobalErrorCodeConstants.SUCCESS.getCode());
        } else if (Boolean.FALSE.equals(reqVO.getSuccess())) {
            query.gt(OperateLogDO::getResultCode, GlobalErrorCodeConstants.SUCCESS.getCode());
        }
        query.orderByDesc(OperateLogDO::getId); // 降序
        return query;
    }
}
