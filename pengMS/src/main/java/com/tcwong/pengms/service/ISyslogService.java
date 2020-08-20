package com.tcwong.pengms.service;


import com.tcwong.pengms.model.Syslog;
import com.tcwong.pengms.utils.WebPageResponse;

public interface ISyslogService {

    int addSyslog(Syslog syslog);

    WebPageResponse getAllSyslog(Integer page, Integer size, String behavior, String fkTypeid, String fkUserid, Integer isexception);

}
