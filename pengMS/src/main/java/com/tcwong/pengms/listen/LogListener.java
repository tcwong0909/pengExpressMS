package com.tcwong.pengms.listen;

import com.tcwong.pengms.dto.LogDTO;
import com.tcwong.pengms.model.OperateLog;
import com.tcwong.pengms.service.OperateLogService;
import java.util.Date;
import javax.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * Description 日志监听
 *
 * @author tcwong
 * @date 2020/8/24 Since 1.8
 */
@Component
public class LogListener {

    @Resource private OperateLogService operateLogService;

    @Async
    @EventListener(LogEvent.class)
    public void insertLog(LogEvent logEvent) {
        LogDTO logDTO = (LogDTO) logEvent.getSource();
        OperateLog operateLog = new OperateLog();
        BeanUtils.copyProperties(logDTO, operateLog);
        operateLog.setCreatTime(new Date());
        operateLogService.insert(operateLog);
    }
}
