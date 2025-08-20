package org.example.service.system.logger;

import ma.glasnost.orika.MapperFacade;

import org.example.po.mapper.system.LoginLogMapper;
import org.example.po.system.LoginLogDO;
import org.example.pojo.PageResult;
import org.example.vo.system.loginlog.LoginLogExportReqVO;
import org.example.vo.system.loginlog.LoginLogPageReqVO;
import org.example.vo.system.operatelog.LoginLogCreateReqDTO;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.List;

/**
 * 登录日志 Service 实现
 */
@Service
@Validated
public class LoginLogServiceImpl implements LoginLogService {

    @Resource
    private LoginLogMapper loginLogMapper;

    @Override
    public PageResult<LoginLogDO> getLoginLogPage(LoginLogPageReqVO reqVO) {
        return loginLogMapper.selectPage(reqVO);
    }

    @Override
    public List<LoginLogDO> getLoginLogList(LoginLogExportReqVO reqVO) {
        return loginLogMapper.selectList(reqVO);
    }

    @Resource
    private MapperFacade mapperFacade;

    @Override
    public void createLoginLog(LoginLogCreateReqDTO reqDTO) {
        LoginLogDO loginLog = mapperFacade.map(reqDTO, LoginLogDO.class);
        loginLogMapper.insert(loginLog);
    }

}
