package com.tcwong.pengms.service;


import com.tcwong.pengms.model.Truckteam;
import com.tcwong.pengms.base.WebPageResponse;

public interface TruckTeamService {
    int addTruckTeam(Truckteam truckteam);

    int deleteByIds(String ids);

    int editTruckTeam(Truckteam truckteam);

    WebPageResponse getAllByPage(Integer page, Integer size, String teamname, String leader);
}
