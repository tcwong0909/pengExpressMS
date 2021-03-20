package com.tcwong.pengms.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * Description 请求分页
 *
 * @author tcwong
 * @date 2020/8/22 Since 1.8
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class BaseRequest {

    private int pageNum = 0;

    private int pageSize = 10;
}
