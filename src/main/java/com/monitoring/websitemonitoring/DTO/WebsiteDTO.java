package com.monitoring.websitemonitoring.DTO;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public class WebsiteDTO {
    private Long id;

    private String name;

    @NotBlank(message = "URL is required")
    private String url;

    private Boolean livingStatus;
    private LocalDateTime lastCheckTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getLivingStatus() {
        return livingStatus;
    }

    public void setLivingStatus(Boolean livingStatus) {
        this.livingStatus = livingStatus;
    }

    public LocalDateTime getLastCheckTime() {
        return lastCheckTime;
    }

    public void setLastCheckTime(LocalDateTime lastCheckTime) {
        this.lastCheckTime = lastCheckTime;
    }
}
