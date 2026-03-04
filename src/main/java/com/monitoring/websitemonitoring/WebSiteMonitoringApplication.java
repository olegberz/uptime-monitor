package com.monitoring.websitemonitoring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class WebSiteMonitoringApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebSiteMonitoringApplication.class, args);
    }

}
