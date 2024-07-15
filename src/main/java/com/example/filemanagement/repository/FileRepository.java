package com.example.filemanagement.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.filemanagement.entity.File;

public interface FileRepository extends JpaRepository<File, Long> {
}