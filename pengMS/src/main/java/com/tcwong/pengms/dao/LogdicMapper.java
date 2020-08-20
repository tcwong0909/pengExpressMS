package com.tcwong.pengms.dao;


import com.tcwong.pengms.model.Logdic;
import com.tcwong.pengms.model.LogdicExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LogdicMapper {
    int countByExample(LogdicExample example);

    int deleteByExample(LogdicExample example);

    int deleteByPrimaryKey(Integer typeid);

    int insert(Logdic record);

    int insertSelective(Logdic record);

    List<Logdic> selectByExample(LogdicExample example);

    Logdic selectByPrimaryKey(Integer typeid);

    int updateByExampleSelective(@Param("record") Logdic record, @Param("example") LogdicExample example);

    int updateByExample(@Param("record") Logdic record, @Param("example") LogdicExample example);

    int updateByPrimaryKeySelective(Logdic record);

    int updateByPrimaryKey(Logdic record);
}