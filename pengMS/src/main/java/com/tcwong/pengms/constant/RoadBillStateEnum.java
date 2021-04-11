package com.tcwong.pengms.constant;

import cn.hutool.core.util.ObjectUtil;
import java.util.Arrays;
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
    CREATED(10, "已创建"),
    ;
    private Integer state;
    private String message;

    public static String getMessage(Integer stateParam) {
        RoadBillStateEnum enumItem;
        if (ObjectUtil.isNotNull(
                enumItem =
                        Arrays.asList(values())
                                .stream()
                                .filter(s -> s.state.equals(stateParam))
                                .findFirst()
                                .get())) {
            return enumItem.getMessage();
        }
        return null;
    }
}
