package org.example.service.dream;

import org.example.po.dream.dream.DreamDepositTypeDO;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.vo.dream.deposit.DepositTypeVO;

import java.util.List;

/**
* @author Administrator
* @description 针对表【dream_deposit_type】的数据库操作Service
* @createDate 2023-09-17 03:12:43
*/
public interface DreamDepositTypeService extends IService<DreamDepositTypeDO> {

    List<DepositTypeVO> getDepositMethod();


    DreamDepositTypeDO selectByDepositId(Long depositTypeId);
}
