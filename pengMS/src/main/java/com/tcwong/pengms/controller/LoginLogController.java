package com.tcwong.pengms.controller;

import com.github.pagehelper.PageInfo;
import com.tcwong.pengms.base.WebResponse;
import com.tcwong.pengms.dto.LoginLogRequest;
import com.tcwong.pengms.model.LoginLog;
import com.tcwong.pengms.service.LoginLogService;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description 登陆日志接口
 *
 * @author tcwong
 * @date 2020/8/22 Since 1.8
 */
@RestController
@RequestMapping("/pengms/loginlog")
public class LoginLogController {

    @Resource private LoginLogService loginLogService;

    @PostMapping("/listLoginLog")
    public WebResponse listLoginLog(@RequestBody LoginLogRequest request) {
        PageInfo<LoginLog> loginLogPageInfo = loginLogService.listLoginLog(request);
        return WebResponse.success(loginLogPageInfo);
    }
}
