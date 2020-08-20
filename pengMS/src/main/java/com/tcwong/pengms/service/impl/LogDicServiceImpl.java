package com.tcwong.pengms.service.impl;


import com.tcwong.pengms.dao.LogdicMapper;
import com.tcwong.pengms.model.Logdic;
import com.tcwong.pengms.service.ILogDicService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class LogDicServiceImpl implements ILogDicService {
    @Resource
    private LogdicMapper logdicMapper;
    @Override
    public List<Logdic> getAll() {
        return logdicMapper.selectByExample(null);
    }
}
