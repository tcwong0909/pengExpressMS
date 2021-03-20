package com.tcwong.pengms.dto.driver;

import com.tcwong.pengms.base.BaseRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * Description 司机分页查询请求
 *
 * @author tcwong
 * @date 2021/3/20 Since 1.8
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class DriverPageRequest extends BaseRequest {

    private String name;

    private Integer state;
}
