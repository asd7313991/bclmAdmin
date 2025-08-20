package org.example.system.db.mysql.mapper.system;


import org.apache.ibatis.annotations.Mapper;
import org.example.config.mybatisplus.BaseMapperX;
import org.example.config.mybatisplus.LambdaQueryWrapperX;
import org.example.pojo.PageResult;
import org.example.system.db.mysql.po.system.ErrorCodeDO;
import org.example.system.vo.errorcode.vo.ErrorCodeExportReqVO;
import org.example.system.vo.errorcode.vo.ErrorCodePageReqVO;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

/**
 * The interface Error code mapper.
 */
@Mapper
public interface ErrorCodeMapper extends BaseMapperX<ErrorCodeDO> {

    /**
     * Select page page result.
     *
     * @param reqVO the req vo
     * @return the page result
     */
    default PageResult<ErrorCodeDO> selectPage(ErrorCodePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ErrorCodeDO>()
                .eqIfPresent(ErrorCodeDO::getType, reqVO.getType())
                .likeIfPresent(ErrorCodeDO::getApplicationName, reqVO.getApplicationName())
                .eqIfPresent(ErrorCodeDO::getCode, reqVO.getCode())
                .likeIfPresent(ErrorCodeDO::getMessage, reqVO.getMessage())
                .betweenIfPresent(ErrorCodeDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ErrorCodeDO::getCode));
    }

    /**
     * Select list list.
     *
     * @param reqVO the req vo
     * @return the list
     */
    default List<ErrorCodeDO> selectList(ErrorCodeExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<ErrorCodeDO>()
                .eqIfPresent(ErrorCodeDO::getType, reqVO.getType())
                .likeIfPresent(ErrorCodeDO::getApplicationName, reqVO.getApplicationName())
                .eqIfPresent(ErrorCodeDO::getCode, reqVO.getCode())
                .likeIfPresent(ErrorCodeDO::getMessage, reqVO.getMessage())
                .betweenIfPresent(ErrorCodeDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ErrorCodeDO::getCode));
    }

    /**
     * Select list by codes list.
     *
     * @param codes the codes
     * @return the list
     */
    default List<ErrorCodeDO> selectListByCodes(Collection<Integer> codes) {
        return selectList(ErrorCodeDO::getCode, codes);
    }

    /**
     * Select by code error code do.
     *
     * @param code the code
     * @return the error code do
     */
    default ErrorCodeDO selectByCode(Integer code) {
        return selectOne(ErrorCodeDO::getCode, code);
    }

    /**
     * Select list by application name and update time gt list.
     *
     * @param applicationName the application name
     * @param minUpdateTime   the min update time
     * @return the list
     */
    default List<ErrorCodeDO> selectListByApplicationNameAndUpdateTimeGt(String applicationName, LocalDateTime minUpdateTime) {
        return selectList(new LambdaQueryWrapperX<ErrorCodeDO>().eq(ErrorCodeDO::getApplicationName, applicationName)
                .gtIfPresent(ErrorCodeDO::getModifyTime, minUpdateTime));
    }

}
