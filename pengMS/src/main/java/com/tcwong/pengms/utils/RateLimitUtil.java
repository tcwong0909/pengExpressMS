package com.tcwong.pengms.utils;

import com.tcwong.pengms.constant.RateLimitConstant;
import com.tcwong.pengms.constant.RedisKeyConstant;
import java.util.concurrent.TimeUnit;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;

/**
 * Description
 *
 * @author tcwong
 * @date 2020/8/23 Since 1.8
 */
@Component
public class RateLimitUtil {

    @Autowired private RedisTemplate redisTemplate;

    public Boolean rateLimit(HttpServletRequest request, Object handler) {
        if (handler instanceof HandlerMethod) {
            String ipAddr = IpUtil.getIpAddr(request);
            String limittingRateIpKey = RedisKeyUtil.getLimittingRateIpKey(ipAddr);
            // 验证是否限流
            if (redisTemplate.hasKey(RedisKeyConstant.LIMITTING_TOTAL)
                    || redisTemplate.hasKey(limittingRateIpKey)) {
                return true;
            }
            Integer totalRate =
                    (Integer) redisTemplate.opsForValue().get(RedisKeyConstant.RATE_LIMIT_TOTAL);
            if (totalRate != null && totalRate > RateLimitConstant.TOTAL_LIMIT_NUM) {
                // 五秒内超过200请求 限流5分钟
                redisTemplate
                        .opsForValue()
                        .set(
                                RedisKeyConstant.LIMITTING_TOTAL,
                                null,
                                RateLimitConstant.TOTAL_LIMIT_MINUTE,
                                TimeUnit.MINUTES);
                redisTemplate.delete(RedisKeyConstant.RATE_LIMIT_TOTAL);
                return true;
            } else if (totalRate == null) {
                // 第一次请求统计
                redisTemplate
                        .opsForValue()
                        .set(RedisKeyConstant.RATE_LIMIT_TOTAL, 1, 5, TimeUnit.SECONDS);
            } else {
                // 请求统计自增
                redisTemplate.opsForValue().increment(RedisKeyConstant.RATE_LIMIT_TOTAL, 1);
            }
            long currentTimeMillis = System.currentTimeMillis();
            String rateLimitIpKey = RedisKeyUtil.getRateLimitIpKey(ipAddr);
            Long ipRate = redisTemplate.opsForList().size(rateLimitIpKey);
            redisTemplate.opsForList().leftPush(rateLimitIpKey, currentTimeMillis);
            redisTemplate.expire(rateLimitIpKey, RateLimitConstant.EXPIRE_HOUR, TimeUnit.HOURS);
            if (ipRate != null && ipRate > RateLimitConstant.IP_LIMIT_NUM) {
                // 一分钟内单个Ip超过200，限流60分钟
                Long lastRateTime = (Long) redisTemplate.opsForList().rightPop(rateLimitIpKey);
                if ((currentTimeMillis - lastRateTime) < TimeUnit.MINUTES.toMillis(1)) {
                    redisTemplate
                            .opsForValue()
                            .set(
                                    limittingRateIpKey,
                                    null,
                                    RateLimitConstant.IP_LIMIT_MINUTE,
                                    TimeUnit.MINUTES);
                    return true;
                }
            }
        }
        return false;
    }
}
