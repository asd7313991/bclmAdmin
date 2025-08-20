package org.example.system.controller.monitor;

import cn.hutool.core.util.StrUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.config.log.TLogAspectExt;
import org.example.pojo.CommonResult;
import org.example.system.config.log.SystemTLogConvert;
import org.example.system.config.satoken.SystemCheckPermission;
import org.example.system.vo.monitor.RedisMonitorRespVO;
import org.springframework.data.redis.connection.RedisServerCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Properties;

import static org.example.operatelog.core.enums.OperateTypeEnum.GET;
import static org.example.pojo.CommonResult.success;


/**
 * The type Redis controller.
 */
@Tag(name = "管理后台 - Redis 监控")
@RestController
@RequestMapping("/infra/redis")
public class RedisController {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * Gets redis monitor info.
     *
     * @return the redis monitor info
     */
    @GetMapping("/get-monitor-info")
//    @Operation(summary = "获得 Redis 监控信息")
    @SystemCheckPermission("infra:redis:get-monitor-info")
    @TLogAspectExt(str = "获得 Redis 监控信息", moduleName = "Redis", type = GET, convert = SystemTLogConvert.class)
    public CommonResult<RedisMonitorRespVO> getRedisMonitorInfo() {
        // 获得 Redis 统计信息
        Properties info = stringRedisTemplate.execute((RedisCallback<Properties>) RedisServerCommands::info);
        Long dbSize = stringRedisTemplate.execute(RedisServerCommands::dbSize);
        Properties commandStats = stringRedisTemplate.execute((
                RedisCallback<Properties>) connection -> connection.info("commandstats"));
        assert commandStats != null; // 断言，避免警告
        // 拼接结果返回
        return success(build(info, dbSize, commandStats));
    }


    /**
     * 拼装结果返回
     *
     * @param info         the info
     * @param dbSize       the db size
     * @param commandStats the command stats
     * @return redis monitor resp vo
     */
    public RedisMonitorRespVO build(Properties info, Long dbSize, Properties commandStats) {
        RedisMonitorRespVO respVO = RedisMonitorRespVO.builder().info(info).dbSize(dbSize)
                .commandStats(new ArrayList<>(commandStats.size())).build();
        commandStats.forEach((key, value) -> {
            respVO.getCommandStats().add(RedisMonitorRespVO.CommandStat.builder()
                    .command(StrUtil.subAfter((String) key, "cmdstat_", false))
                    .calls(Long.valueOf(StrUtil.subBetween((String) value, "calls=", ",")))
                    .usec(Long.valueOf(StrUtil.subBetween((String) value, "usec=", ",")))
                    .build());
        });
        return respVO;
    }
}
