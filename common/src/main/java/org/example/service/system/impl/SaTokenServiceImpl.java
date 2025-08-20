package org.example.service.system.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.po.system.SaTokenDO;
import org.example.service.system.SaTokenService;
import org.example.po.mapper.SaTokenMapper;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【sa_token】的数据库操作Service实现
* @createDate 2023-09-25 00:17:46
*/
@Service
public class SaTokenServiceImpl extends ServiceImpl<SaTokenMapper, SaTokenDO>
    implements SaTokenService{

}




