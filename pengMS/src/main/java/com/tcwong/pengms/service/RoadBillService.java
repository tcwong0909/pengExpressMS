package com.tcwong.pengms.service;

import com.tcwong.pengms.base.WebPageResponse;
import com.tcwong.pengms.dto.bill.RoadBillAddRequest;
import com.tcwong.pengms.dto.bill.RoadBillDeleteRequest;
import com.tcwong.pengms.dto.bill.RoadBillEditRequest;
import com.tcwong.pengms.dto.bill.RoadBillPageRequest;

public interface RoadBillService {
    /**
     * Description 运单添加
     *
     * @param request 运单添加请求
     * @return Integer 成功条数
     * @author tcwong
     * @date 2021/3/22
     */
    Integer addRoadBill(RoadBillAddRequest request);

    /**
     * Description 运单编辑
     *
     * @param request 运单编辑请求
     * @return Integer 成功条数
     * @author tcwong
     * @date 2021/3/22
     */
    Integer editRoadBill(RoadBillEditRequest request);

    /**
     * Description 运单分页查询
     *
     * @param request 运单分页查询参数
     * @return WebPageResponse 运单分页结果集
     * @author tcwong
     * @date 2021/3/22
     */
    WebPageResponse pageRoadBill(RoadBillPageRequest request);

    /**
     * Description 运单删除
     *
     * @param request 运单删除请求
     * @return Integer 成功条数
     * @author tcwong
     * @date 2021/3/22
     */
    Integer deleteRoadBill(RoadBillDeleteRequest request);
}
