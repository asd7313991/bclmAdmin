package org.example.service.dream;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.po.dream.dream.DreamUFinanceFlowDO;
import org.example.pojo.PageResult;
import org.example.vo.dream.financeFlow.DreamUFinanceFlowReqVo;
import org.example.vo.dream.financeFlow.DreamUFinanceFlowVO;

import java.util.List;

/**
 * @description 针对表【dream_u_finance_flow】的数据库操作Service
 * @createDate 2023-09-13 13:32:04
 */
public interface DreamUFinanceFlowService extends IService<DreamUFinanceFlowDO> {

    PageResult<DreamUFinanceFlowVO> pageByParams(DreamUFinanceFlowReqVo pageVO);

    List<DreamUFinanceFlowVO> listByParams(DreamUFinanceFlowReqVo pageVO);
}
