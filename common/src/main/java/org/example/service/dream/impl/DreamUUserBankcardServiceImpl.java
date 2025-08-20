package org.example.service.dream.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.po.dream.dream.DreamUUserBankcardDO;
import org.example.po.mapper.dream.DreamUUserBankcardMapper;
import org.example.pojo.PageResult;
import org.example.service.dream.DreamUUserBankcardService;
import org.example.vo.dream.bankcard.DreamUserBankcardReqVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @description 针对表【dream_u_user_bankcard】的数据库操作Service实现
 * @createDate 2023-09-13 13:32:04
 */
@Service
public class DreamUUserBankcardServiceImpl extends ServiceImpl<DreamUUserBankcardMapper, DreamUUserBankcardDO>
        implements DreamUUserBankcardService {

    @Resource
    private DreamUUserBankcardMapper dreamUUserBankcardMapper;

    @Override
    public PageResult<DreamUUserBankcardDO> pageByParams(DreamUserBankcardReqVo pageVO) {
        return dreamUUserBankcardMapper.selectPage(pageVO);
    }
}




