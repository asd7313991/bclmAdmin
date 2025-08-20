package org.example.service.dream;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.po.dream.dream.DreamUFinanceDepositDO;
import org.example.pojo.PageResult;
import org.example.vo.dream.financeDeposit.DreamUFinanceDepositPageReqVO;
import org.example.vo.dream.financeDeposit.DreamUFinanceDepositVO;

/**
 * @description 针对表【dream_u_finance_deposit】的数据库操作Service
 * @createDate 2023-09-13 13:32:04
 */
public interface DreamUFinanceDepositService extends IService<DreamUFinanceDepositDO> {

    PageResult<DreamUFinanceDepositVO> pageByParams(DreamUFinanceDepositPageReqVO pageVO);
}
