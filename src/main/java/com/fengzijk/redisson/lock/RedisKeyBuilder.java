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


/**
 * <pre>RedisKeyBuilder 统一自定义生成redisKey</pre>
 *
 * @author fengzijk
 * @since 2022/8/28
 */
public class RedisKeyBuilder {
    /**
     * 主数据系统标识
     */
    private static final String KEY_PREFIX = "lock";
    /**
     * 分割字符，默认[:]，使用:可用于rdm分组查看
     */
    private static final String KEY_SPLIT_CHAR = ":";


    /**
     * <pre>redis的key键规则定义</pre>
     *
     * @param module 模块名称
     * @param func   方法名称
     * @param args   参数..
     * @return java.lang.String
     * @since : 2022/8/28
     */
    public static String keyBuilder(String module, String func, String... args) {

        return keyBuilder(null, module, func, args);
    }


    /**
     * <pre>redis的key键规则定义</pre>
     *
     * @param module 模块名称
     * @param func   方法名称
     * @param objStr 对象.toString()
     * @return java.lang.String
     * @since : 2022/8/28
     */
    public static String keyBuilder(String module, String func, String objStr) {
        return keyBuilder(null, module, func, new String[]{objStr});
    }

    /**
     * <pre>redis的key键规则定义</pre>
     *
     * @param prefix 项目前缀
     * @param module 模块名称
     * @param func   方法名称
     * @param objStr 对象.toString()
     * @return java.lang.String
     * @since : 2022/8/28
     */
    public static String keyBuilder(String prefix, String module, String func, String objStr) {
        return keyBuilder(prefix, module, func, new String[]{objStr});
    }


    /**
     * <pre>redis的key键规则定义</pre>
     *
     * @param prefix 项目前缀
     * @param module 模块名称
     * @param func   方法名称
     * @param args   参数..
     * @return java.lang.String
     * @since : 2022/8/28
     */
    private static String keyBuilder(String prefix, String module, String func, String... args) {
        // 项目前缀
        if (prefix == null) {
            prefix = KEY_PREFIX;
        }
        StringBuilder key = new StringBuilder(prefix);
        // KEY_SPLIT_CHAR 为分割字符
        key.append(KEY_SPLIT_CHAR).append(module).append(KEY_SPLIT_CHAR).append(func);
        for (String arg : args) {
            key.append(KEY_SPLIT_CHAR).append(arg);
        }
        return key.toString();
    }
}
