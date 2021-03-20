package com.tcwong.pengms.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tcwong.pengms.base.WebPageResponse;
import com.tcwong.pengms.constant.DeletedEnum;
import com.tcwong.pengms.dao.TruckTeamMapper;
import com.tcwong.pengms.dto.team.TruckTeamAddRequest;
import com.tcwong.pengms.dto.team.TruckTeamDeleteRequest;
import com.tcwong.pengms.dto.team.TruckTeamEditRequest;
import com.tcwong.pengms.dto.team.TruckTeamPageRequest;
import com.tcwong.pengms.model.TruckTeam;
import com.tcwong.pengms.model.User;
import com.tcwong.pengms.model.example.TruckTeamExample;
import com.tcwong.pengms.service.TruckTeamService;
import com.tcwong.pengms.utils.PengContext;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * Description 车队服务
 *
 * @author tcwong
 * @date 2021/3/20 Since 1.8
 */
@Service
public class TruckTeamServiceImpl implements TruckTeamService {
    @Resource private TruckTeamMapper truckteamMapper;

    /**
     * Description 添加车队
     *
     * @param request 车队请求
     * @return Integer 操作成功数量
     * @author tcwong
     * @date 2021/3/20
     */
    @Override
    public Integer addTruckTeam(TruckTeamAddRequest request) {
        User user = PengContext.getUser();
        Date nowDate = new Date();
        TruckTeam truckTeam =
                TruckTeam.builder()
                        .name(request.getName())
                        .leader(request.getLeader())
                        .remark(request.getRemark())
                        .checkTime(nowDate)
                        .isDelete(DeletedEnum.UN_DELETED.getState())
                        .updateBy(user.getUsername())
                        .createBy(user.getUsername())
                        .updateTime(nowDate)
                        .createTime(nowDate)
                        .build();
        return truckteamMapper.insert(truckTeam);
    }

    /**
     * Description 车队删除
     *
     * @param request 车队删除请求
     * @return Integer 操作成功数量
     * @author tcwong
     * @date 2021/3/20
     */
    @Override
    public Integer deleteTruckTeam(TruckTeamDeleteRequest request) {
        Date nowDate = new Date();
        User user = PengContext.getUser();
        TruckTeamExample truckTeamExample = new TruckTeamExample();
        truckTeamExample
                .createCriteria()
                .andIdIn(request.getIdList())
                .andIsDeleteEqualTo(DeletedEnum.UN_DELETED.getState());
        TruckTeam truckTeam =
                TruckTeam.builder()
                        .isDelete(DeletedEnum.DONE_DELETED.getState())
                        .updateTime(nowDate)
                        .updateBy(user.getUsername())
                        .build();
        return truckteamMapper.updateByExample(truckTeam, truckTeamExample);
    }

    /**
     * Description 车队编辑
     *
     * @param request 车队编辑参数
     * @return Integer 操作成功数量
     * @author tcwong
     * @date 2021/3/20
     */
    @Override
    public Integer editTruckTeam(TruckTeamEditRequest request) {
        User user = PengContext.getUser();
        Date nowDate = new Date();
        TruckTeam truckTeam =
                TruckTeam.builder()
                        .id(request.getId())
                        .name(request.getName())
                        .leader(request.getLeader())
                        .remark(request.getRemark())
                        .updateBy(user.getUsername())
                        .updateTime(nowDate)
                        .build();
        return truckteamMapper.updateByPrimaryKeySelective(truckTeam);
    }

    /**
     * Description 车队分页查询
     *
     * @param request 分页查询
     * @return WebPageResponse 车队分页结果集
     * @author tcwong
     * @date 2021/3/20
     */
    @Override
    public WebPageResponse pageTruckTeam(TruckTeamPageRequest request) {
        PageHelper.startPage(request.getPageNum(), request.getPageSize());
        TruckTeamExample truckTeamExample = new TruckTeamExample();
        truckTeamExample.setOrderByClause("id DESC");
        TruckTeamExample.Criteria criteria = truckTeamExample.createCriteria();
        if (ObjectUtil.isNotEmpty(request.getName())) {
            criteria.andNameEqualTo(request.getName());
        }
        if (ObjectUtil.isNotNull(request.getLeader())) {
            criteria.andLeaderEqualTo(request.getLeader());
        }
        List<TruckTeam> truckTeamList = truckteamMapper.selectByExample(truckTeamExample);
        PageInfo<TruckTeam> truckTeamPageInfo = new PageInfo<>(truckTeamList);
        return new WebPageResponse(truckTeamPageInfo.getTotal(), truckTeamPageInfo.getList());
    }
}
