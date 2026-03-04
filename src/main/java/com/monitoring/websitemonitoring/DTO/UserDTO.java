package com.monitoring.websitemonitoring.DTO;

import jakarta.validation.constraints.NotBlank;

public class UserDTO {
    private Long id;

    @NotBlank
    private String name;
    @NotBlank
    private String email;
    @NotBlank
    private String telegramId;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelegramId() {
        return telegramId;
    }

    public void setTelegramId(String telegramId) {
        this.telegramId = telegramId;
    }
}
