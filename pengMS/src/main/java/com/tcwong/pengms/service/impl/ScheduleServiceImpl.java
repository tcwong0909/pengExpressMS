package com.tcwong.pengms.service.impl;

import com.tcwong.pengms.base.WebPageResponse;
import com.tcwong.pengms.dao.SchedulingMapper;
import com.tcwong.pengms.dao.TruckMapper;
import com.tcwong.pengms.model.Scheduling;
import com.tcwong.pengms.model.Truck;
import com.tcwong.pengms.service.ScheduleService;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Resource private SchedulingMapper schedulingMapper;
    @Resource private TruckMapper truckMapper;

    @Override
    public int dispatchById(Scheduling scheduling) {
        //
        // scheduling.setFkUserid(((User)(SecurityUtils.getSubject().getSession().getAttribute("user"))).getUserid());
        scheduling.setCheckintime(new Date());
        scheduling.setAltertime(new Date());
        schedulingMapper.insert(scheduling);
        Truck truck = new Truck();
        truck.setState(1);
        truckMapper.updateByPrimaryKeySelective(truck);
        //        Carriers carriers = new Carriers();
        //        carriers.setCarriersid(scheduling.getFkCarriersid());
        //        carriers.setFinishedstate(2);
        //        carriers.setAltertime(new Date());
        //        carriersMapper.updateByPrimaryKeySelective(carriers);
        return 1;
    }

    @Override
    public WebPageResponse getSchedules(
            Integer page,
            Integer size,
            Integer schedulingid,
            Integer fkCarriersid,
            String sendcompany,
            String receivecompany) {
        if (page != null && size != null) {
            page = (page - 1) * size;
        }
        return new WebPageResponse(
                schedulingMapper.getTotal(schedulingid, fkCarriersid, sendcompany, receivecompany),
                schedulingMapper.getAllByPage(
                        page, size, schedulingid, fkCarriersid, sendcompany, receivecompany));
    }

    @Override
    public int deleteMore(List<Scheduling> scheduleList) {
        int size = scheduleList.size();
        StringBuilder schedulingidBuilder = new StringBuilder();
        StringBuilder fkTruckidBuilder = new StringBuilder();
        StringBuilder fkCarriersidBuilder = new StringBuilder();
        scheduleList.forEach(
                (schedule) -> {
                    Integer schedulingid = schedule.getSchedulingid();
                    Integer fkTruckid = schedule.getFkTruckid();
                    Integer fkCarriersid = schedule.getFkCarriersid();
                    if (schedulingid != null) {
                        schedulingidBuilder.append(schedulingid).append(",");
                    }
                    if (fkTruckid != null) {
                        fkTruckidBuilder.append(fkTruckid).append(",");
                    }
                    if (fkCarriersid != null) {
                        fkCarriersidBuilder.append(fkCarriersid).append(",");
                    }
                });
        String[] schedulingIds = schedulingidBuilder.toString().split(",");
        String[] carriersIds = fkCarriersidBuilder.toString().split(",");
        String[] truckIds = fkTruckidBuilder.toString().split(",");
        schedulingMapper.deleteByIds(schedulingIds);
        //        carriersMapper.updateByIds(carriersIds);
        return size;
    }

    @Override
    public int addCost(Scheduling scheduling) {
        scheduling.setAltertime(new Date());
        Integer fkCarriersid = scheduling.getFkCarriersid();
        //        Carriers carriers = new Carriers();
        //        carriers.setFinishedstate(4);
        //        carriers.setCarriersid(fkCarriersid);
        //        carriersMapper.updateByPrimaryKeySelective(carriers);
        return schedulingMapper.updateByPrimaryKeySelective(scheduling);
    }
}
