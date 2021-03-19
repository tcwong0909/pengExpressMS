package com.tcwong.pengms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication
@EnableRedisHttpSession
@MapperScan(value = "com.tcwong.pengms.dao")
public class PengMsApplication {

    public static void main(String[] args) {
        SpringApplication.run(PengMsApplication.class, args);
    }
}
