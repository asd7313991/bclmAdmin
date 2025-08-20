package org.example.player.controller.game;


import lombok.extern.slf4j.Slf4j;
import org.example.pojo.CommonResult;
import org.example.service.hf.HfGameIssueService;
import org.example.vo.hf.gameIssue.KenoDrawThirdDTO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/game/game-issue")
public class GameIssueController {


    @Resource
    private HfGameIssueService hfGameIssueService;


    @RequestMapping("/push")
    public CommonResult push(@RequestBody KenoDrawThirdDTO kenoDrawThirdDTO) {
        log.info("接收到开奖信息");

        hfGameIssueService.push(kenoDrawThirdDTO);

        return CommonResult.success(Boolean.TRUE);

    }
}
