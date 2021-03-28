package com.tcwong.pengms.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description 车辆实体
 *
 * @author tcwong
 * @date 2021/3/20 Since 1.8
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Truck {
    private Long id;

    private String plateNo;

    private Integer type;

    private Float deadWeight;

    private String weightUnit;

    private Long driverId;

    private Integer state;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime buyTime;

    private String remark;

    private Integer isDelete;

    private LocalDateTime checkTime;

    private String updateBy;

    private String createBy;

    private LocalDateTime updateTime;

    private LocalDateTime createTime;
}
