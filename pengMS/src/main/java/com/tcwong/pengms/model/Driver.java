package com.tcwong.pengms.model;

import com.alibaba.fastjson.annotation.JSONField;
import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Driver implements Serializable {
    private Integer driverid;

    private String name;

    private Integer sex;

    @JSONField(format = "yyyy-MM-dd")
    private Date birth;

    private String phone;

    private String idcard;

    private Integer fkTeamid;

    private Integer state;

    private String remark;

    @JSONField(format = "yyyy-MM-dd")
    private Date checkintime;

    private Integer isdelete;

    @JSONField(format = "yyyy-MM-dd")
    private Date altertime;

    private TruckTeam truckTeam;
}
