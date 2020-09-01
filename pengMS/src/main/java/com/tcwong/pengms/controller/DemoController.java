package com.tcwong.pengms.controller;

import com.tcwong.pengms.base.BaseException;
import com.tcwong.pengms.base.LogFilter;
import com.tcwong.pengms.constant.LogOperationType;
import com.tcwong.pengms.utils.LocaleMessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

/**
 * Description
 *
 * @author tcwong
 * @date 2020/8/22
 * Since 1.8
 */
@RestController
public class DemoController {

    @Autowired
    private MessageSource messageSource;

    @GetMapping("/test1")
    public Object test1() {
        return LocaleMessageUtil.getMessage("0000");
    }

    @GetMapping("/test2")
    @LogFilter(logOperationType = LogOperationType.UPDATE,description = "测试test")
    public String test2(String param) {
        return "response>>>>>"+param;
    }

    public static void main(String[] args) {
        User susu = new User("susu");
        System.out.println(susu.getAge());


    }

    static class User {
        private String name;
        private Integer age;

        public User(String name) {
            this.name = name;
            this.age = null;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }
    }
}
