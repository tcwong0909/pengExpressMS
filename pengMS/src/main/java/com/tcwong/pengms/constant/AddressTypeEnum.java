package com.tcwong.pengms.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Description 地址类型
 *
 * @author tcwong
 * @date 2021/4/5 Since 1.8
 */
@Getter
@AllArgsConstructor
public enum AddressTypeEnum {
    DELIVER(1, ""),

    RECEIVER(2, "");

    private Integer type;
    private String msg;
}
