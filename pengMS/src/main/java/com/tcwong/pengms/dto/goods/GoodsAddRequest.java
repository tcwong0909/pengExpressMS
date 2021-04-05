package com.tcwong.pengms.dto.goods;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GoodsAddRequest {

    private String name;

    private Integer amount;

    private Float weight;

    private Float volume;
}
