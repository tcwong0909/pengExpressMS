package com.tcwong.pengms.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description
 *
 * @author tcwong
 * @date 2020/8/22
 * Since 1.8
 */
@RestController
public class DemoController {
    public static void main(String[] args) {
        System.out.println(StringUtils.isBlank(" "));
    }
}
