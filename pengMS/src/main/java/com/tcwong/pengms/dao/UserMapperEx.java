package com.tcwong.pengms.dao;

import com.tcwong.pengms.dto.UserDO;
import com.tcwong.pengms.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Description 用户管理 扩展dao
 *
 * @author tcwong
 * @date 2020/9/2
 * Since 1.8
 */
@Repository
public interface UserMapperEx {
    /**
     * Description 查询用户列表
     *
     * @param user 用户
     * @return
     * @author tcwong
     * @date 2020/9/2
     */
    List<UserDO> listUsers(User user);
}
