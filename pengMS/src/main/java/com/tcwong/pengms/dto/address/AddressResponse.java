package com.tcwong.pengms.dto.address;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressResponse {

    private String companyName;

    private String address;

    private String person;

    private String phone;

    private Integer type;
}
