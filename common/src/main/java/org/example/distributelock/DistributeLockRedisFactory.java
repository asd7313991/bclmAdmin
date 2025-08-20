package org.example.distributelock;

import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DistributeLockRedisFactory implements DistributeLockFactory{

    @Autowired
    public DistributeLockRedisFactory(RedissonClient redissonClient){
        this.redissonClient = redissonClient;
    }
    protected RedissonClient redissonClient;
    public static final String ZK_LOCK_ROOT = "/ZK_LOCK_ROOT";
    public static final String ZK_LOCK_ROOT_2 = ZK_LOCK_ROOT + "/";
    public DistributeLock newLock(String monitor) {
        if (!monitor.startsWith("/")) {
            monitor = ZK_LOCK_ROOT_2 + monitor;
        } else {
            monitor = ZK_LOCK_ROOT + monitor;
        }
        return new RedisLock(redissonClient, monitor);
    }
}
