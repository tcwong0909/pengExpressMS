package com.tcwong.pengms.service;


import com.tcwong.pengms.model.Contact;
import com.tcwong.pengms.utils.WebPageResponse;

public interface IContactService {
     WebPageResponse getAll(Integer page, Integer size, Integer fkTruckid, Integer fkDriverid, String drivername,
                            String teamname, Integer bindId) ;


     int bindTruck(Contact contact);

     int unbindTrucks(String ids);
}
