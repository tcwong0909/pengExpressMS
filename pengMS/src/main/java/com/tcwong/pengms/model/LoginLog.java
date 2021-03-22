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
public class LoginLog {
    private Integer id;

    private String username;

    private String account;

    private String loginIp;

    private Date loginTime;
}
