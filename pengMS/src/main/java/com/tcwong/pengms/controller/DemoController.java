package com.tcwong.pengms.controller;

import com.tcwong.pengms.base.LogFilter;
import com.tcwong.pengms.constant.LogOperationType;
import com.tcwong.pengms.utils.LocaleMessageUtil;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description
 *
 * @author tcwong
 * @date 2020/8/22 Since 1.8
 */
@RestController
public class DemoController {

    @Autowired private RedisTemplate redisTemplate;

    @GetMapping("/test1")
    public Object test1() {
        return LocaleMessageUtil.getMessage("0000");
    }

    @GetMapping("/test2")
    @LogFilter(logOperationType = LogOperationType.UPDATE, description = "测试test")
    public String test2(String param) {
        return "response>>>>>" + param;
    }

    public static void main(String[] args) {
        User susu = new User("susu");
        System.out.println(susu.getAge());
        List<String> linkedList = new LinkedList<>();
        LinkedHashMap<String, String> stringStringLinkedHashMap = new LinkedHashMap<>();
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
