package com.monitoring.websitemonitoring.controller;

import com.monitoring.websitemonitoring.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/website")
public class UserRestController {
    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

}
