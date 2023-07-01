package ru.netology.cloudstorage.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthenticationRQ {
    @NotBlank(message = "Логин не может быть пустым")
    String login;

    @NotBlank(message = "пароль не может быть пустым")
    String password;
}