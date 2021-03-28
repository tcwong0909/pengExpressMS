package com.tcwong.pengms.dto.truck;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description 车辆添加请求
 *
 * @author tcwong
 * @date 2021/3/20 Since 1.8
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TruckAddRequest {

    private String plateNo;

    private Integer type;

    private Float deadWeight;

    private String weightUnit;

    private Long driverId;

    private Integer state;

    private LocalDateTime buyTime;

    private String remark;
}
