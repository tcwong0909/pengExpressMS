package com.tcwong.pengms.dao;

import com.tcwong.pengms.model.DriverTruckMapping;
import com.tcwong.pengms.model.example.DriverTruckMappingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DriverTruckMappingMapper {
    int countByExample(DriverTruckMappingExample example);

    int deleteByExample(DriverTruckMappingExample example);

    int deleteByPrimaryKey(Long id);

    int insert(DriverTruckMapping record);

    int insertSelective(DriverTruckMapping record);

    List<DriverTruckMapping> selectByExample(DriverTruckMappingExample example);

    DriverTruckMapping selectByPrimaryKey(Long id);

    int updateByExampleSelective(
            @Param("record") DriverTruckMapping record,
            @Param("example") DriverTruckMappingExample example);

    int updateByExample(
            @Param("record") DriverTruckMapping record,
            @Param("example") DriverTruckMappingExample example);

    int updateByPrimaryKeySelective(DriverTruckMapping record);

    int updateByPrimaryKey(DriverTruckMapping record);
}
