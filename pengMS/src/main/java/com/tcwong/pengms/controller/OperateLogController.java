package com.tcwong.pengms.controller;

import com.github.pagehelper.PageInfo;
import com.tcwong.pengms.base.WebResponse;
import com.tcwong.pengms.dto.OperateLogRequest;
import com.tcwong.pengms.model.OperateLog;
import com.tcwong.pengms.service.OperateLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description
 *
 * @author tcwong
 * @date 2020/8/31 Since 1.8
 */
@RestController
@RequestMapping("/pengms/operateLog")
public class OperateLogController {

    @Autowired private OperateLogService operateLogService;

    /**
     * Description 系统日志查询
     *
     * @param request 页面请求
     * @return
     * @author tcwong
     * @date 2020/8/31
     */
    @PostMapping("/listOperateLog")
    public WebResponse listOperateLog(@RequestBody OperateLogRequest request) {
        PageInfo<OperateLog> operateLogPageInfo = operateLogService.listOperateLog(request);
        return WebResponse.success(operateLogPageInfo);
    }
}
