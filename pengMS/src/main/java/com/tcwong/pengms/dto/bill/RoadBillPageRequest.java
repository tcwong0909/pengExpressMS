package com.tcwong.pengms.dto.bill;

import com.tcwong.pengms.base.BaseRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * Description 运单分页请求
 *
 * @author tcwong
 * @date 2021/3/22 Since 1.8
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class RoadBillPageRequest extends BaseRequest {

    private Integer state;
}
