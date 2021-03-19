package com.tcwong.pengms.service.impl;

import com.tcwong.pengms.base.WebPageResponse;
import com.tcwong.pengms.dao.TruckteamMapper;
import com.tcwong.pengms.model.Truckteam;
import com.tcwong.pengms.service.TruckTeamService;
import java.util.Date;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class TruckTeamServiceImpl implements TruckTeamService {
    @Resource private TruckteamMapper truckteamMapper;

    @Override
    public int addTruckTeam(Truckteam truckteam) {
        truckteam.setCheckintime(new Date());
        truckteam.setAltertime(new Date());
        return truckteamMapper.insert(truckteam);
    }

    @Override
    public int deleteByIds(String ids) {
        String[] split = ids.split(",");
        return truckteamMapper.deleteByIds(split);
    }

    @Override
    public int editTruckTeam(Truckteam truckteam) {
        truckteam.setAltertime(new Date());

        return truckteamMapper.updateByPrimaryKeySelective(truckteam);
    }

    @Override
    public WebPageResponse getAllByPage(
            Integer page, Integer size, String teamname, String leader) {
        if (page != null && size != null) {
            page = (page - 1) * size;
        }

        return new WebPageResponse(
                truckteamMapper.getTotal(teamname, leader),
                truckteamMapper.getALlTruckTeamByPage(page, size, teamname, leader));
    }
}
