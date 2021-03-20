package com.tcwong.pengms.service;

import com.tcwong.pengms.base.WebPageResponse;
import com.tcwong.pengms.dto.truck.TruckAddRequest;
import com.tcwong.pengms.dto.truck.TruckDeleteRequest;
import com.tcwong.pengms.dto.truck.TruckEditRequest;
import com.tcwong.pengms.dto.truck.TruckPageRequest;

/**
 * Description 车辆管理服务
 *
 * @author tcwong
 * @date 2021/3/20 Since 1.8
 */
public interface TruckService {
    /**
     * Description 车辆添加
     *
     * @param request 车辆添加请求
     * @return Integer 成功条数
     * @author tcwong
     * @date 2021/3/20
     */
    Integer addTruck(TruckAddRequest request);

    /**
     * Description 车量编辑
     *
     * @param request 车辆编辑请求
     * @return Integer 成功条数
     * @author tcwong
     * @date 2021/3/20
     */
    Integer editTruck(TruckEditRequest request);

    /**
     * Description 删除车辆
     *
     * @param request 车辆删除请求
     * @return Integer 成功条数
     * @author tcwong
     * @date 2021/3/20
     */
    Integer deleteTruck(TruckDeleteRequest request);

    /**
     * Description 车辆分页查询
     *
     * @param request 车辆分页查询请求
     * @return WebPageResponse 车辆分页
     * @author tcwong
     * @date 2021/3/20
     */
    WebPageResponse pageTruck(TruckPageRequest request);
}
