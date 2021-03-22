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
public class OperateLog {
    private Integer id;

    private String operationType;

    private String description;

    private String userAccount;

    private String ip;

    private String username;

    private String params;

    private Date creatTime;

    private Byte exceptionFlag;

    private String exceptionError;
}
