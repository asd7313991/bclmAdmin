package org.example.system.db.mysql.mapper.system;

import org.apache.ibatis.annotations.Mapper;
import org.example.config.mybatisplus.BaseMapperX;
import org.example.config.mybatisplus.LambdaQueryWrapperX;
import org.example.system.db.mysql.po.system.DeptDO;
import org.example.system.vo.dept.vo.dept.DeptListReqVO;

import java.util.Collection;
import java.util.List;

/**
 * The interface Dept mapper.
 */
@Mapper
public interface DeptMapper extends BaseMapperX<DeptDO> {

    /**
     * Select list list.
     *
     * @param reqVO the req vo
     * @return the list
     */
    default List<DeptDO> selectList(DeptListReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<DeptDO>()
                .likeIfPresent(DeptDO::getName, reqVO.getName())
                .eqIfPresent(DeptDO::getStatus, reqVO.getStatus()));
    }

    /**
     * Select by parent id and name dept do.
     *
     * @param parentId the parent id
     * @param name     the name
     * @return the dept do
     */
    default DeptDO selectByParentIdAndName(Long parentId, String name) {
        return selectOne(DeptDO::getParentId, parentId, DeptDO::getName, name);
    }

    /**
     * Select count by parent id long.
     *
     * @param parentId the parent id
     * @return the long
     */
    default Long selectCountByParentId(Long parentId) {
        return selectCount(DeptDO::getParentId, parentId);
    }

    /**
     * Select list by parent id list.
     *
     * @param parentIds the parent ids
     * @return the list
     */
    default List<DeptDO> selectListByParentId(Collection<Long> parentIds) {
        return selectList(DeptDO::getParentId, parentIds);
    }

}
