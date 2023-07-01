package ru.netology.cloudstorage.model;

import com.sun.istack.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "username", unique = true)
    @NotBlank(message = "Имя пользователя не может быть пустым")
    @Size(min = 2, max = 30)
    String login;

    @Column(name = "password")
    @NotBlank(message = "пароль не может быть пустым")
    @Size(min = 3, max = 10)
    String password;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    Role role;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
