package com.monitoring.websitemonitoring.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;

import java.time.LocalDateTime;

@Entity
public class Website {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;
    private String url;
    private Boolean livingStatus;
    private LocalDateTime lastCheckTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Website() {
    }

    public Website(Long id, String name, String url, Boolean livingStatus, LocalDateTime lastCheckTime, User user) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.livingStatus = livingStatus;
        this.lastCheckTime = lastCheckTime;
        this.user = user;
    }

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
