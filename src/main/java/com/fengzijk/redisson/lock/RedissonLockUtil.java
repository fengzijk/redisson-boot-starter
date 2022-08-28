
/*
 *   All rights Reserved, Designed By ZTE-ITS
 *   Copyright:    Copyright(C) 2019-2025
 *   Company       fengzijk LTD.
 *   @Author:    fengzijk
 *   @Email: fengzijkvip@163.com
 *   @Version    V1.0
 *   @Date:   2022年08月28日 18时23分
 *   Modification       History:
 *   ------------------------------------------------------------------------------------
 *   Date                  Author        Version        Description
 *   -----------------------------------------------------------------------------------
 *  2022-08-28 18:23:27    fengzijk         1.0         Why & What is modified: <修改原因描述>
 *
 *
 */

package com.fengzijk.redisson.lock;

import org.redisson.api.RLock;

import java.util.concurrent.TimeUnit;


/**
 * <pre>分布式锁工具类</pre>
 *
 * @author guozhifeng
 * @since 2022/8/28
 */
public class RedissonLockUtil {
    private static DistributedLocker redissonLock;

    public static void setLocker(DistributedLocker locker) {
        redissonLock = locker;
    }
    /**
     * <pre>此方法不带超时 不手动释放可能引起死锁</pre>
     *
     * @param lockKey redis的key
     * @return org.redisson.api.RLock
     * @since : 2022/8/28
     */
    public static RLock lock(String lockKey) {
      return  redissonLock.lock(lockKey);
    }


    /**
     * <pre>获取锁 此方法带超时</pre>
     *
     * @param lockKey redis的key
     * @param timeout 超时时间，单位为秒
     * @return org.redisson.api.RLock
     * @since : 2022/8/28
     */
    public static RLock lock(String lockKey, int timeout) {
         return redissonLock.lock(lockKey, timeout);
    }

    /**
     * <pre>获取锁</pre>
     *
     * @param lockKey key
     * @param unit    时间单位（建议不要太长）
     * @param timeout 超时释放时间
     * @return org.redisson.api.RLock
     * @since : 2022/8/28
     */
    public static RLock lock(String lockKey, TimeUnit unit, int timeout) {
       return redissonLock.lock(lockKey, unit, timeout);
    }
    /**
     * <pre>加锁操作支持过期解锁功能,最多等待多久就上锁leaseTime后以后自动解锁, 无需调用unlock方法手动解锁</pre>
     *
     * @param lockKey   redis key
     * @param unit      时间单位
     * @param waitTime  等待时间数值
     * @param leaseTime 经过多久释放的数值
     * @return boolean 加锁是否成功
     * @author : guozhifeng
     * @date : 2022/8/28 17:48
     */
    public static RLock lock(String lockKey, TimeUnit unit, int waitTime, int leaseTime) {
        return redissonLock.lock(lockKey,  unit,  waitTime,  leaseTime);
    }
    /**
     * <pre>加锁操作支持过期解锁功能,最多等待多久就上锁leaseTime后以后自动解锁, 无需调用unlock方法手动解锁</pre>
     *
     * @param lockKey   redis key
     * @param unit      时间单位
     * @param waitTime  等待时间数值
     * @param leaseTime 经过多久释放的数值
     * @return boolean 加锁是否成功
     * @author : guozhifeng
     * @date : 2022/8/28 17:48
     */
   public static Boolean tryLock(String lockKey, TimeUnit unit, int waitTime, int leaseTime){
        return redissonLock.tryLock(lockKey,unit,waitTime,leaseTime);
    }

    /**
     * <pre>通过redisKey释放锁</pre>
     *
     * @param lockKey redisKey
     * @since : 2022/8/28
     */
    public static void unlock(String lockKey) {
        redissonLock.unlock(lockKey);
    }

    /**
     * <pre>通过所对象释放锁</pre>
     *
     * @param lock 对象
     * @since : 2022/8/28
     */
    public static void unlock(RLock lock) {
        redissonLock.unlock(lock);
    }
}
