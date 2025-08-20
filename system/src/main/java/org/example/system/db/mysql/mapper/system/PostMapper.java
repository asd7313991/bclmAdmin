package org.example.system.db.mysql.mapper.system;

import org.apache.ibatis.annotations.Mapper;
import org.example.config.mybatisplus.BaseMapperX;
import org.example.config.mybatisplus.LambdaQueryWrapperX;
import org.example.pojo.PageResult;
import org.example.system.db.mysql.po.system.PostDO;
import org.example.system.vo.dept.vo.post.PostExportReqVO;
import org.example.system.vo.dept.vo.post.PostPageReqVO;

import java.util.Collection;
import java.util.List;

/**
 * The interface Post mapper.
 */
@Mapper
public interface PostMapper extends BaseMapperX<PostDO> {

    /**
     * Select list list.
     *
     * @param ids      the ids
     * @param statuses the statuses
     * @return the list
     */
    default List<PostDO> selectList(Collection<Long> ids, Collection<Integer> statuses) {
        return selectList(new LambdaQueryWrapperX<PostDO>()
                .inIfPresent(PostDO::getId, ids)
                .inIfPresent(PostDO::getStatus, statuses));
    }

    /**
     * Select page page result.
     *
     * @param reqVO the req vo
     * @return the page result
     */
    default PageResult<PostDO> selectPage(PostPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<PostDO>()
                .likeIfPresent(PostDO::getCode, reqVO.getCode())
                .likeIfPresent(PostDO::getName, reqVO.getName())
                .eqIfPresent(PostDO::getStatus, reqVO.getStatus())
                .orderByDesc(PostDO::getId));
    }

    /**
     * Select list list.
     *
     * @param reqVO the req vo
     * @return the list
     */
    default List<PostDO> selectList(PostExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<PostDO>()
                .likeIfPresent(PostDO::getCode, reqVO.getCode())
                .likeIfPresent(PostDO::getName, reqVO.getName())
                .eqIfPresent(PostDO::getStatus, reqVO.getStatus()));
    }

    /**
     * Select by name post do.
     *
     * @param name the name
     * @return the post do
     */
    default PostDO selectByName(String name) {
        return selectOne(PostDO::getName, name);
    }

    /**
     * Select by code post do.
     *
     * @param code the code
     * @return the post do
     */
    default PostDO selectByCode(String code) {
        return selectOne(PostDO::getCode, code);
    }

}
