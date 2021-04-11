package com.tcwong.pengms.dto.cost;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CostResponse {

    private BigDecimal insurance;

    private BigDecimal transport;

    private BigDecimal extra;

    private BigDecimal total;
}
