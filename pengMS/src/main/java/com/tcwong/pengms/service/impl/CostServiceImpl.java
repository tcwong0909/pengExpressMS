package com.tcwong.pengms.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tcwong.pengms.base.WebPageResponse;
import com.tcwong.pengms.constant.DeletedEnum;
import com.tcwong.pengms.dao.CostMapper;
import com.tcwong.pengms.dto.cost.CostAddRequest;
import com.tcwong.pengms.dto.cost.CostPageRequest;
import com.tcwong.pengms.model.Cost;
import com.tcwong.pengms.model.User;
import com.tcwong.pengms.model.example.CostExample;
import com.tcwong.pengms.service.CostService;
import com.tcwong.pengms.utils.PengContext;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * Description 成本服务
 *
 * @author tcwong
 * @date 2021/3/22 Since 1.8
 */
@Service()
public class CostServiceImpl implements CostService {

    @Resource private CostMapper costMapper;

    /**
     * Description 成本查询
     *
     * @param request 成本分页查询请求
     * @return WebPageResponse 成本分页结果集
     * @author tcwong
     * @date 2021/3/22
     */
    @Override
    public WebPageResponse pageCost(CostPageRequest request) {
        PageHelper.startPage(request.getPageNum(), request.getPageSize());
        CostExample costExample = new CostExample();
        costExample.setOrderByClause("id DESC");
        List<Cost> costList = costMapper.selectByExample(costExample);
        PageInfo<Cost> costPageInfo = new PageInfo<>(costList);
        return new WebPageResponse(costPageInfo.getTotal(), costPageInfo.getList());
    }

    /**
     * Description 成本添加
     *
     * @param request 成本添加请求
     * @return Integer 成功条数
     * @author tcwong
     * @date 2021/3/22
     */
    @Override
    public Integer addCost(CostAddRequest request) {
        User user = PengContext.getUser();
        Date nowDate = new Date();
        Cost cost =
                Cost.builder()
                        .insurance(request.getInsurance())
                        .transport(request.getTransport())
                        .extra(request.getExtra())
                        .total(
                                request.getInsurance()
                                        .add(request.getTransport().add(request.getExtra())))
                        .remark(request.getRemark())
                        .isDelete(DeletedEnum.UN_DELETED.getState())
                        .updateBy(user.getUsername())
                        .createBy(user.getUsername())
                        .updateTime(nowDate)
                        .createTime(nowDate)
                        .build();
        return costMapper.insertSelective(cost);
    }
}
