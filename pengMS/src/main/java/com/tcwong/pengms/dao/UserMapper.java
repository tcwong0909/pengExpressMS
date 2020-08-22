package com.tcwong.pengms.dao;


import com.tcwong.pengms.model.User;
import com.tcwong.pengms.model.example.UserExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    int countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Integer userid);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Integer userid);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    int deleteByIds(@Param("ids") String[] ids);

    Long getTotal(@Param("username") String username, @Param("account") String account, @Param("sex") Integer sex, @Param("fkRoleid") Integer fkRoleid);

    List<?> getAllByPage(@Param("page") Integer page, @Param("size") Integer size, @Param("username") String username, @Param("account") String account, @Param("sex") Integer sex, @Param("fkRoleid") Integer fkRoleid);;
}