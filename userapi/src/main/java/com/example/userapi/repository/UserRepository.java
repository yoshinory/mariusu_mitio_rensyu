package com.example.userapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.userapi.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
