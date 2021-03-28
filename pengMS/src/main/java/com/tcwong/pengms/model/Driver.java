package com.tcwong.pengms.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description 司机实体类
 *
 * @author tcwong
 * @date 2021/3/25 Since 1.8
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Driver {
    private Long id;

    private String name;

    private Integer sex;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime birthTime;

    private String phoneNo;

    private String identityNo;

    private Long truckTeamId;

    private Integer state;

    private String remark;

    private LocalDateTime checkTime;

    private Integer isDelete;

    private String updateBy;

    private String createBy;

    private LocalDateTime updateTime;

    private LocalDateTime createTime;
}
