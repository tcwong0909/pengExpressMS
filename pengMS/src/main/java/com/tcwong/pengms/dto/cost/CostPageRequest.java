package com.tcwong.pengms.dto.cost;

import com.tcwong.pengms.base.BaseRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * Description 成本分页查询请求
 *
 * @author tcwong
 * @date 2021/3/22 Since 1.8
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CostPageRequest extends BaseRequest {}
