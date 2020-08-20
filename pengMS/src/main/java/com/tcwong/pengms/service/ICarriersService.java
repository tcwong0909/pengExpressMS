package com.tcwong.pengms.service;


import com.tcwong.pengms.model.Carriers;
import com.tcwong.pengms.utils.WebPageResponse;

public interface ICarriersService {
    int addCarrier(Carriers carriers);

    int editCarrier(Carriers carriers);

    WebPageResponse getAllByPage(Integer page, Integer size, String sendcompany, String receivecompany, Integer finishedstate);

    int deleteCarrier(String ids);

    int reveiveById(Integer id);
}
