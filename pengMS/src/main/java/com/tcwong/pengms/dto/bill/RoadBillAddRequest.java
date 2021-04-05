package com.tcwong.pengms.dto.bill;

import com.tcwong.pengms.dto.address.AddressAddRequest;
import com.tcwong.pengms.dto.cost.CostAddRequest;
import com.tcwong.pengms.dto.goods.GoodsAddRequest;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description 运单添加请求
 *
 * @author tcwong
 * @date 2021/3/22 Since 1.8
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoadBillAddRequest {

    private String remark;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private AddressAddRequest deliverAddressAdd;

    private AddressAddRequest receiverAddressAdd;

    private CostAddRequest costAdd;

    private GoodsAddRequest goodsAdd;
}
