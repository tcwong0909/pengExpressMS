package com.tcwong.pengms.dto.bill;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description 运单添加请求
 *
 * @author tcwong
 * @date 2021/3/22 Since 1.8
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoadBillAddRequest {

    private String remark;
}
