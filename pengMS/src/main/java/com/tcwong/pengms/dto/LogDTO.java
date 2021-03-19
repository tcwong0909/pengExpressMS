package com.tcwong.pengms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description
 *
 * @author tcwong
 * @date 2020/8/24 Since 1.8
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogDTO {

    private String operationType;

    private String description;

    private String params;

    private String ip;

    private Byte exceptionFlag;

    private String exceptionError;

    private String userAccount;

    private String username;
}
