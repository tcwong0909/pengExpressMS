package com.tcwong.pengms.service.impl;

import com.tcwong.pengms.dao.SyslogMapper;
import com.tcwong.pengms.model.Syslog;
import com.tcwong.pengms.service.ISyslogService;
import com.tcwong.pengms.utils.WebPageResponse;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SyslogServiceImpl implements ISyslogService {

    @Resource
    private SyslogMapper syslogMapper;

    @Override
    public int addSyslog(Syslog syslog) {
        return syslogMapper.insert(syslog);
    }

    @Override
    public WebPageResponse getAllSyslog(Integer page, Integer size, String behavior, String fkTypeid, String fkUserid, Integer isexception) {
        if (page != null && size != null) {
            page = (page - 1)*size;
        }

        return new WebPageResponse(syslogMapper.getTotal(behavior, fkTypeid, fkUserid, isexception),
                syslogMapper.getAll(page,size,behavior, fkTypeid, fkUserid, isexception));
    }


}
