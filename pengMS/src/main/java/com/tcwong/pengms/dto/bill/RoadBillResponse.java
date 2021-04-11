package com.tcwong.pengms.dto.bill;

import com.tcwong.pengms.dto.address.AddressResponse;
import com.tcwong.pengms.dto.cost.CostResponse;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoadBillResponse {
    private Long id;

    private Long billNo;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private Integer state;

    private String stateName;

    private String remark;

    private String updateBy;

    private String createBy;

    private LocalDateTime updateTime;

    private LocalDateTime createTime;

    private CostResponse cost;

    private AddressResponse deliverAddress;

    private AddressResponse receiverAddress;
}
