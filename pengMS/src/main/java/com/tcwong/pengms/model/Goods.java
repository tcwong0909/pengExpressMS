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
public class Goods {
    private Long id;

    private Long billNo;

    private String name;

    private Integer amount;

    private Float weight;

    private Float volume;

    private Integer isDelete;

    private String updateBy;

    private String createBy;

    private LocalDateTime updateTime;

    private LocalDateTime createTime;
}
