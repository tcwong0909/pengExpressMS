package com.tcwong.pengms.service;


import com.tcwong.pengms.model.Truck;
import com.tcwong.pengms.utils.WebPageResponse;

import java.util.List;

public interface ITruckService {
    int addTruck(Truck truck);


    WebPageResponse getAllTrucksByPage(Integer page, Integer size);

    int  editTruck(Truck truck);

    int deleteByIds(String ids);

    WebPageResponse search(Integer page, Integer size, String number, String type, Integer fkTeamid);

    List<Truck> selectTruck();
}
