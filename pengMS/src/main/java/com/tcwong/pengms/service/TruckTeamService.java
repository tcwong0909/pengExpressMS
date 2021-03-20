package com.tcwong.pengms.service;

import com.tcwong.pengms.base.WebPageResponse;
import com.tcwong.pengms.dto.team.TruckTeamAddRequest;
import com.tcwong.pengms.dto.team.TruckTeamDeleteRequest;
import com.tcwong.pengms.dto.team.TruckTeamEditRequest;
import com.tcwong.pengms.dto.team.TruckTeamPageRequest;

public interface TruckTeamService {
    /**
     * Description 添加车队
     *
     * @param request 车队请求
     * @return Integer 操作成功数量
     * @author tcwong
     * @date 2021/3/20
     */
    Integer addTruckTeam(TruckTeamAddRequest request);

    /**
     * Description 车队删除
     *
     * @param request 车队删除请求
     * @return Integer 操作成功数量
     * @author tcwong
     * @date 2021/3/20
     */
    Integer deleteTruckTeam(TruckTeamDeleteRequest request);

    /**
     * Description 车队编辑
     *
     * @param request 车队编辑参数
     * @return Integer 操作成功数量
     * @author tcwong
     * @date 2021/3/20
     */
    Integer editTruckTeam(TruckTeamEditRequest request);

    /**
     * Description 车队分页查询
     *
     * @param request 分页查询
     * @return WebPageResponse 车队分页结果集
     * @author tcwong
     * @date 2021/3/20
     */
    WebPageResponse pageTruckTeam(TruckTeamPageRequest request);
}
