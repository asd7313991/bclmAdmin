package org.example.po.mapper.dream;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.example.config.mybatisplus.BaseMapperX;
import org.example.config.mybatisplus.LambdaQueryWrapperX;
import org.example.po.dream.dream.DreamDepositVirAddressDO;
import org.example.po.dream.dream.DreamUUserDO;
import org.example.po.dream.dream.DreamUVirAddressDO;
import org.example.pojo.PageResult;
import org.example.vo.dream.DepositVirAddressVO;

/**
 * @author Administrator
 * @description 针对表【dream_u_vir_address】的数据库操作Mapper
 * @createDate 2023-09-16 04:20:53
 * @Entity org.example.po.dream.DreamUVirAddressDODO
 */
public interface DreamUVirAddressMapper extends BaseMapperX<DreamUVirAddressDO> {

    default PageResult<DreamUVirAddressDO> pageByParams(DepositVirAddressVO req) {
        return selectPage(req, new LambdaQueryWrapperX<DreamUVirAddressDO>()
                .eqIfPresent(DreamUVirAddressDO::getUserId, req.getUserId())
//                .eqIfPresent(DreamUVirAddressDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(DreamUVirAddressDO::getCreateTime, req.getCreateTime())
                .orderByDesc(DreamUVirAddressDO::getId));
    }
}




