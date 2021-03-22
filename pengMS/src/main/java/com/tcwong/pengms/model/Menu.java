package com.tcwong.pengms.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Menu {
    private Integer id;

    private String path;

    private String name;

    private String icon;

    private Byte keepAlive;

    private Integer parentId;
}
