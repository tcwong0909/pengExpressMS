package com.tcwong.pengms.dto.bill;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description 运单删除请求
 *
 * @author tcwong
 * @date 2021/3/22 Since 1.8
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoadBillDeleteRequest {

    private List<Long> idList;
}
