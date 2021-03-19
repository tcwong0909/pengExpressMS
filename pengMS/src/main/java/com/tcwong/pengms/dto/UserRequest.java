package com.tcwong.pengms.dto;

import com.tcwong.pengms.base.BaseRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description
 *
 * @author tcwong
 * @date 2020/9/2 Since 1.8
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest extends BaseRequest {

    private String username;

    private String userAccount;

    private Byte sex;

    private Integer roleId;
}
