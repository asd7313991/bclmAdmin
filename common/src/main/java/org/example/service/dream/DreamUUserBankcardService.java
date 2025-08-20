package org.example.service.dream;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.po.dream.dream.DreamUUserBankcardDO;
import org.example.pojo.PageResult;
import org.example.vo.dream.bankcard.DreamUserBankcardReqVo;

/**
 * @description 针对表【dream_u_user_bankcard】的数据库操作Service
 * @createDate 2023-09-13 13:32:04
 */
public interface DreamUUserBankcardService extends IService<DreamUUserBankcardDO> {

    PageResult<DreamUUserBankcardDO> pageByParams(DreamUserBankcardReqVo pageVO);
}
