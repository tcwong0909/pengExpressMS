package com.tcwong.pengms.service;


import com.tcwong.pengms.model.Driver;
import com.tcwong.pengms.base.WebPageResponse;

public interface DriverService {
    int addDriver(Driver driver);

    int deleteByIds(String ids);

    int editDriver(Driver driver);

    WebPageResponse getAllDriverByPage(Integer page, Integer size, String name, Integer fkTeamid, Integer state);
}
