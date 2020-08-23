package com.tcwong.pengms.constant;

/**
 * Description RedisKey 常量
 *
 * @author tcwong
 * @date 2020/8/23
 * Since 1.8
 */
public interface RedisKeyConstant {

    /**
     * 系统总限流策略
     */
    String RATE_LIMIT_TOTAL = "rate:limit:total:value:";

    /**
    *根据IP 限流
    */
    String RATE_LIMIT_IP = "rate:limit:ip:list:";

    /**
    *总限流标记
    */
    String LIMITTING_TOTAL = "limitting:total:value:";

    /**
    *IP 限流标记
    */
    String LIMITTING_IP = "limitting:ip:value:value:";
}
