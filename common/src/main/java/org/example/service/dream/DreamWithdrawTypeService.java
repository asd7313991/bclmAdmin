package org.example.service.dream;

import org.example.po.dream.dream.DreamWithdrawTypeDO;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.vo.dream.deposit.DepositTypeVO;
import org.example.vo.dream.withdraw.WithdrawTypeVO;

import java.util.List;

/**
* @author a5.0
* @description 针对表【dream_withdraw_type】的数据库操作Service
* @createDate 2023-09-17 09:51:06
*/
public interface DreamWithdrawTypeService extends IService<DreamWithdrawTypeDO> {


    List<WithdrawTypeVO> getWithdrawMethod(Long userId);

    DreamWithdrawTypeDO selectByWithdrawTypeId(Long withdrawId);
}
