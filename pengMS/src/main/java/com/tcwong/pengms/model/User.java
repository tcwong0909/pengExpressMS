package com.tcwong.pengms.model;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer id;

    private String username;

    private String userAccount;

    private String password;

    private Byte sex;

    private Integer roleId;

    private Date updateTime;

    private Date createTime;

    private Byte cancelFlag;
}
