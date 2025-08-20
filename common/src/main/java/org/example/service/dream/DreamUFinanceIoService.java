package org.example.service.dream;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.po.dream.dream.DreamUFinanceIoDO;
import org.example.pojo.PageResult;
import org.example.service.system.homepage.HomePageReqVO;
import org.example.vo.dream.financeIo.DreamUFinanceIoAmountPerDayVO;
import org.example.vo.dream.financeIo.DreamUFinanceIoAmountVO;
import org.example.vo.dream.financeIo.DreamUFinanceIoReqVo;
import org.example.vo.dream.financeIo.DreamUFinanceIoVO;

import java.math.BigDecimal;
import java.util.List;

/**
 * @description 针对表【dream_u_finance_io】的数据库操作Service
 * @createDate 2023-09-13 13:32:04
 */
public interface DreamUFinanceIoService extends IService<DreamUFinanceIoDO> {

    PageResult<DreamUFinanceIoVO> pageByParams(DreamUFinanceIoReqVo pageVO);

    List<DreamUFinanceIoAmountVO> sumAmount(HomePageReqVO reqVO);

    List<DreamUFinanceIoAmountPerDayVO> sumPerDayAmount(HomePageReqVO reqVO);
}
