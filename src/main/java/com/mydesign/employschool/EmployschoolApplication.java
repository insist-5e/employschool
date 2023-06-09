package com.mydesign.employschool;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@MapperScan("com.mydesign.employschool.mapper")
@EnableSwagger2
public class EmployschoolApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmployschoolApplication.class, args);
    }

}
