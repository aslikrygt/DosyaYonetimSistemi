package com.example.filemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.filemanagement.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);
}
