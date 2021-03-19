package com.tcwong.pengms.service;

import com.tcwong.pengms.base.WebPageResponse;
import com.tcwong.pengms.model.Contact;

public interface ContactService {
    WebPageResponse getAll(
            Integer page,
            Integer size,
            Integer fkTruckid,
            Integer fkDriverid,
            String drivername,
            String teamname,
            Integer bindId);

    int bindTruck(Contact contact);

    int unbindTrucks(String ids);
}
