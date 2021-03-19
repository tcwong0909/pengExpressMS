package com.tcwong.pengms.service.impl;

import com.tcwong.pengms.base.WebPageResponse;
import com.tcwong.pengms.dao.ContactMapper;
import com.tcwong.pengms.dao.DriverMapper;
import com.tcwong.pengms.model.Contact;
import com.tcwong.pengms.model.Driver;
import com.tcwong.pengms.service.DriverService;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class DriverServiceImpl implements DriverService {

    @Resource private DriverMapper driverMapper;
    @Resource private ContactMapper contactMapper;

    @Override
    public int addDriver(Driver driver) {
        driver.setAltertime(new Date());
        driver.setCheckintime(new Date());
        driverMapper.insert(driver);
        Integer driverid = driver.getDriverid();
        Contact contact = new Contact();
        contact.setFkDriverid(driverid);
        return contactMapper.insert(contact);
    }

    @Override
    public int deleteByIds(String ids) {
        String[] split = ids.split(",");
        driverMapper.deleteByIds(split);
        contactMapper.deleteByIds(split);
        return split.length;
    }

    @Override
    public int editDriver(Driver driver) {
        driver.setAltertime(new Date());
        return driverMapper.updateByPrimaryKey(driver);
    }

    @Override
    public WebPageResponse getAllDriverByPage(
            Integer page, Integer size, String name, Integer fkTeamid, Integer state) {
        if (page != null && size != null) {
            page = (page - 1) * size;
        }
        List<Driver> allDrivers =
                driverMapper.getAllDriverByPage(page, size, name, fkTeamid, state);
        return new WebPageResponse(driverMapper.getTotal(name, fkTeamid, state), allDrivers);
    }
}
