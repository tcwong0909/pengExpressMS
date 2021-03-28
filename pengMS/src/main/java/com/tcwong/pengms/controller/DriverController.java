package com.tcwong.pengms.controller;

import com.tcwong.pengms.base.LogFilter;
import com.tcwong.pengms.base.WebPageResponse;
import com.tcwong.pengms.base.WebResponse;
import com.tcwong.pengms.constant.LogOperationType;
import com.tcwong.pengms.dto.driver.DriverAddRequest;
import com.tcwong.pengms.dto.driver.DriverDeleteRequest;
import com.tcwong.pengms.dto.driver.DriverEditRequest;
import com.tcwong.pengms.dto.driver.DriverPageRequest;
import com.tcwong.pengms.service.DriverService;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * Description 司机管理
 *
 * @author tcwong
 * @date 2021/3/20 Since 1.8
 */
@RestController
@RequestMapping("/pengms/driver")
public class DriverController {

    @Resource private DriverService driverService;

    /**
     * Description 添加司机
     *
     * @param request 司机添加请求
     * @return Integer 成功条数
     * @author tcwong
     * @date 2021/3/20
     */
    @LogFilter(description = "司机添加", logOperationType = LogOperationType.ADD)
    @PostMapping("/add")
    public WebResponse addDriver(@RequestBody DriverAddRequest request) {
        Integer num = driverService.addDriver(request);
        if (num > 0) {
            return WebResponse.success("添加成功");
        }
        return WebResponse.failed("添加失败");
    }

    /**
     * Description 删除司机
     *
     * @param request 司机删除参数
     * @return Integer 成功条数
     * @author tcwong
     * @date 2021/3/20
     */
    @LogFilter(description = "司机删除", logOperationType = LogOperationType.DELETE)
    @PostMapping("/delete")
    public WebResponse deleteDriver(@RequestBody DriverDeleteRequest request) {
        Integer num = driverService.deleteDriver(request);
        if (num > 0) {
            return WebResponse.success("删除成功");
        }
        return WebResponse.failed("删除失败");
    }

    /**
     * Description 司机编辑
     *
     * @param request 司机编辑参数
     * @return Integer 成功条数
     * @author tcwong
     * @date 2021/3/20
     */
    @LogFilter(description = "司机编辑", logOperationType = LogOperationType.UPDATE)
    @PostMapping("/edit")
    public WebResponse editDriver(@RequestBody DriverEditRequest request) {
        Integer num = driverService.editDriver(request);
        if (num > 0) {
            return WebResponse.success("修改成功");
        }
        return WebResponse.failed("修改失败");
    }

    /**
     * Description 司机分页查询
     *
     * @param request 司机分页查询请求
     * @return 司机分页结果
     * @author tcwong
     * @date 2021/3/20
     */
    @PostMapping("/page")
    public WebResponse pageDriver(@RequestBody DriverPageRequest request) {
        WebPageResponse pageResponse = driverService.pageDriver(request);
        if (pageResponse != null) {
            return WebResponse.success(pageResponse, "查询成功");
        }
        return WebResponse.failed("查询失败");
    }
}
