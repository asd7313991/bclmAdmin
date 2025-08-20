package org.example.service.notice;

import com.google.common.annotations.VisibleForTesting;
import ma.glasnost.orika.MapperFacade;
import org.example.config.mybatisplus.LambdaQueryWrapperX;
import org.example.enums.CommonStatusEnum;
import org.example.po.mapper.system.NoticeMapper;
import org.example.po.system.NoticeDO;
import org.example.pojo.PageResult;
import org.example.vo.system.notice.NoticeCreateReqVO;
import org.example.vo.system.notice.NoticePageReqVO;
import org.example.vo.system.notice.NoticeRespVO;
import org.example.vo.system.notice.NoticeUpdateReqVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import static org.example.enums.ErrorCodeConstants.NOTICE_NOT_FOUND;
import static org.example.exception.util.ServiceExceptionUtil.exception;


/**
 * 通知公告 Service 实现类
 *
 * @author 后台源码
 */
@Service
public class NoticeServiceImpl implements NoticeService {

    @Resource
    private NoticeMapper noticeMapper;
    @Resource
    private MapperFacade mapperFacade;

    @Override
    public Long createNotice(NoticeCreateReqVO reqVO) {
        NoticeDO notice = mapperFacade.map(reqVO, NoticeDO.class);
        noticeMapper.insert(notice);
        return notice.getId();
    }

    @Override
    public void updateNotice(NoticeUpdateReqVO reqVO) {
        // 校验是否存在
        validateNoticeExists(reqVO.getId());
        // 更新通知公告
        NoticeDO updateObj = mapperFacade.map(reqVO, NoticeDO.class);
        noticeMapper.updateById(updateObj);
    }

    @Override
    public void deleteNotice(Long id) {
        // 校验是否存在
        validateNoticeExists(id);
        // 删除通知公告
        noticeMapper.deleteById(id);
    }

    @Override
    public PageResult<NoticeDO> getNoticePage(NoticePageReqVO reqVO) {
        return noticeMapper.selectPage(reqVO);
    }

    @Override
    public NoticeDO getNotice(Long id) {
        return noticeMapper.selectById(id);
    }

    @Override
    public List<NoticeRespVO> getNoticeList() {

        List<NoticeDO> noticeDOS = noticeMapper.selectList(new LambdaQueryWrapperX<NoticeDO>()
                .eq(NoticeDO::getStatus, CommonStatusEnum.ENABLE.getStatus())
                .orderByDesc(NoticeDO::getCreateTime)
        );

        return mapperFacade.mapAsList(noticeDOS, NoticeRespVO.class);
    }

    @VisibleForTesting
    public void validateNoticeExists(Long id) {
        if (id == null) {
            return;
        }
        NoticeDO notice = noticeMapper.selectById(id);
        if (notice == null) {
            throw exception(NOTICE_NOT_FOUND);
        }
    }


}
