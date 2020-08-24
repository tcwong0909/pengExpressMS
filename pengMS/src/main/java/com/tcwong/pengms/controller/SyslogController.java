package com.tcwong.pengms.controller;

import com.tcwong.pengms.base.WebPageResponse;
import com.tcwong.pengms.base.WebResponse;
import com.tcwong.pengms.service.SyslogService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/pengms/syslog/")
public class SyslogController {

    @Resource
    private SyslogService syslogService;

    @PostMapping("/getAllByPage")
    public WebResponse getAllSyslog(Integer page, Integer size, String behavior, String fkTypeid,
                                    String fkUserid , Integer isexception) {
        WebPageResponse pageResponse = syslogService.getAllSyslog(page, size, behavior, fkTypeid, fkUserid, isexception);
        if (pageResponse != null) {
            return WebResponse.success(pageResponse, "查询成功");
        }
        return WebResponse.failed("查询失败");
    }
}
