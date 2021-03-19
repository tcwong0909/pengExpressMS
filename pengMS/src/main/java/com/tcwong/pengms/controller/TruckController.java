package com.tcwong.pengms.controller;

import com.tcwong.pengms.base.LogFilter;
import com.tcwong.pengms.base.WebPageResponse;
import com.tcwong.pengms.base.WebResponse;
import com.tcwong.pengms.constant.LogOperationType;
import com.tcwong.pengms.model.Truck;
import com.tcwong.pengms.service.TruckService;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/** 车辆 */
@RestController
@RequestMapping("/pengms/truck/")
public class TruckController {

    @Resource private TruckService truckService;

    @LogFilter(description = "车辆添加", logOperationType = LogOperationType.ADD)
    @PostMapping("/add")
    public WebResponse addTruck(@RequestBody Truck truck) {
        int num = truckService.addTruck(truck);
        if (num > 0) {
            return WebResponse.success("添加成功");
        }
        return WebResponse.failed("添加失败");
    }

    @LogFilter(description = "车辆修改", logOperationType = LogOperationType.UPDATE)
    @PutMapping("/put")
    public WebResponse editTruck(@RequestBody Truck truck) {
        int num = truckService.editTruck(truck);
        if (num > 0) {
            return WebResponse.success("编辑成功");
        }
        return WebResponse.failed("编辑失败");
    }

    @LogFilter(description = "车辆删除", logOperationType = LogOperationType.DELETE)
    @DeleteMapping("/delete/{ids}")
    public WebResponse deleteByIds(@PathVariable String ids) {
        int num = truckService.deleteByIds(ids);
        if (num > 0) {
            return WebResponse.success("删除成功");
        }
        return WebResponse.failed("删除失败");
    }

    @PostMapping("/getAllByPage")
    public WebResponse getAllTrucksByPage(
            Integer page, Integer size, String number, String type, Integer fkTeamid) {
        WebPageResponse pageResponse = truckService.search(page, size, number, type, fkTeamid);
        if (pageResponse != null) {
            return WebResponse.success(pageResponse, "查询成功");
        }
        return WebResponse.failed("查询失败");
    }

    @GetMapping("/search")
    public WebResponse doSearch(
            Integer page, Integer size, String number, String type, String fkTeamid) {
        String number1 = null;
        String type1 = null;
        Integer fkTeamid1 = null;

        try {
            number1 = URLDecoder.decode(number, "utf-8");
            type1 = URLDecoder.decode(type, "utf-8");
            if (fkTeamid != null && fkTeamid != "") {
                fkTeamid1 = Integer.parseInt(URLDecoder.decode(fkTeamid, "utf-8"));
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        WebPageResponse search = truckService.search(page, size, number1, type1, fkTeamid1);
        if (search != null) {
            return WebResponse.success(search, "查询成功");
        }
        return WebResponse.failed("为查询到结果");
    }

    @RequestMapping("/getTrucks")
    public WebResponse selectTruck() {
        List<Truck> trucks = truckService.selectTruck();
        if (trucks != null) {
            return WebResponse.success(trucks);
        }
        return WebResponse.failed("查询失败");
    }
}
