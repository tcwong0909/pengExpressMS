package com.tcwong.pengms.dto.team;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description 车队编辑请求
 *
 * @author tcwong
 * @date 2021/3/20 Since 1.8
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TruckTeamEditRequest {

    @NotNull(message = "车队Id不能为空")
    private Long id;

    private String name;

    private String leader;

    private String remark;
}
