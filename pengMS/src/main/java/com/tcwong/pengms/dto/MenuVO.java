package com.tcwong.pengms.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description
 *
 * @author tcwong
 * @date 2020/10/14 Since 1.8
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuVO {
    private String name;
    private String path;
    private String icon;
    private List<MenuChild> children;

    @Data
    public static class MenuChild {
        private String name;
        private String path;
    }
}
