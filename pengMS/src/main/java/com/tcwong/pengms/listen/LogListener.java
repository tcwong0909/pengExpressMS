package com.tcwong.pengms.listen;

import com.tcwong.pengms.constant.SessionConstant;
import com.tcwong.pengms.dto.LogDTO;
import com.tcwong.pengms.model.OperateLog;
import com.tcwong.pengms.model.User;
import com.tcwong.pengms.service.OperateLogService;
import com.tcwong.pengms.utils.SessionUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Description 日志监听
 *
 * @author tcwong
 * @date 2020/8/24
 * Since 1.8
 */
@Component
public class LogListener {

    @Autowired
    private OperateLogService operateLogService;

    @Async
    @EventListener(LogEvent.class)
    public void insertLog(LogEvent logEvent) {
        LogDTO logDTO = (LogDTO) logEvent.getSource();
        OperateLog operateLog = new OperateLog();
        BeanUtils.copyProperties(logDTO,operateLog);
        User user = SessionUtil.getAttribute(SessionConstant.SESSION_USER, User.class);
        operateLog.setUsername(user.getUsername());
        operateLog.setUserAccount(user.getAccount());
        operateLog.setCreatTime(new Date());
        operateLogService.insert(operateLog);
    }
}
