package com.example.randomuser.repo;

import com.example.randomuser.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Long> {
}
