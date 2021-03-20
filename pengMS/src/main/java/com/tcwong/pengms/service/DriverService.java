package com.tcwong.pengms.service;

import com.tcwong.pengms.base.WebPageResponse;
import com.tcwong.pengms.dto.driver.DriverAddRequest;
import com.tcwong.pengms.dto.driver.DriverDeleteRequest;
import com.tcwong.pengms.dto.driver.DriverEditRequest;
import com.tcwong.pengms.dto.driver.DriverPageRequest;

public interface DriverService {

    /**
     * Description 添加司机
     *
     * @param request 司机添加请求
     * @return Integer 成功条数
     * @author tcwong
     * @date 2021/3/20
     */
    Integer addDriver(DriverAddRequest request);

    /**
     * Description 删除司机
     *
     * @param request 司机删除参数
     * @return Integer 成功条数
     * @author tcwong
     * @date 2021/3/20
     */
    Integer deleteDriver(DriverDeleteRequest request);

    /**
     * Description 司机编辑
     *
     * @param request 司机编辑参数
     * @return Integer 成功条数
     * @author tcwong
     * @date 2021/3/20
     */
    Integer editDriver(DriverEditRequest request);

    /**
     * Description 司机分页查询
     *
     * @param request 司机分页查询请求
     * @return 司机分页结果
     * @author tcwong
     * @date 2021/3/20
     */
    WebPageResponse pageDriver(DriverPageRequest request);
}
