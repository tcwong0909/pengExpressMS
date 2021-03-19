package com.tcwong.pengms.utils;

import com.tcwong.pengms.constant.RedisKeyConstant;

/**
 * Description redisKey生成类
 *
 * @author tcwong
 * @date 2020/8/23 Since 1.8
 */
public class RedisKeyUtil {

    /** 流量统计 */
    public static String getRateLimitIpKey(String ipAddr) {
        return RedisKeyConstant.RATE_LIMIT_IP + ipAddr;
    }

    /** 正在限流key */
    public static String getLimittingRateIpKey(String ipAddr) {
        return RedisKeyConstant.LIMITTING_IP + ipAddr;
    }
}
