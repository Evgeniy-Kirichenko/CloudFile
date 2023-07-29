package ru.netology.cloudstorage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.netology.cloudstorage.model.Role;
import ru.netology.cloudstorage.model.User;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
