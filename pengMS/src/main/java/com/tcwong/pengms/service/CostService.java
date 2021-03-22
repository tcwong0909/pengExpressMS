package com.tcwong.pengms.service;

import com.tcwong.pengms.base.WebPageResponse;
import com.tcwong.pengms.dto.cost.CostAddRequest;
import com.tcwong.pengms.dto.cost.CostPageRequest;

/**
 * Description 成本服务
 *
 * @author tcwong
 * @date 2021/3/22 Since 1.8
 */
public interface CostService {

    /**
     * Description 成本查询
     *
     * @param request 成本分页查询请求
     * @return WebPageResponse 成本分页结果集
     * @author tcwong
     * @date 2021/3/22
     */
    WebPageResponse pageCost(CostPageRequest request);

    /**
     * Description 成本添加
     *
     * @param request 成本添加请求
     * @return Integer 成功条数
     * @author tcwong
     * @date 2021/3/22
     */
    Integer addCost(CostAddRequest request);
}
