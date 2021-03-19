package com.tcwong.pengms.constant;

/**
 * Description 限流量值 设定
 *
 * @author tcwong
 * @date 2020/8/23 Since 1.8
 */
public interface RateLimitConstant {

    /** 总限流指标 5秒内 200 */
    Integer TOTAL_LIMIT_NUM = 200;

    /** 根据IP限流 一分钟200 */
    Integer IP_LIMIT_NUM = 200;

    /** 总限流设置时长 */
    Long TOTAL_LIMIT_MINUTE = 5L;

    /** IP 限流设置时长 */
    Long IP_LIMIT_MINUTE = 60L;

    /** 过期时长 */
    Long EXPIRE_HOUR = 5L;
}
