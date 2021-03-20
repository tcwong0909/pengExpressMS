package com.tcwong.pengms.controller;

import com.tcwong.pengms.base.LogFilter;
import com.tcwong.pengms.base.WebPageResponse;
import com.tcwong.pengms.base.WebResponse;
import com.tcwong.pengms.constant.LogOperationType;
import com.tcwong.pengms.dto.truck.TruckAddRequest;
import com.tcwong.pengms.dto.truck.TruckDeleteRequest;
import com.tcwong.pengms.dto.truck.TruckEditRequest;
import com.tcwong.pengms.dto.truck.TruckPageRequest;
import com.tcwong.pengms.service.TruckService;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * Description 车辆管理
 *
 * @author tcwong
 * @date 2021/3/20 Since 1.8
 */
@RestController
@RequestMapping("/pengms/truck/")
public class TruckController {

    @Resource private TruckService truckService;

    /**
     * Description 车辆添加
     *
     * @param request 车辆添加请求
     * @return Integer 成功条数
     * @author tcwong
     * @date 2021/3/20
     */
    @LogFilter(description = "车辆添加", logOperationType = LogOperationType.ADD)
    @PostMapping("/add")
    public WebResponse addTruck(@RequestBody TruckAddRequest request) {
        Integer num = truckService.addTruck(request);
        if (num > 0) {
            return WebResponse.success("添加成功");
        }
        return WebResponse.failed("添加失败");
    }

    /**
     * Description 车量编辑
     *
     * @param request 车辆编辑请求
     * @return Integer 成功条数
     * @author tcwong
     * @date 2021/3/20
     */
    @LogFilter(description = "车辆修改", logOperationType = LogOperationType.UPDATE)
    @PutMapping("/edit")
    public WebResponse editTruck(@RequestBody TruckEditRequest request) {
        Integer num = truckService.editTruck(request);
        if (num > 0) {
            return WebResponse.success("编辑成功");
        }
        return WebResponse.failed("编辑失败");
    }

    /**
     * Description 删除车辆
     *
     * @param request 车辆删除请求
     * @return Integer 成功条数
     * @author tcwong
     * @date 2021/3/20
     */
    @LogFilter(description = "车辆删除", logOperationType = LogOperationType.DELETE)
    @DeleteMapping("/delete")
    public WebResponse deleteTruck(@RequestBody TruckDeleteRequest request) {
        Integer num = truckService.deleteTruck(request);
        if (num > 0) {
            return WebResponse.success("删除成功");
        }
        return WebResponse.failed("删除失败");
    }

    /**
     * Description 车辆分页查询
     *
     * @param request 车辆分页查询请求
     * @return WebPageResponse 车辆分页
     * @author tcwong
     * @date 2021/3/20
     */
    @PostMapping("/page")
    public WebResponse pageTruck(@RequestBody TruckPageRequest request) {
        WebPageResponse pageResponse = truckService.pageTruck(request);
        if (pageResponse != null) {
            return WebResponse.success(pageResponse, "查询成功");
        }
        return WebResponse.failed("查询失败");
    }
}
