package org.example.player.controller;


import org.example.config.log.TLogAspectExt;
import org.example.operatelog.core.enums.OperateTypeEnum;
import org.example.player.config.log.PlayerLogConvert;
import org.example.player.config.sa.StpPlayerUtil;
import org.example.pojo.CommonResult;
import org.example.pojo.PageResult;
import org.example.service.dream.DreamUFinanceIoService;
import org.example.vo.dream.financeIo.DreamUFinanceIoReqVo;
import org.example.vo.dream.financeIo.DreamUFinanceIoVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

import static org.example.pojo.CommonResult.success;

@RestController
@RequestMapping("/player/order")
public class OrderController {

    @Resource
    private DreamUFinanceIoService dreamUFinanceIoService;

    /**
     * 查询订单详情信息
     * @param pageVO
     * @return
     */
    @TLogAspectExt(str = "查询订单详情信息", moduleName = "Notify", type = OperateTypeEnum.GET, convert = PlayerLogConvert.class)
    @PostMapping("/page")
    public CommonResult<PageResult<DreamUFinanceIoVO>> pageDeposit(@Valid @RequestBody DreamUFinanceIoReqVo pageVO) {

        pageVO.setUserId(StpPlayerUtil.getLoginIdAsLong());

        PageResult<DreamUFinanceIoVO> pageResult = dreamUFinanceIoService.pageByParams(pageVO);

        return success(pageResult);
    }
}
