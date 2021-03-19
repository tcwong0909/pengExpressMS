package com.tcwong.pengms.service;

import com.tcwong.pengms.base.WebPageResponse;
import com.tcwong.pengms.model.Truck;
import java.util.List;

public interface TruckService {
    int addTruck(Truck truck);

    WebPageResponse getAllTrucksByPage(Integer page, Integer size);

    int editTruck(Truck truck);

    int deleteByIds(String ids);

    WebPageResponse search(
            Integer page, Integer size, String number, String type, Integer fkTeamid);

    List<Truck> selectTruck();
}
