package com.tcwong.pengms.dto;

import com.tcwong.pengms.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Description
 *
 * @author tcwong
 * @date 2020/9/2
 * Since 1.8
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDO {

    private Integer id;

    private String username;

    private String userAccount;

    private String password;

    private Integer roleId;

    private Byte sex;

    private Date updateTime;

    private Date createTime;

    private Byte cancelFlag;

    private Role role;

}
