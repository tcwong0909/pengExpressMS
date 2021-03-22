package com.tcwong.pengms.dto.cost;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description 成本添加请求
 *
 * @author tcwong
 * @date 2021/3/22 Since 1.8
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CostAddRequest {

    private BigDecimal insurance;

    private BigDecimal transport;

    private BigDecimal extra;

    private String remark;
}
