package ru.netology.cloudstorage.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import ru.netology.cloudstorage.dto.request.AuthenticationRQ;
import ru.netology.cloudstorage.dto.response.AppError;
import ru.netology.cloudstorage.dto.response.AuthenticationRS;
import ru.netology.cloudstorage.model.TokenUser;
import ru.netology.cloudstorage.repository.TokenUserRepository;
import ru.netology.cloudstorage.service.AuthenticationService;
import ru.netology.cloudstorage.service.UserService;
import ru.netology.cloudstorage.utils.JwtTokenUtils;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final TokenUserRepository tokenUserRepository;
    private final JwtTokenUtils jwtTokenUtils;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @NotNull AuthenticationRQ authenticationRQ) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRQ.getLogin(), authenticationRQ.getPassword()));
                    } catch (BadCredentialsException e) {
            return new ResponseEntity<>(new AppError(HttpStatus.UNAUTHORIZED.value(), "Некорректный логин или пароль"),
                    HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails = userService.loadUserByUsername(authenticationRQ.getLogin());
        String authToken = jwtTokenUtils.generateToken(userDetails);
        tokenUserRepository.save(new TokenUser(userDetails.getUsername(), authToken));
        return ResponseEntity.ok(new AuthenticationRS(authToken));

    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader("auth-token") String authToken) {
        authenticationService.logout(authToken);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
