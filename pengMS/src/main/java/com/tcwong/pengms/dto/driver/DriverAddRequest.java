package com.tcwong.pengms.dto.driver;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description 司机添加请求
 *
 * @author tcwong
 * @date 2021/3/20 Since 1.8
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DriverAddRequest {

    private String name;

    private Integer sex;

    private LocalDateTime birthTime;

    private String phoneNo;

    private String identityNo;

    private Integer state;

    private String remark;
}
