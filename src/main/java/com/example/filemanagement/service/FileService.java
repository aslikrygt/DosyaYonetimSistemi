package com.example.filemanagement.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.filemanagement.entity.File;
import com.example.filemanagement.repository.FileRepository;

@Service
public class FileService {

    @Autowired
    private FileRepository fileRepository;

    private final Path fileStorageLocation = Paths.get("file_storage").toAbsolutePath().normalize();

    public FileService() {
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new RuntimeException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    public File storeFile(org.springframework.web.multipart.MultipartFile file) {
        String fileName = file.getOriginalFilename();

        try {
            if (fileName.contains("..")) {
                throw new RuntimeException("Invalid path sequence " + fileName);
            }

            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            File dbFile = new File();
            dbFile.setFilename(fileName);
            dbFile.setFilepath(targetLocation.toString());
            dbFile.setUploadDate(LocalDateTime.now());

            return fileRepository.save(dbFile);
        } catch (Exception ex) {
            throw new RuntimeException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public List<File> getAllFiles() {
        return fileRepository.findAll();
    }

    public Optional<File> getFile(Long id) {
        return fileRepository.findById(id);
    }

    public void deleteFile(Long id) {
        fileRepository.deleteById(id);
    }

    // Metodlar: Dosya düzenleme eklenebilir
}