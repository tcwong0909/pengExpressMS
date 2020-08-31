package com.tcwong.pengms.controller;

import com.github.pagehelper.PageInfo;
import com.tcwong.pengms.dto.LoginLogRequest;
import com.tcwong.pengms.model.LoginLog;
import com.tcwong.pengms.service.LoginLogService;
import com.tcwong.pengms.base.WebResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description
 *
 * @author tcwong
 * @date 2020/8/22
 * Since 1.8
 */
@RestController
@RequestMapping("/pengms/loginlog")
public class LoginLogController {

    @Autowired
    private LoginLogService loginLogService;

    @PostMapping("/listLoginLog")
    public WebResponse listLoginLog(@RequestBody LoginLogRequest request) {
        WebResponse<PageInfo<LoginLog>> response = new WebResponse<>();
        PageInfo<LoginLog> loginLogPageInfo = loginLogService.listLoginLog(request);
        response.setData(loginLogPageInfo);
        return response;
    }

}
