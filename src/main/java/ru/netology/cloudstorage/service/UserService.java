package ru.netology.cloudstorage.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.netology.cloudstorage.exception.UnauthorizedException;
import ru.netology.cloudstorage.model.User;
import ru.netology.cloudstorage.repository.UserRepository;

import javax.naming.AuthenticationException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;

    public User loadUserByUsernameAndPassword(String username, String password) {
        User user = userRepository.findByLoginAndPassword(username, password);
        if (user == null) throw new UnauthorizedException("Вы не авторизованны");
        return user;

    }
}
