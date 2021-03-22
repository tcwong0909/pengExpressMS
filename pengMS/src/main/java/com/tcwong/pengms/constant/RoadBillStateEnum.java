package com.tcwong.pengms.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Description
 *
 * @author tcwong
 * @date 2021/3/22 Since 1.8
 */
@Getter
@AllArgsConstructor
public enum RoadBillStateEnum {
    CREATED(10, "已创建");
    private Integer state;
    private String message;
}
