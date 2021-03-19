package com.tcwong.pengms.dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description
 *
 * @author tcwong
 * @date 2020/9/2 Since 1.8
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVO {

    private Integer id;

    private String username;

    private String userAccount;

    private Byte sex;

    private Date updateTime;

    private Date createTime;

    private String roleName;
}
