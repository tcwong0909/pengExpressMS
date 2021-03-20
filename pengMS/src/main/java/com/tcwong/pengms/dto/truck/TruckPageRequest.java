package com.tcwong.pengms.dto.truck;

import com.tcwong.pengms.base.BaseRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * Description 车辆分页查询请求
 *
 * @author tcwong
 * @date 2021/3/20 Since 1.8
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class TruckPageRequest extends BaseRequest {

    private String plateNo;

    private Integer type;
}
