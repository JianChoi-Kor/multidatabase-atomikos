package com.example.multidatabase.second.repository;

import com.example.multidatabase.second.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
