package com.tcwong.pengms.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tcwong.pengms.dao.OperateLogMapper;
import com.tcwong.pengms.dto.OperateLogRequest;
import com.tcwong.pengms.model.OperateLog;
import com.tcwong.pengms.model.example.OperateLogExample;
import com.tcwong.pengms.service.OperateLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    /**
     * Description 查询日志列表
     *
     * @param request 请求
     * @return
     * @author tcwong
     * @date 2020/8/31
     */
    @Override
    public PageInfo<OperateLog> listOperateLog(OperateLogRequest request) {
        PageHelper.startPage(request.getPageNum(), request.getPageSize());
        OperateLogExample operateLogExample = new OperateLogExample();
        List<OperateLog> operateLogs = operateLogMapper.selectByExample(operateLogExample);
        return new PageInfo<>(operateLogs);
    }
}
