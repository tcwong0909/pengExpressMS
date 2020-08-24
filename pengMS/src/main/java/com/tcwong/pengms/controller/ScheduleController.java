package com.tcwong.pengms.controller;


import com.tcwong.pengms.base.LogFilter;
import com.tcwong.pengms.base.WebPageResponse;
import com.tcwong.pengms.base.WebResponse;
import com.tcwong.pengms.constant.LogOperationType;
import com.tcwong.pengms.model.Scheduling;
import com.tcwong.pengms.service.ScheduleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 调度
 */
@RestController
@RequestMapping("/pengms/schedule/")
public class ScheduleController {

    @Resource
    private ScheduleService scheduleService;

    @LogFilter(description = "承运单调度",logOperationType = LogOperationType.UPDATE)
    @PutMapping("/dispatch")
    public WebResponse dispatchById(@RequestBody Scheduling scheduling) {

        int num = scheduleService.dispatchById(scheduling);
        if (num > 0) {
            return WebResponse.success("已调度");
        }
        return WebResponse.failed("调度失败");
    }

    @PostMapping("/getAllByPage")
    public WebResponse getSchedules(Integer page, Integer size, Integer schedulingid,Integer fkCarriersid,String sendcompany,String receivecompany){
        WebPageResponse pageResponse = scheduleService.getSchedules(page, size, schedulingid, fkCarriersid, sendcompany, receivecompany);
        if (pageResponse != null) {
            return WebResponse.success(pageResponse, "查询成功");
        }
        return WebResponse.failed("查询失败");
    }

    @LogFilter(description = "调度单删除",logOperationType = LogOperationType.DELETE)
    @PostMapping("/delete")
    public WebResponse deleteMore(@RequestBody List<Scheduling> scheduleList) {
        int num = scheduleService.deleteMore(scheduleList);
        if (num > 0) {
            return WebResponse.success("删除成功");
        }
        return WebResponse.failed("删除失败");
    }

}
