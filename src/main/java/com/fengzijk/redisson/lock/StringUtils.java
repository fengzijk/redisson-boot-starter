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
 * <pre>String工具类</pre>
 *
 * @author guozhifeng
 * @date 2022/8/28 17:39
 */
public class StringUtils {

    /**
     * <pre>判断字符串是否为空</pre>
     *
     * @param cs 字符串
     * @return boolean
     * @since : 2022/8/28
     */
    public static boolean isBlank(CharSequence cs) {
        int strLen;
        if (cs != null && (strLen = cs.length()) != 0) {
            for (int i = 0; i < strLen; ++i) {
                if (!Character.isWhitespace(cs.charAt(i))) {
                    return false;
                }
            }

        }
        return true;
    }

    /**
     * <pre>判断字符串是否不为空</pre>
     *
     * @param cs 字符串
     * @return boolean
     * @since : 2022/8/28
     */
    public static boolean isNotBlank(CharSequence cs) {
        return !isBlank(cs);
    }
}
