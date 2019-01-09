package com.starriddle.starter.springboot.admin.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import de.codecentric.boot.admin.server.config.EnableAdminServer;

/**
 * SpringBoot Admin Server
 *
 * @author CYL
 * @date 2019-01-09
 */
@SpringBootApplication
@EnableAdminServer
public class AdminServerApplication {

    public static void main(String[] args){
        SpringApplication.run(AdminServerApplication.class, args);
    }

}
