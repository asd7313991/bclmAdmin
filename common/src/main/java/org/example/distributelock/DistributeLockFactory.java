package org.example.distributelock;



/**
 * 分布式锁，先期使用curator的InterProcessMutex实现，后期根据需要改用自定义的lock。
 * 
 * @author Boyce 2016年6月29日 上午10:29:17
 */
public interface DistributeLockFactory {

	 DistributeLock newLock(String monitor);
}
