package com.monitoring.websitemonitoring.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class UserDTO {
    private Long id;

    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String email;
    @NotBlank
    private String telegramId;

}
