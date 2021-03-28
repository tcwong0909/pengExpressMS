package com.tcwong.pengms.dao;

import com.tcwong.pengms.model.Truck;
import com.tcwong.pengms.model.example.TruckExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TruckMapper {
    long countByExample(TruckExample example);

    int deleteByExample(TruckExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Truck record);

    int insertSelective(Truck record);

    List<Truck> selectByExample(TruckExample example);

    Truck selectByPrimaryKey(Long id);

    int updateByExampleSelective(
            @Param("record") Truck record, @Param("example") TruckExample example);

    int updateByExample(@Param("record") Truck record, @Param("example") TruckExample example);

    int updateByPrimaryKeySelective(Truck record);

    int updateByPrimaryKey(Truck record);
}
