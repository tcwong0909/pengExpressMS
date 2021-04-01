package com.tcwong.pengms.dao;

import com.tcwong.pengms.model.RoadBill;
import com.tcwong.pengms.model.example.RoadBillExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoadBillMapper {
    long countByExample(RoadBillExample example);

    int deleteByExample(RoadBillExample example);

    int deleteByPrimaryKey(Long id);

    int insert(RoadBill record);

    int insertSelective(RoadBill record);

    List<RoadBill> selectByExample(RoadBillExample example);

    RoadBill selectByPrimaryKey(Long id);

    int updateByExampleSelective(
            @Param("record") RoadBill record, @Param("example") RoadBillExample example);

    int updateByExample(
            @Param("record") RoadBill record, @Param("example") RoadBillExample example);

    int updateByPrimaryKeySelective(RoadBill record);

    int updateByPrimaryKey(RoadBill record);
}
