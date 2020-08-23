package com.tcwong.pengms.controller;

import com.tcwong.pengms.model.Scheduling;
import com.tcwong.pengms.service.IScheduleService;
import com.tcwong.pengms.utils.Log;
import com.tcwong.pengms.utils.LogdicType;
import com.tcwong.pengms.utils.WebPageResponse;
import com.tcwong.pengms.utils.WebResponse;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 成本维护
 */
@RestController
@RequestMapping("/pengms/cost/")
public class CostController {

    @Resource
    private IScheduleService scheduleService;


    @PostMapping("/getAllByPage")
    public WebResponse getAllByPage(Integer page, Integer size, Integer schedulingid, Integer fkCarriersid){
        WebPageResponse pageResponse = scheduleService.getSchedules(page, size, schedulingid, fkCarriersid, null, null);
        if (pageResponse != null) {
            return WebResponse.success(pageResponse, "查询成功");
        }
        return WebResponse.failed("查询失败");
    }

    @Log(behavior = "成本添加",fkTypeid = LogdicType.ADD)
    @PutMapping("/add")
    public WebResponse addCost(@RequestBody Scheduling scheduling){
        int num = scheduleService.addCost(scheduling);
        if (num > 0) {
            return WebResponse.success("添加成功");
        }
        return WebResponse.failed("添加失败");
    }
}
