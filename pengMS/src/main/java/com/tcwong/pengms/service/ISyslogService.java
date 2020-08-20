package com.tcwong.pengms.service;

import com.tcwong.bean.Syslog;
import com.tcwong.common.WebPageResponse;

public interface ISyslogService {

    int addSyslog(Syslog syslog);

    WebPageResponse getAllSyslog(Integer page, Integer size, String behavior, String fkTypeid, String fkUserid, Integer isexception);

}
