package com.tcwong.pengms.service;

import com.github.pagehelper.PageInfo;
import com.tcwong.pengms.dto.OperateLogRequest;
import com.tcwong.pengms.model.OperateLog;

/**
 * Description
 *
 * @author tcwong
 * @date 2020/8/24 Since 1.8
 */
public interface OperateLogService {

    /**
     * Description 添加系统日志
     *
     * @param operateLog 系统日志
     * @return
     * @author tcwong
     * @date 2020/8/31
     */
    Integer insert(OperateLog operateLog);

    /**
     * Description 查询系统日志
     *
     * @param request 系统日志界面查询
     * @return
     * @author tcwong
     * @date 2020/8/31
     */
    PageInfo<OperateLog> listOperateLog(OperateLogRequest request);
}
