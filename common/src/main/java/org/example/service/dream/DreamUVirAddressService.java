package org.example.service.dream;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.po.dream.dream.DreamDepositVirAddressDO;
import org.example.po.dream.dream.DreamUVirAddressDO;
import org.example.pojo.PageResult;
import org.example.vo.dream.DepositVirAddressVO;

import java.util.List;

/**
 * @author Administrator
 * @description 针对表【dream_u_vir_address】的数据库操作Service
 * @createDate 2023-09-16 04:20:53
 */
public interface DreamUVirAddressService extends IService<DreamUVirAddressDO> {

    List<DreamUVirAddressDO> setectByUserId(long loginIdAsLong);

    Boolean addVirAddress(DreamUVirAddressDO dreamUVirAddressDO);

    DreamUVirAddressDO getOneReviewedBy(Long id, Long userId);

    DreamUVirAddressDO getOneNotDefaultedBy(Long id, Long userId);

    Boolean changeVirAddressDefaulted(Long id, Long userId);

    Boolean deleteVirAddress(Long id);


    PageResult<DreamUVirAddressDO> pageByParams(DepositVirAddressVO depositVirAddressVO);

    Boolean audit(DepositVirAddressVO depositVirAddressVO);
}
