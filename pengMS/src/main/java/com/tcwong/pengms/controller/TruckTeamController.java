package com.tcwong.pengms.controller;

import com.tcwong.pengms.base.LogFilter;
import com.tcwong.pengms.base.WebPageResponse;
import com.tcwong.pengms.base.WebResponse;
import com.tcwong.pengms.constant.LogOperationType;
import com.tcwong.pengms.model.Truckteam;
import com.tcwong.pengms.service.TruckTeamService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 车队
 */
@RestController
@RequestMapping("/pengms/truckTeam/")
public class TruckTeamController {

    @Resource
    private TruckTeamService truckTeamService;

    @LogFilter(description = "车队添加",logOperationType = LogOperationType.ADD)
    @PostMapping("/add")
    public WebResponse addTruckTeam(@RequestBody Truckteam truckteam){

        int num = truckTeamService.addTruckTeam(truckteam);
        if (num > 0) {
            return WebResponse.success("添加成功");
        }
        return WebResponse.failed("添加失败");
    }

    @LogFilter(description = "车队删除",logOperationType = LogOperationType.DELETE)
    @DeleteMapping("/delete/{ids}")
    public WebResponse deleteByIds(@PathVariable String ids) {
        int num = truckTeamService.deleteByIds(ids);
        if (num > 0) {
            return WebResponse.success("删除成功");
        }
        return WebResponse.failed("删除失败");
    }

    @LogFilter(description = "车队修改",logOperationType = LogOperationType.UPDATE)
    @PutMapping("/put")
    public WebResponse editTruckTeam(@RequestBody Truckteam truckteam) {
        int num = truckTeamService.editTruckTeam(truckteam);
        if (num > 0) {
            return WebResponse.success(num,"修改成功");
        }
        return WebResponse.failed("修改失败");
    }
    @PostMapping("/getAllByPage")
    public WebResponse getAllByPage(Integer page,Integer size,String teamname,String leader) {
        WebPageResponse webPageResponse = truckTeamService.getAllByPage(page, size,teamname,leader);
        if (webPageResponse != null) {
            return WebResponse.success(webPageResponse, "查询成功");
        }
        return WebResponse.failed("查询失败");
    }

}
