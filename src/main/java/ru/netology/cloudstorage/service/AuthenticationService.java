package ru.netology.cloudstorage.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.netology.cloudstorage.dto.request.AuthenticationRQ;
import ru.netology.cloudstorage.dto.response.AuthenticationRS;
import ru.netology.cloudstorage.model.TokenUser;
import ru.netology.cloudstorage.model.User;
import ru.netology.cloudstorage.repository.TokenUserRepository;


@Service
@AllArgsConstructor
public class AuthenticationService {
    private UserService userService;
    private TokenUserRepository tokenUserRepository;

    //проверяем логин и пароль в UserRepository. Если есть, то формируем токен и записываем в TokenUserRepository
    public AuthenticationRS login(AuthenticationRQ authenticationRQ) {
        final String userName = authenticationRQ.getLogin();
        final String userPassword = authenticationRQ.getPassword();
        User user = userService.loadUserByUsernameAndPassword(userName, userPassword);
        String token = "2222222";
        tokenUserRepository.save(new TokenUser(1L, userName, token));
        return new AuthenticationRS(token);
    }

    public void logout(String authToken) {
        tokenUserRepository.deleteByAuthToken(authToken);
    }

}

