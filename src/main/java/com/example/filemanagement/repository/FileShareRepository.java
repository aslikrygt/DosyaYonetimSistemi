package com.example.filemanagement.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.filemanagement.entity.FileShare;

public interface FileShareRepository extends JpaRepository<FileShare, Long> {
}