package com.tcwong.pengms.controller;

import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/test1")
    public void test1() {
        System.out.println("test");
    }

    @GetMapping("/test2")
    public String test2(String param) {
        return "response>>>>>"+param;
    }
}
