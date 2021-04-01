package com.tcwong.pengms.model;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private Long id;

    private Long billNo;

    private String companyName;

    private String address;

    private String person;

    private String phone;

    private Integer type;

    private Integer isDelete;

    private String updateBy;

    private String createBy;

    private LocalDateTime updateTime;

    private LocalDateTime createTime;
}
