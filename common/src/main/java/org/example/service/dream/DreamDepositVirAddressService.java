package org.example.service.dream;

import org.example.po.dream.dream.DreamDepositVirAddressDO;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.pojo.PageResult;
import org.example.vo.DreamDepositVirAddressVO;
import org.example.vo.dream.DepositVirAddressVO;
import org.example.vo.dream.withdraw.DepositVirAddressReq;

import java.util.List;

/**
* @author Administrator
* @description 针对表【dream_deposit_vir_address】的数据库操作Service
* @createDate 2023-09-17 03:12:43
*/
public interface DreamDepositVirAddressService extends IService<DreamDepositVirAddressDO> {

    List<DreamDepositVirAddressDO> selectByDepositTypeId(List<Long> collect);

    DreamDepositVirAddressDO selectByAddressId(Long addressId);

    PageResult<DreamDepositVirAddressVO> pageByParams(DepositVirAddressVO depositVirAddressVO);


    Boolean update(DepositVirAddressReq depositVirAddressVO);

    Boolean create(DepositVirAddressReq depositVirAddressVO);
}
