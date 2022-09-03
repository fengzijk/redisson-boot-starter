
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

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Arrays;


/**
 * <pre>RedissonProperties</pre>
 *
 * @author fengzijk
 * @since 2022/8/28
 */
@ConfigurationProperties(prefix = "spring.redis.redisson")
@ConditionalOnProperty(value = "spring.redis.redisson.enabled",havingValue ="true" )
public class RedissonProperties {



    private  boolean enabled;
    private int timeout = 3000;
    private int database = 0;
    private int connectionPoolSize = 64;
    private int connectionMinimumIdleSize = 10;
    private int slaveConnectionPoolSize = 250;
    private int masterConnectionPoolSize = 250;

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }


    public int getDatabase() {
        return database;
    }

    public void setDatabase(int database) {
        this.database = database;
    }

    public int getConnectionPoolSize() {
        return connectionPoolSize;
    }

    public void setConnectionPoolSize(int connectionPoolSize) {
        this.connectionPoolSize = connectionPoolSize;
    }

    public int getConnectionMinimumIdleSize() {
        return connectionMinimumIdleSize;
    }

    public void setConnectionMinimumIdleSize(int connectionMinimumIdleSize) {
        this.connectionMinimumIdleSize = connectionMinimumIdleSize;
    }

    public int getSlaveConnectionPoolSize() {
        return slaveConnectionPoolSize;
    }

    public void setSlaveConnectionPoolSize(int slaveConnectionPoolSize) {
        this.slaveConnectionPoolSize = slaveConnectionPoolSize;
    }

    public int getMasterConnectionPoolSize() {
        return masterConnectionPoolSize;
    }

    public void setMasterConnectionPoolSize(int masterConnectionPoolSize) {
        this.masterConnectionPoolSize = masterConnectionPoolSize;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "RedissonProperties{" +
                "timeout=" + timeout +
                ", database=" + database +
                ", connectionPoolSize=" + connectionPoolSize +
                ", connectionMinimumIdleSize=" + connectionMinimumIdleSize +
                ", slaveConnectionPoolSize=" + slaveConnectionPoolSize +
                ", masterConnectionPoolSize=" + masterConnectionPoolSize +
                '}';
    }
}
