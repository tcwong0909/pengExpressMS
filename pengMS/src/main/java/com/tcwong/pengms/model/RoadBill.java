package com.tcwong.pengms.model;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description 运单实体类
 *
 * @author tcwong
 * @date 2021/3/22 Since 1.8
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoadBill {
    private Long id;

    private Long billNo;

    private Date startTime;

    private Date endTime;

    private Integer state;

    private String remark;

    private Integer isDelete;

    private String updateBy;

    private String createBy;

    private Date updateTime;

    private Date createTime;
}
