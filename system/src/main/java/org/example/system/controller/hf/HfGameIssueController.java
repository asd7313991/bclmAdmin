package org.example.system.controller.hf;


import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import org.example.config.log.TLogAspectExt;
import org.example.po.dream.hf.HfGameIssueDO;
import org.example.pojo.CommonResult;
import org.example.pojo.PageResult;
import org.example.service.hf.HfGameIssueService;
import org.example.system.config.log.SystemTLogConvert;
import org.example.vo.hf.gameIssue.HfGameIssueReqVo;
import org.example.vo.hf.gameIssue.HfGameIssueVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

import static org.example.operatelog.core.enums.OperateTypeEnum.GET;
import static org.example.pojo.CommonResult.success;

/**
 * The type Hf game issue controller.
 */
@RestController
@RequestMapping("/hf/gameIssue")
@Slf4j
public class HfGameIssueController {

    @Resource
    private HfGameIssueService hfGameIssueService;
    @Resource
    private MapperFacade mapperFacade;


    /**
     * 游戏期号获取 分页查询
     *
     * @param pageVO the page vo
     * @return banner page
     */
    @GetMapping("/page")
    @TLogAspectExt(str = " 游戏期号获取 分页查询", moduleName = "GameIssue", type = GET, convert = SystemTLogConvert.class)
    public CommonResult<PageResult<HfGameIssueVO>> getBannerPage(@Valid HfGameIssueReqVo pageVO) {
        PageResult<HfGameIssueDO> pageResult = hfGameIssueService.pageByParams(pageVO);
        PageResult<HfGameIssueVO> empty = PageResult.empty(pageResult.getTotal());
        empty.setList(mapperFacade.mapAsList(pageResult.getList(), HfGameIssueVO.class));
        return success(empty);
    }


}
