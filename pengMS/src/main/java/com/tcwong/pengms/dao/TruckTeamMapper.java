package com.tcwong.pengms.dao;

import com.tcwong.pengms.model.TruckTeam;
import com.tcwong.pengms.model.example.TruckTeamExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TruckTeamMapper {
    long countByExample(TruckTeamExample example);

    int deleteByExample(TruckTeamExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TruckTeam record);

    int insertSelective(TruckTeam record);

    List<TruckTeam> selectByExample(TruckTeamExample example);

    TruckTeam selectByPrimaryKey(Long id);

    int updateByExampleSelective(
            @Param("record") TruckTeam record, @Param("example") TruckTeamExample example);

    int updateByExample(
            @Param("record") TruckTeam record, @Param("example") TruckTeamExample example);

    int updateByPrimaryKeySelective(TruckTeam record);

    int updateByPrimaryKey(TruckTeam record);
}
