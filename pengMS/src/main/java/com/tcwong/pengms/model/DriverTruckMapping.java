package com.tcwong.pengms.model;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description 司机-车辆映射
 *
 * @author tcwong
 * @date 2021/3/22 Since 1.8
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DriverTruckMapping {
    private Long id;

    private Long driverId;

    private Long truckId;

    private Integer isDelete;

    private String updateBy;

    private String createBy;

    private Date updateTime;

    private Date createTime;
}
