package com.monitoring.websitemonitoring.service;

import com.monitoring.websitemonitoring.DTO.WebsiteDTO;
import com.monitoring.websitemonitoring.entity.User;
import com.monitoring.websitemonitoring.entity.Website;
import com.monitoring.websitemonitoring.repo.WebsiteRepo;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class MonitoringService {
    private final WebsiteRepo websiteRepo;
    private final UserService userService;

    public MonitoringService(WebsiteRepo websiteRepo, UserService userService) {
        this.websiteRepo = websiteRepo;
        this.userService = userService;
    }

    @Transactional
    public WebsiteDTO createWebsite(Long userId, WebsiteDTO websiteDTO) {
        User user = userService.findUserById(userId);

        Website website = new Website();
        website.setUrl(websiteDTO.getUrl());
        website.setName(websiteDTO.getName());
        website.setUser(user);
        website.setLivingStatus(null);

        Website savedWebsite = websiteRepo.save(website);

        return convertToDTO(savedWebsite);
    }

    private WebsiteDTO convertToDTO(Website website) {
        WebsiteDTO websiteDTO = new WebsiteDTO();
        websiteDTO.setId(website.getId());
        websiteDTO.setName(website.getName());
        websiteDTO.setUrl(website.getUrl());
        websiteDTO.setLivingStatus(website.getLivingStatus());
        websiteDTO.setLastCheckTime(website.getLastCheckTime());

        return websiteDTO;
    }

    @Scheduled(fixedRate = 60000)
    @Transactional
    public void checkWebsite(){
        List<Website> websites = websiteRepo.findAll();

        for (Website website : websites) {
            boolean isAlive = pingUrl(website.getUrl());

            if (website.getLivingStatus() == null || website.getLivingStatus() != isAlive) {
                System.out.println("Status changed for: " + website.getUrl() + " to " + isAlive);
            }

            website.setLivingStatus(isAlive);
            website.setLastCheckTime(LocalDateTime.now());
        }
    }

    private boolean pingUrl(String urlAddress) {
        try {
            URL url = new URL(urlAddress);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.connect();
            int code = connection.getResponseCode();
            return (code >= 200 && code < 400);
        } catch (Exception e) {
            return false;
        }
    }
}
