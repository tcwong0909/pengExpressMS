package com.tcwong.pengms.listen;

import com.tcwong.pengms.dto.LogDTO;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * Description 日志监听
 *
 * @author tcwong
 * @date 2020/8/24
 * Since 1.8
 */
@Component
public class LogListener {

    @Async
    @EventListener(LogEvent.class)
    public void insertLog(LogEvent logEvent) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LogDTO source = (LogDTO) logEvent.getSource();
        System.out.println(Thread.currentThread().getName()+source);
    }
}
