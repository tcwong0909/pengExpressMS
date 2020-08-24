package com.tcwong.pengms.service.impl;

import com.tcwong.pengms.dao.OperateLogMapper;
import com.tcwong.pengms.model.OperateLog;
import com.tcwong.pengms.service.OperateLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description
 *
 * @author tcwong
 * @date 2020/8/24
 * Since 1.8
 */

@Service
public class OperateLogServiceImpl implements OperateLogService {

    @Autowired
    private OperateLogMapper operateLogMapper;
    @Override
    public Integer insert(OperateLog operateLog) {
        int resultNum = operateLogMapper.insertSelective(operateLog);
        return resultNum;
    }
}
