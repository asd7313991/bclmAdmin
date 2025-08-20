package org.example.po.mapper.dream;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.example.config.mybatisplus.BaseMapperX;
import org.example.po.dream.dream.DreamDepositVirAddressDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.example.vo.DreamDepositVirAddressVO;
import org.example.vo.dream.DepositVirAddressVO;

import java.util.List;

/**
* @author Administrator
* @description 针对表【dream_deposit_vir_address】的数据库操作Mapper
* @createDate 2023-09-17 03:12:43
* @Entity org.example.po.dream.dream.DreamDepositVirAddressDO
*/
public interface DreamDepositVirAddressMapper extends BaseMapperX<DreamDepositVirAddressDO> {

    List<DreamDepositVirAddressVO> selectPageNew(Page<DreamDepositVirAddressVO> objectPage, @Param("vo") DepositVirAddressVO depositVirAddressVO);
}




