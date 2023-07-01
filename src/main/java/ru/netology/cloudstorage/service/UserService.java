package ru.netology.cloudstorage.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.netology.cloudstorage.model.User;
import ru.netology.cloudstorage.repository.UserRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;
    public User loadUserByUsernameAndPassword(String username, String password) {
        return userRepository.findByLoginAndPassword(username,password).orElseThrow();
    }
}
