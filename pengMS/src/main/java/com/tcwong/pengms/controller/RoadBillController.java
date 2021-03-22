package com.tcwong.pengms.controller;

import com.tcwong.pengms.base.LogFilter;
import com.tcwong.pengms.base.WebPageResponse;
import com.tcwong.pengms.base.WebResponse;
import com.tcwong.pengms.constant.LogOperationType;
import com.tcwong.pengms.dto.bill.RoadBillAddRequest;
import com.tcwong.pengms.dto.bill.RoadBillDeleteRequest;
import com.tcwong.pengms.dto.bill.RoadBillEditRequest;
import com.tcwong.pengms.dto.bill.RoadBillPageRequest;
import com.tcwong.pengms.service.RoadBillService;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * Description 承运单管理
 *
 * @author tcwong
 * @date 2021/3/22 Since 1.8
 */
@RestController
@RequestMapping("/pengms/bill/")
public class RoadBillController {

    @Resource private RoadBillService roadBillService;

    /**
     * Description 运单添加
     *
     * @param request 运单添加请求
     * @return Integer 成功条数
     * @author tcwong
     * @date 2021/3/22
     */
    @LogFilter(description = "运单添加", logOperationType = LogOperationType.ADD)
    @PostMapping("/add")
    public WebResponse addRoadBill(@RequestBody RoadBillAddRequest request) {
        Integer num = roadBillService.addRoadBill(request);
        if (num > 0) {
            return WebResponse.success("添加成功");
        }
        return WebResponse.failed("添加失败");
    }

    /**
     * Description 运单删除
     *
     * @param request 运单删除请求
     * @return Integer 成功条数
     * @author tcwong
     * @date 2021/3/22
     */
    @LogFilter(description = "运单删除", logOperationType = LogOperationType.DELETE)
    @PostMapping("/delete")
    public WebResponse deleteRoadBill(@RequestBody RoadBillDeleteRequest request) {
        Integer num = roadBillService.deleteRoadBill(request);
        if (num > 0) {
            return WebResponse.success("删除成功");
        }
        return WebResponse.failed("删除失败");
    }

    /**
     * Description 运单编辑
     *
     * @param request 运单编辑请求
     * @return Integer 成功条数
     * @author tcwong
     * @date 2021/3/22
     */
    @LogFilter(description = "运单编辑", logOperationType = LogOperationType.UPDATE)
    @PostMapping("/edit")
    public WebResponse editRoadBill(@RequestBody RoadBillEditRequest request) {
        int num = roadBillService.editRoadBill(request);
        if (num > 0) {
            return WebResponse.success("修改成功");
        }
        return WebResponse.failed("修改失败");
    }

    /**
     * Description 运单分页查询
     *
     * @param request 运单分页查询参数
     * @return WebPageResponse 运单分页结果集
     * @author tcwong
     * @date 2021/3/22
     */
    @PostMapping("/page")
    public WebResponse pageRoadBill(@RequestBody RoadBillPageRequest request) {
        WebPageResponse pageResponse = roadBillService.pageRoadBill(request);
        if (pageResponse != null) {
            return WebResponse.success(pageResponse);
        }
        return WebResponse.failed("查询失败");
    }
}
