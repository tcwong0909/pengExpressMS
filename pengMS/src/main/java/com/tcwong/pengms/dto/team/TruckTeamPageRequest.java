package com.tcwong.pengms.dto.team;

import com.tcwong.pengms.base.BaseRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * Description 车队分页请求
 *
 * @author tcwong
 * @date 2021/3/20 Since 1.8
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class TruckTeamPageRequest extends BaseRequest {

    private String name;

    private String leader;
}
