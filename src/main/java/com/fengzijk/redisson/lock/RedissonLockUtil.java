
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

    public static void lock(String lockKey) {
        redissonLock.lock(lockKey);
    }

    public static void unlock(String lockKey) {
        redissonLock.unlock(lockKey);
    }


    public static void lock(String lockKey, int timeout) {
        redissonLock.lock(lockKey, timeout);
    }


    public static void lock(String lockKey, TimeUnit unit, int timeout) {
        redissonLock.lock(lockKey, unit, timeout);
    }
}
