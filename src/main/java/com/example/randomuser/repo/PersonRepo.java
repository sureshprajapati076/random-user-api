package com.example.randomuser.repo;

import com.example.randomuser.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepo extends JpaRepository<User,Long> {
}
