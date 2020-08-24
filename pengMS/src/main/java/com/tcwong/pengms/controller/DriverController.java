package com.tcwong.pengms.controller;

import com.tcwong.pengms.base.LogFilter;
import com.tcwong.pengms.base.WebPageResponse;
import com.tcwong.pengms.base.WebResponse;
import com.tcwong.pengms.constant.LogOperationType;
import com.tcwong.pengms.model.Driver;
import com.tcwong.pengms.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 驾驶员
 */
@RestController
@RequestMapping("/pengms/driver/")
public class DriverController {

    @Autowired
    private DriverService driverService;

    @LogFilter(description = "驾驶员添加",logOperationType = LogOperationType.ADD)
    @PostMapping("/add")
    public WebResponse addDriver(@RequestBody Driver driver) {
        int num = driverService.addDriver(driver);
        if (num > 0) {
            return WebResponse.success("添加成功");
        }
        return WebResponse.failed("添加失败");
    }

    @LogFilter(description = "驾驶员删除",logOperationType = LogOperationType.DELETE)
    @DeleteMapping("/delete/{ids}")
    public WebResponse deleteByIds(@PathVariable String ids) {
        int num = driverService.deleteByIds(ids);
        if (num > 0) {
            return WebResponse.success("删除成功");
        }
        return WebResponse.failed("删除失败");
    }

    @LogFilter(description = "驾驶员修改",logOperationType = LogOperationType.UPDATE)
    @PutMapping("/put")
    public WebResponse editDriver(@RequestBody Driver driver) {
        int num = driverService.editDriver(driver);
        if (num > 0) {
            return WebResponse.success("修改成功");
        }
        return WebResponse.failed("修改失败");
    }

    @PostMapping("/getAllByPage")
    public WebResponse getAllDriverByPage(Integer page, Integer size,String name,Integer fkTeamid,
                                          Integer state) {
        WebPageResponse pageResponse = driverService.getAllDriverByPage(page, size,name,fkTeamid,state);
        if (pageResponse != null) {
            return WebResponse.success(pageResponse, "查询成功");
        }
        return WebResponse.failed("查询失败");
    }
}
