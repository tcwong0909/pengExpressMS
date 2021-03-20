package com.tcwong.pengms.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tcwong.pengms.base.WebPageResponse;
import com.tcwong.pengms.constant.DeletedEnum;
import com.tcwong.pengms.dao.TruckMapper;
import com.tcwong.pengms.dto.truck.TruckAddRequest;
import com.tcwong.pengms.dto.truck.TruckDeleteRequest;
import com.tcwong.pengms.dto.truck.TruckEditRequest;
import com.tcwong.pengms.dto.truck.TruckPageRequest;
import com.tcwong.pengms.model.Truck;
import com.tcwong.pengms.model.User;
import com.tcwong.pengms.model.example.TruckExample;
import com.tcwong.pengms.service.TruckService;
import com.tcwong.pengms.utils.PengContext;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * Description 车辆管理服务
 *
 * @author tcwong
 * @date 2021/3/20 Since 1.8
 */
@Service
public class TruckServiceImpl implements TruckService {

    @Resource private TruckMapper truckMapper;

    /**
     * Description 车辆添加
     *
     * @param request 车辆添加请求
     * @return Integer 成功条数
     * @author tcwong
     * @date 2021/3/20
     */
    @Override
    public Integer addTruck(TruckAddRequest request) {
        User user = PengContext.getUser();
        Date nowDate = new Date();
        Truck truck =
                Truck.builder()
                        .plateNo(request.getPlateNo())
                        .type(request.getType())
                        .deadWeight(request.getDeadWeight())
                        .weightUnit(request.getWeightUnit())
                        .driverId(request.getDriverId())
                        .state(request.getState())
                        .buyTime(request.getBuyTime())
                        .remark(request.getRemark())
                        .isDelete(DeletedEnum.UN_DELETED.getState())
                        .checkTime(nowDate)
                        .updateBy(user.getUsername())
                        .createBy(user.getUsername())
                        .updateTime(nowDate)
                        .createTime(nowDate)
                        .build();
        return truckMapper.insertSelective(truck);
    }

    /**
     * Description 车量编辑
     *
     * @param request 车辆编辑请求
     * @return Integer 成功条数
     * @author tcwong
     * @date 2021/3/20
     */
    @Override
    public Integer editTruck(TruckEditRequest request) {
        User user = PengContext.getUser();
        Date nowDate = new Date();
        Truck truck =
                Truck.builder()
                        .id(request.getId())
                        .plateNo(request.getPlateNo())
                        .type(request.getType())
                        .deadWeight(request.getDeadWeight())
                        .weightUnit(request.getWeightUnit())
                        .driverId(request.getDriverId())
                        .state(request.getState())
                        .buyTime(request.getBuyTime())
                        .remark(request.getRemark())
                        .checkTime(nowDate)
                        .updateBy(user.getUsername())
                        .updateTime(nowDate)
                        .build();
        return truckMapper.updateByPrimaryKeySelective(truck);
    }

    /**
     * Description 删除车辆
     *
     * @param request 车辆删除请求
     * @return Integer 成功条数
     * @author tcwong
     * @date 2021/3/20
     */
    @Override
    public Integer deleteTruck(TruckDeleteRequest request) {
        Truck truck = Truck.builder().isDelete(DeletedEnum.DONE_DELETED.getState()).build();
        TruckExample truckExample = new TruckExample();
        truckExample
                .createCriteria()
                .andIdIn(request.getIdList())
                .andIsDeleteEqualTo(DeletedEnum.UN_DELETED.getState());
        return truckMapper.updateByExampleSelective(truck, truckExample);
    }

    /**
     * Description 车辆分页查询
     *
     * @param request 车辆分页查询请求
     * @return WebPageResponse 车辆分页
     * @author tcwong
     * @date 2021/3/20
     */
    @Override
    public WebPageResponse pageTruck(TruckPageRequest request) {
        TruckExample truckExample = new TruckExample();
        truckExample.setOrderByClause("id DESC");
        TruckExample.Criteria criteria = truckExample.createCriteria();
        if (ObjectUtil.isNotEmpty(request.getPlateNo())) {
            criteria.andPlateNoEqualTo(request.getPlateNo());
        }
        if (ObjectUtil.isNotEmpty(request.getType())) {
            criteria.andTypeEqualTo(request.getType());
        }
        PageHelper.startPage(request.getPageNum(), request.getPageSize());
        List<Truck> truckList = truckMapper.selectByExample(truckExample);
        PageInfo<Truck> truckPageInfo = new PageInfo<>(truckList);
        return new WebPageResponse(truckPageInfo.getTotal(), truckPageInfo.getList());
    }
}
