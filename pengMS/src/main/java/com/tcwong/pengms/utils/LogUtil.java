package com.tcwong.pengms.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;

/**
 * Description 日志工具
 *
 * @author tcwong
 * @date 2020/8/22
 * Since 1.8
 */
public class LogUtil {

    private static final Logger LOG = LoggerFactory.getLogger("pengMSProject");

    public static SimpleDateFormat DATE_FORMAT =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * Description
     *
     * @param
     * @return
     * @author tcwong
     * @date 2020/8/22
     */
    public static void error(Exception e){
        LOG.error("ErrorCode is" + -1 +",message is" + e.getMessage());
    }

    /**
     * Description 日志信息
     *
     * @param info 日志信息
     * @param params 日志参数
     * @return
     * @author tcwong
     * @date 2020/8/22
     */
    public static void info(String info,Object... params) {
        LOG.info(info,params);
    }

    /**
     * Description 错误日志信息
     *
     * @param info 日志信息
     * @param params 日志参数
     * @return
     * @author tcwong
     * @date 2020/8/22
     */
    public static void error(String info, Object... params) {
        LOG.error(info,params);
    }

}
