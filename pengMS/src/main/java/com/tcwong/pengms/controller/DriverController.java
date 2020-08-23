package com.tcwong.pengms.controller;

import com.tcwong.pengms.model.Driver;
import com.tcwong.pengms.service.IDriverService;
import com.tcwong.pengms.utils.Log;
import com.tcwong.pengms.utils.LogdicType;
import com.tcwong.pengms.utils.WebPageResponse;
import com.tcwong.pengms.utils.WebResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 驾驶员
 */
@RestController
@RequestMapping("/pengms/driver/")
public class DriverController {

    @Autowired
    private IDriverService driverService;

    @Log(behavior = "驾驶员添加",fkTypeid = LogdicType.ADD)
    @PostMapping("/add")
    public WebResponse addDriver(@RequestBody Driver driver) {
        int num = driverService.addDriver(driver);
        if (num > 0) {
            return WebResponse.success("添加成功");
        }
        return WebResponse.failed("添加失败");
    }

    @Log(behavior = "驾驶员删除",fkTypeid = LogdicType.DELETE)
    @DeleteMapping("/delete/{ids}")
    public WebResponse deleteByIds(@PathVariable String ids) {
        int num = driverService.deleteByIds(ids);
        if (num > 0) {
            return WebResponse.success("删除成功");
        }
        return WebResponse.failed("删除失败");
    }

    @Log(behavior = "驾驶员修改",fkTypeid = LogdicType.UPDATE)
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
