package com.monitoring.websitemonitoring.service;

import com.monitoring.websitemonitoring.repo.WebsiteRepo;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class MonitoringService {
    private final WebsiteRepo websiteRepo;

    public MonitoringService(WebsiteRepo websiteRepo) {
        this.websiteRepo = websiteRepo;
    }

    @Scheduled(fixedRate = 60000)
    public void checkWebsite(){
        //logic for pinging websites
    }
}
