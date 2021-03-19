package com.tcwong.pengms.controller;

import com.tcwong.pengms.base.LogFilter;
import com.tcwong.pengms.base.WebPageResponse;
import com.tcwong.pengms.base.WebResponse;
import com.tcwong.pengms.constant.LogOperationType;
import com.tcwong.pengms.model.Scheduling;
import com.tcwong.pengms.service.ScheduleService;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/** 成本维护 */
@RestController
@RequestMapping("/pengms/cost/")
public class CostController {

    @Resource private ScheduleService scheduleService;

    @PostMapping("/getAllByPage")
    public WebResponse getAllByPage(
            Integer page, Integer size, Integer schedulingid, Integer fkCarriersid) {
        WebPageResponse pageResponse =
                scheduleService.getSchedules(page, size, schedulingid, fkCarriersid, null, null);
        if (pageResponse != null) {
            return WebResponse.success(pageResponse, "查询成功");
        }
        return WebResponse.failed("查询失败");
    }

    @LogFilter(description = "成本添加", logOperationType = LogOperationType.ADD)
    @PutMapping("/add")
    public WebResponse addCost(@RequestBody Scheduling scheduling) {
        int num = scheduleService.addCost(scheduling);
        if (num > 0) {
            return WebResponse.success("添加成功");
        }
        return WebResponse.failed("添加失败");
    }
}
