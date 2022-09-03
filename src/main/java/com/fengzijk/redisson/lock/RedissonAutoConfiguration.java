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


import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SentinelServersConfig;
import org.redisson.config.SingleServerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.Objects;


/**
 * <pre>redisson自动配置类</pre>
 *
 * @author guozhifeng
 * @since 2022/8/28
 */
@Configuration
@ConditionalOnClass(Config.class)
@AutoConfigureBefore(RedisAutoConfiguration.class)
@EnableConfigurationProperties(RedissonProperties.class)
public class RedissonAutoConfiguration {

    @Autowired
    private RedissonProperties redissonProperties;


    @Autowired
    private RedisProperties redisProperties;


    private static final String REDIS_PROTOCOL_PREFIX = "redis://";
    private static final String REDISS_PROTOCOL_PREFIX = "rediss://";

    /**
     * 单机模式自动装配
     */
    @SuppressWarnings("ConstantConditions")
    @Bean
    @ConditionalOnMissingBean
    RedissonClient redissonSingle() {
        Config config = new Config();


        String prefix = REDIS_PROTOCOL_PREFIX;
        Method method = ReflectionUtils.findMethod(RedisProperties.class, "isSsl");
        if (method != null && (Boolean)ReflectionUtils.invokeMethod(method, redisProperties)) {
            prefix = REDISS_PROTOCOL_PREFIX;
        }


        if (Objects.nonNull(redisProperties)
                && Objects.nonNull(redisProperties.getSentinel())
                && !CollectionUtils.isEmpty(redisProperties.getSentinel().getNodes())) {
            String[] nodes = new String[redisProperties.getSentinel().getNodes().size()];

            for (int i = 0; i < redisProperties.getSentinel().getNodes().size(); i++) {
                if (!redisProperties.getSentinel().getNodes().get(i).startsWith(prefix)) {
                    nodes[i] = (prefix + redisProperties.getSentinel().getNodes().get(i));
                } else {
                    nodes[i] = redisProperties.getSentinel().getNodes().get(i);
                }
            }
            SentinelServersConfig serverConfig =
                    config.useSentinelServers()
                            .addSentinelAddress(nodes)
                            .setMasterName(redisProperties.getSentinel().getMaster())
                            .setTimeout(redissonProperties.getTimeout())
                            .setMasterConnectionPoolSize(redissonProperties.getMasterConnectionPoolSize())
                            .setSlaveConnectionPoolSize(redissonProperties.getSlaveConnectionPoolSize())
                            .setDatabase(redissonProperties.getDatabase());
            if (StringUtils.isNotBlank(redisProperties.getSentinel().getPassword())) {
                serverConfig.setPassword(redisProperties.getSentinel().getPassword());
            }

        }else{

            SingleServerConfig serverConfig = config.useSingleServer();
            serverConfig.setAddress(prefix + redisProperties.getHost() + ":" + redisProperties.getPort());
            serverConfig.setTimeout(redissonProperties.getTimeout());
            serverConfig.setConnectionPoolSize(redissonProperties.getConnectionPoolSize());
            serverConfig.setConnectionMinimumIdleSize(redissonProperties.getConnectionMinimumIdleSize());
            serverConfig.setDatabase(redissonProperties.getDatabase());

            if (org.springframework.util.StringUtils.hasText(redisProperties.getUrl())) {
                serverConfig.setAddress(redisProperties.getUrl());
            } else {
                serverConfig.setAddress(prefix + redisProperties.getHost() + ":" + redisProperties.getPort());
            }

            if (StringUtils.isNotBlank(redisProperties.getPassword())) {
                serverConfig.setPassword(redisProperties.getPassword());
            }

        }

        return Redisson.create(config);
    }

    /**
     * 装配locker类，并将实例注入到RedissonLockUtil中
     *
     */
    @Bean
    @ConditionalOnMissingBean
    DistributedLocker distributedLocker(RedissonClient redissonSingle) {
        RedissonDistributedLocker locker = new RedissonDistributedLocker();
        locker.setRedissonClient(redissonSingle);
        RedissonLockUtil.setLocker(locker);
        return locker;
    }
}

