package com.liaoin.dataclean;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableSwagger2Doc
@SpringBootApplication
public class DatacleanApplication {

    public static void main(String[] args) {
        SpringApplication.run(DatacleanApplication.class, args);
    }

}

