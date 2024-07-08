package org.autech;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
@Slf4j
@EnableAutoConfiguration
public class OrderManagementSystemStarter {
    public static void main(String[] args) {
        log.info("Starting OrderManagementSystem with args {}", Arrays.toString(args));
        SpringApplication.run(OrderManagementSystemStarter.class);
        log.info("Started OMS..");
    }
}