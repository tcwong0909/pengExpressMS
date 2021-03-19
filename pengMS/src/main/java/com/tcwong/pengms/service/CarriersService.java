package com.tcwong.pengms.service;

import com.tcwong.pengms.base.WebPageResponse;
import com.tcwong.pengms.model.Carriers;

public interface CarriersService {
    int addCarrier(Carriers carriers);

    int editCarrier(Carriers carriers);

    WebPageResponse getAllByPage(
            Integer page,
            Integer size,
            String sendcompany,
            String receivecompany,
            Integer finishedstate);

    int deleteCarrier(String ids);

    int reveiveById(Integer id);
}
