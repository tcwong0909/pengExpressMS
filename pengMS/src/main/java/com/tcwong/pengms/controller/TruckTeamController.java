package com.tcwong.pengms.controller;

import com.tcwong.pengms.base.LogFilter;
import com.tcwong.pengms.base.WebPageResponse;
import com.tcwong.pengms.base.WebResponse;
import com.tcwong.pengms.constant.LogOperationType;
import com.tcwong.pengms.dto.team.TruckTeamAddRequest;
import com.tcwong.pengms.dto.team.TruckTeamDeleteRequest;
import com.tcwong.pengms.dto.team.TruckTeamEditRequest;
import com.tcwong.pengms.dto.team.TruckTeamPageRequest;
import com.tcwong.pengms.service.TruckTeamService;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * Description 车队管理
 *
 * @author tcwong
 * @date 2021/3/20 Since 1.8
 */
@RestController
@RequestMapping("/pengms/truckTeam/")
public class TruckTeamController {

    @Resource private TruckTeamService truckTeamService;

    /**
     * Description 添加车队
     *
     * @param request 车队请求
     * @return Integer 操作成功数量
     * @author tcwong
     * @date 2021/3/20
     */
    @LogFilter(description = "车队添加", logOperationType = LogOperationType.ADD)
    @PostMapping("/add")
    public WebResponse addTruckTeam(@RequestBody TruckTeamAddRequest request) {
        Integer num = truckTeamService.addTruckTeam(request);
        if (num > 0) {
            return WebResponse.success("添加成功");
        }
        return WebResponse.failed("添加失败");
    }

    /**
     * Description 车队删除
     *
     * @param request 车队删除请求
     * @return Integer 操作成功数量
     * @author tcwong
     * @date 2021/3/20
     */
    @LogFilter(description = "车队删除", logOperationType = LogOperationType.DELETE)
    @PostMapping("/delete")
    public WebResponse deleteTruckTeam(@RequestBody TruckTeamDeleteRequest request) {
        int num = truckTeamService.deleteTruckTeam(request);
        if (num > 0) {
            return WebResponse.success("删除成功");
        }
        return WebResponse.failed("删除失败");
    }

    /**
     * Description 车队编辑
     *
     * @param request 车队编辑参数
     * @return Integer 操作成功数量
     * @author tcwong
     * @date 2021/3/20
     */
    @LogFilter(description = "车队修改", logOperationType = LogOperationType.UPDATE)
    @PostMapping("/edit")
    public WebResponse editTruckTeam(@RequestBody TruckTeamEditRequest request) {
        Integer num = truckTeamService.editTruckTeam(request);
        if (num > 0) {
            return WebResponse.success(num, "修改成功");
        }
        return WebResponse.failed("修改失败");
    }

    /**
     * Description 车队分页查询
     *
     * @param request 分页查询
     * @return WebPageResponse 车队分页结果集
     * @author tcwong
     * @date 2021/3/20
     */
    @PostMapping("/page")
    public WebResponse pageTruckTeam(@RequestBody TruckTeamPageRequest request) {
        WebPageResponse webPageResponse = truckTeamService.pageTruckTeam(request);
        if (webPageResponse != null) {
            return WebResponse.success(webPageResponse, "查询成功");
        }
        return WebResponse.failed("查询失败");
    }
}
