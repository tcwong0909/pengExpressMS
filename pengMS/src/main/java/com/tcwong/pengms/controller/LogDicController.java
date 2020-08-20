package com.tcwong.pengms.controller;

import com.tcwong.pengms.model.Logdic;
import com.tcwong.pengms.service.ILogDicService;
import com.tcwong.pengms.utils.WebResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/logDic/")
public class LogDicController {

    @Resource
    private ILogDicService logDicService;

    @GetMapping("/getAll")
    public WebResponse getAll(){
        List<Logdic> lists = logDicService.getAll();
        if (lists != null) {
            return WebResponse.success(lists);
        }
        return WebResponse.failed("查询失败");
    }
}
