package org.example.service.dream;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.po.dream.dream.DreamUFinanceAccDO;
import org.example.pojo.PageResult;
import org.example.vo.dream.financeAcc.DreamUFinanceAccReqVo;
import org.example.vo.dream.financeAcc.DreamUFinanceAccVO;

/**
 * @description 针对表【dream_u_finance_acc】的数据库操作Service
 * @createDate 2023-09-13 13:32:04
 */
public interface DreamUFinanceAccService extends IService<DreamUFinanceAccDO> {

    PageResult<DreamUFinanceAccDO> pageByParams(DreamUFinanceAccReqVo pageVO);

    DreamUFinanceAccVO selectByUserId(Long id);

}
