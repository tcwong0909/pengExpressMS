package com.tcwong.pengms.dao;

import com.tcwong.pengms.model.Truck;
import com.tcwong.pengms.model.example.TruckExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TruckMapper {
    int countByExample(TruckExample example);

    int deleteByExample(TruckExample example);

    int deleteByPrimaryKey(Integer truckid);

    int insert(Truck record);

    int insertSelective(Truck record);

    List<Truck> selectByExample(TruckExample example);

    Truck selectByPrimaryKey(Integer truckid);

    int updateByExampleSelective(
            @Param("record") Truck record, @Param("example") TruckExample example);

    int updateByExample(@Param("record") Truck record, @Param("example") TruckExample example);

    int updateByPrimaryKeySelective(Truck record);

    int updateByPrimaryKey(Truck record);

    List<Truck> getAllTrucksByPage(@Param("page") Integer page, @Param("size") Integer size);

    Long getTotal();

    int deleteByIds(@Param("ids") String[] ids);

    Long getSearchTotal(
            @Param("number") String number,
            @Param("type") String type,
            @Param("fkTeamid") Integer fkTeamid);

    List<Truck> getALLSearch(
            @Param("page") Integer page,
            @Param("size") Integer size,
            @Param("number") String number,
            @Param("type") String type,
            @Param("fkTeamid") Integer fkTeamid);

    int updateByIds(@Param("ids") String[] toString);
}
