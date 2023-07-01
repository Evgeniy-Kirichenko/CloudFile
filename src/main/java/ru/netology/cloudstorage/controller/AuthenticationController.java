package ru.netology.cloudstorage.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.netology.cloudstorage.dto.request.AuthenticationRQ;
import ru.netology.cloudstorage.dto.response.AuthenticationRS;
import ru.netology.cloudstorage.service.AuthenticationService;

@RestController
@AllArgsConstructor
public class AuthenticationController {
    private AuthenticationService authenticationService;
    @PostMapping("/login")
    public AuthenticationRS login(@RequestBody AuthenticationRQ authenticationRQ){
        return authenticationService.login(authenticationRQ);
    }
}