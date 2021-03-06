package com.tcwong.pengms.dao;

import com.tcwong.pengms.model.Scheduling;
import com.tcwong.pengms.model.example.SchedulingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SchedulingMapper {
    int countByExample(SchedulingExample example);

    int deleteByExample(SchedulingExample example);

    int deleteByPrimaryKey(Integer schedulingid);

    int insert(Scheduling record);

    int insertSelective(Scheduling record);

    List<Scheduling> selectByExample(SchedulingExample example);

    Scheduling selectByPrimaryKey(Integer schedulingid);

    int updateByExampleSelective(
            @Param("record") Scheduling record, @Param("example") SchedulingExample example);

    int updateByExample(
            @Param("record") Scheduling record, @Param("example") SchedulingExample example);

    int updateByPrimaryKeySelective(Scheduling record);

    int updateByPrimaryKey(Scheduling record);

    Long getTotal(
            @Param("schedulingid") Integer schedulingid,
            @Param("fkCarriersid") Integer fkCarriersid,
            @Param("sendcompany") String sendcompany,
            @Param("receivecompany") String receivecompany);

    List<Scheduling> getAllByPage(
            @Param("page") Integer page,
            @Param("size") Integer size,
            @Param("schedulingid") Integer schedulingid,
            @Param("fkCarriersid") Integer fkCarriersid,
            @Param("sendcompany") String sendcompany,
            @Param("receivecompany") String receivecompany);

    int deleteByIds(@Param("ids") String[] schedulingIds);
}
