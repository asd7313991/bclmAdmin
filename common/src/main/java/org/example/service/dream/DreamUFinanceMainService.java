package org.example.service.dream;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.po.dream.dream.DreamUFinanceMainDO;
import org.example.pojo.PageResult;
import org.example.vo.dream.financeMain.DreamUFinanceMainVO;
import org.example.vo.dream.financeMain.DreamUFinancePageReqVO;
import org.example.vo.dream.user.DreamUUserPwdReqVO;
import org.example.vo.dream.user.DreamUUserVO;

/**
 * @description 针对表【dream_u_finance_main】的数据库操作Service
 * @createDate 2023-09-13 13:32:04
 */
public interface DreamUFinanceMainService extends IService<DreamUFinanceMainDO> {

    PageResult<DreamUFinanceMainVO> pageByParams(DreamUFinancePageReqVO pageVO);

    DreamUFinanceMainVO setectByUserId(Long userId);

    boolean changePassword(DreamUUserVO updateReqVO);

    Boolean changePayPassword(DreamUUserPwdReqVO updateReqVO);

    void update(DreamUFinanceMainVO dreamUFinanceMainVO);


    void update(DreamUFinanceMainDO dreamUFinanceMainVO);
    DreamUFinanceMainDO findByUserId(long userId);

}
