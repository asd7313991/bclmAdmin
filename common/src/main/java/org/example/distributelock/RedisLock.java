package org.example.distributelock;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

public class RedisLock implements DistributeLock{
    protected RLock rLock;
    public RedisLock(RedissonClient redissonClient,String monitor){
        rLock = redissonClient.getFairLock(monitor);
    }
    @Override
    public void lock() {
        boolean ret = rLock.tryLock();
        if(!ret){
            throw new RuntimeException();
        }
    }

    @Override
    public void unlock() {
        rLock.unlock();;
    }
}
