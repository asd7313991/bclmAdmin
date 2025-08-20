package org.example.system.db.mysql.mapper.system;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.apache.ibatis.annotations.Mapper;
import org.example.config.mybatisplus.BaseMapperX;
import org.example.config.mybatisplus.LambdaQueryWrapperX;
import org.example.system.db.mysql.po.system.UserPostDO;

import java.util.Collection;
import java.util.List;

/**
 * The interface User post mapper.
 */
@Mapper
public interface UserPostMapper extends BaseMapperX<UserPostDO> {

    /**
     * Select list by user id list.
     *
     * @param userId the user id
     * @return the list
     */
    default List<UserPostDO> selectListByUserId(Long userId) {
        return selectList(UserPostDO::getUserId, userId);
    }

    /**
     * Delete by user id and post id.
     *
     * @param userId  the user id
     * @param postIds the post ids
     */
    default void deleteByUserIdAndPostId(Long userId, Collection<Long> postIds) {
        delete(new LambdaQueryWrapperX<UserPostDO>()
                .eq(UserPostDO::getUserId, userId)
                .in(UserPostDO::getPostId, postIds));
    }

    /**
     * Select list by post ids list.
     *
     * @param postIds the post ids
     * @return the list
     */
    default List<UserPostDO> selectListByPostIds(Collection<Long> postIds) {
        return selectList(UserPostDO::getPostId, postIds);
    }

    /**
     * Delete by user id.
     *
     * @param userId the user id
     */
    default void deleteByUserId(Long userId) {
        delete(Wrappers.lambdaUpdate(UserPostDO.class).eq(UserPostDO::getUserId, userId));
    }
}
