package com.tcwong.pengms.dto.driver;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description 司机删除请求
 *
 * @author tcwong
 * @date 2021/3/20 Since 1.8
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DriverDeleteRequest {
    private List<Long> idList;
}
