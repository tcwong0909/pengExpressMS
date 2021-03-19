package com.tcwong.pengms.service.impl;

import com.tcwong.pengms.base.WebPageResponse;
import com.tcwong.pengms.dao.ContactMapper;
import com.tcwong.pengms.model.Contact;
import com.tcwong.pengms.service.ContactService;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceImpl implements ContactService {

    @Resource private ContactMapper contactMapper;

    @Override
    public WebPageResponse getAll(
            Integer page,
            Integer size,
            Integer fkTruckid,
            Integer fkDriverid,
            String drivername,
            String teamname,
            Integer bindId) {
        if (page != null && size != null) {
            page = (page - 1) * size;
        }
        return new WebPageResponse(
                contactMapper.getTotal(fkTruckid, fkDriverid, drivername, teamname, bindId),
                contactMapper.getAll(
                        page, size, fkTruckid, fkDriverid, drivername, teamname, bindId));
    }

    @Override
    public int bindTruck(Contact contact) {
        return contactMapper.updateByPrimaryKeySelective(contact);
    }

    @Override
    public int unbindTrucks(String ids) {
        String[] split = ids.split(",");

        contactMapper.unbindTrucks(split);
        return split.length;
    }
}
