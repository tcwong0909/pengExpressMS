package com.tcwong.pengms.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cost {
    private Long id;

    private Long billNo;

    private BigDecimal insurance;

    private BigDecimal transport;

    private BigDecimal extra;

    private BigDecimal total;

    private String remark;

    private Integer isDelete;

    private String updateBy;

    private String createBy;

    private LocalDateTime updateTime;

    private LocalDateTime createTime;
}
