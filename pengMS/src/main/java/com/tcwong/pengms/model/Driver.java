package com.tcwong.pengms.model;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description 司机实体类
 *
 * @author tcwong
 * @date 2021/3/20 Since 1.8
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Driver {
    private Long id;

    private String name;

    private Integer sex;

    private Date birthTime;

    private String phoneNo;

    private String identityNo;

    private Long truckTeamId;

    private Integer state;

    private String remark;

    private Date checkTime;

    private Integer isDelete;

    private String updateBy;

    private String createBy;

    private Date updateTime;

    private Date createTime;
}
