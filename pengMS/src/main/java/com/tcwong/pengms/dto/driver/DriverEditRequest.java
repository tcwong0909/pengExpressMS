package com.tcwong.pengms.dto.driver;

import java.util.Date;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description 司机编辑请求
 *
 * @author tcwong
 * @date 2021/3/20 Since 1.8
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DriverEditRequest {

    @NotNull(message = "司机Id不能为空")
    private Long id;

    private String name;

    private Integer sex;

    private Date birthTime;

    private String phoneNo;

    private String identityNo;

    private Integer state;

    private String remark;
}
