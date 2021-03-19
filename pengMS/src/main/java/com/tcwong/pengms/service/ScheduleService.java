package com.tcwong.pengms.service;

import com.tcwong.pengms.base.WebPageResponse;
import com.tcwong.pengms.model.Scheduling;
import java.util.List;

public interface ScheduleService {
    int dispatchById(Scheduling scheduling);

    WebPageResponse getSchedules(
            Integer page,
            Integer size,
            Integer schedulingid,
            Integer fkCarriersid,
            String sendcompany,
            String receivecompany);

    int deleteMore(List<Scheduling> scheduleList);

    int addCost(Scheduling scheduling);
}
