package com.tcwong.pengms.controller;

import com.tcwong.pengms.base.LogFilter;
import com.tcwong.pengms.base.WebPageResponse;
import com.tcwong.pengms.base.WebResponse;
import com.tcwong.pengms.constant.LogOperationType;
import com.tcwong.pengms.model.Carriers;
import com.tcwong.pengms.service.CarriersService;
import com.tcwong.pengms.service.GoodsService;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/** 承运单 */
@RestController
@RequestMapping("/pengms/carriers/")
public class CarriersController {

    @Resource private CarriersService carriersService;
    @Resource private GoodsService goodsService;

    @LogFilter(description = "添加承运单货物", logOperationType = LogOperationType.ADD)
    @PostMapping("/add")
    public WebResponse addCarriersGoods(@RequestBody Carriers carriers) {

        int num = carriersService.addCarrier(carriers);
        if (num > 0) {
            return WebResponse.success("添加成功");
        }
        return WebResponse.failed("添加失败");
    }

    @LogFilter(description = "删除承运单", logOperationType = LogOperationType.DELETE)
    @DeleteMapping("/delete/{ids}")
    public WebResponse deleteCarriersGoods(@PathVariable String ids) {
        int num1 = carriersService.deleteCarrier(ids);
        int num2 = goodsService.deleteGoods(ids);
        if (num1 > 0 && num2 > 0) {
            return WebResponse.success("删除成功");
        }
        return WebResponse.failed("删除失败");
    }

    @LogFilter(description = "修改承运单", logOperationType = LogOperationType.UPDATE)
    @PutMapping("/put")
    public WebResponse editCarriersGoods(@RequestBody Carriers carriers) {
        int num = carriersService.editCarrier(carriers);
        if (num > 0) {
            return WebResponse.success("修改成功");
        }
        return WebResponse.failed("修改失败");
    }

    @PostMapping("/getAll")
    public WebResponse getAllByPage(
            Integer page,
            Integer size,
            String sendcompany,
            String receivecompany,
            Integer finishedstate) {
        WebPageResponse pageResponse =
                carriersService.getAllByPage(
                        page, size, sendcompany, receivecompany, finishedstate);
        if (pageResponse != null) {
            return WebResponse.success(pageResponse);
        }
        return WebResponse.failed("查询失败");
    }

    @LogFilter(description = "承运单接收", logOperationType = LogOperationType.UPDATE)
    @PutMapping("/receive/{id}")
    public WebResponse reveiveById(@PathVariable Integer id) {
        int num = carriersService.reveiveById(id);
        if (num > 0) {
            return WebResponse.success("接收成功");
        }
        return WebResponse.failed("接收失败");
    }
}
