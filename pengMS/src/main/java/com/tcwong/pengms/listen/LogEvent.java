package com.tcwong.pengms.listen;

import org.springframework.context.ApplicationEvent;

/**
 * Description 日志事件
 *
 * @author tcwong
 * @date 2020/8/24
 * Since 1.8
 */
public class LogEvent extends ApplicationEvent {
    public LogEvent(Object source) {
        super(source);
    }
}
