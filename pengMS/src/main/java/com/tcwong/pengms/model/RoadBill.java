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
public class RoadBill {
    private Long id;

    private Long billNo;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private Integer state;

    private String remark;

    private Integer isDelete;

    private String updateBy;

    private String createBy;

    private LocalDateTime updateTime;

    private LocalDateTime createTime;
}
