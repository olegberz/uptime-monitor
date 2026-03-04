package com.monitoring.websitemonitoring.controller;

import com.monitoring.websitemonitoring.DTO.WebsiteDTO;
import com.monitoring.websitemonitoring.entity.Website;
import com.monitoring.websitemonitoring.service.MonitoringService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/websites")
public class WebsiteRestController {
    private final MonitoringService monitoringService;

    public WebsiteRestController(MonitoringService monitoringService) {
        this.monitoringService = monitoringService;
    }

    @PostMapping("/user/{userId}")
    public ResponseEntity<WebsiteDTO> addWebsite(@PathVariable Long userId,
                                                 @Valid @RequestBody WebsiteDTO websiteDTO) {
        WebsiteDTO created = monitoringService.createWebsite(userId, websiteDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }
}
