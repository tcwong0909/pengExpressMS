package com.tcwong.pengms.model;

import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description 成本实体类
 *
 * @author tcwong
 * @date 2021/3/22 Since 1.8
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cost {
    private Long id;

    private BigDecimal insurance;

    private BigDecimal transport;

    private BigDecimal extra;

    private BigDecimal total;

    private String remark;

    private Integer isDelete;

    private String updateBy;

    private String createBy;

    private Date updateTime;

    private Date createTime;
}
