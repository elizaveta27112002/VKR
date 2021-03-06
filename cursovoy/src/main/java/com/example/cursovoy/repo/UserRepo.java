package com.example.cursovoy.repo;

import com.example.cursovoy.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);

}

