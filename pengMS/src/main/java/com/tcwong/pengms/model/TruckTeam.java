package com.tcwong.pengms.model;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description 车队实体类
 *
 * @author tcwong
 * @date 2021/3/20 Since 1.8
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TruckTeam {
    private Long id;

    private String name;

    private String leader;

    private String remark;

    private Date checkTime;

    private Integer isDelete;

    private String updateBy;

    private String createBy;

    private Date updateTime;

    private Date createTime;
}
