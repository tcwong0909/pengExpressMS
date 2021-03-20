package com.tcwong.pengms.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Description 异常类型
 *
 * @author tcwong
 * @date 2020/8/31 Since 1.8
 */
@Getter
@AllArgsConstructor
public enum DeletedEnum {
    UN_DELETED(0, "未删除"),
    DONE_DELETED(1, "已删除");

    private Integer state;
    private String message;
}
