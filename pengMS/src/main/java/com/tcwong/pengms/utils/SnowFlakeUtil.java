package com.tcwong.pengms.utils;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;

/**
 * Description 雪花Id
 *
 * @author tcwong
 * @date 2021/4/1 Since 1.8
 */
public class SnowFlakeUtil {

    private static Snowflake snowflake = IdUtil.createSnowflake(0, 1);

    /**
     * Description 获取Id
     *
     * @return Long 雪花Id
     * @author tcwong
     * @date 2021/4/1
     */
    public static Long getId() {
        return snowflake.nextId();
    }
}
