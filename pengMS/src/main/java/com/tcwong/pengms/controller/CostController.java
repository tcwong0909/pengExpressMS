package com.tcwong.pengms.controller;

import com.tcwong.pengms.base.LogFilter;
import com.tcwong.pengms.base.WebPageResponse;
import com.tcwong.pengms.base.WebResponse;
import com.tcwong.pengms.constant.LogOperationType;
import com.tcwong.pengms.dto.cost.CostAddRequest;
import com.tcwong.pengms.dto.cost.CostPageRequest;
import com.tcwong.pengms.service.CostService;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * Description 成本管理
 *
 * @author tcwong
 * @date 2021/3/22 Since 1.8
 */
@RestController
@RequestMapping("/pengms/cost")
public class CostController {

    @Resource private CostService costService;

    /**
     * Description 成本查询
     *
     * @param request 成本分页查询请求
     * @return WebPageResponse 成本分页结果集
     * @author tcwong
     * @date 2021/3/22
     */
    @PostMapping("/page")
    public WebResponse pageCost(@RequestBody CostPageRequest request) {
        WebPageResponse pageResponse = costService.pageCost(request);
        if (pageResponse != null) {
            return WebResponse.success(pageResponse, "查询成功");
        }
        return WebResponse.failed("查询失败");
    }

    /**
     * Description 成本添加
     *
     * @param request 成本添加请求
     * @return Integer 成功条数
     * @author tcwong
     * @date 2021/3/22
     */
    @LogFilter(description = "成本添加", logOperationType = LogOperationType.ADD)
    @PutMapping("/add")
    public Integer addCost(@RequestBody CostAddRequest request) {
        return costService.addCost(request);
    }
}
