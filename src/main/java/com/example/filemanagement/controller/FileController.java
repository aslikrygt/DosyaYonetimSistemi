package com.example.filemanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.filemanagement.entity.File;
import com.example.filemanagement.service.FileService;

@Controller
public class FileController {

    @Autowired
    private FileService fileService;

    @GetMapping("/files")
    public String listUploadedFiles(Model model) {
        List<File> files = fileService.getAllFiles();
        model.addAttribute("files", files);
        return "file-list";
    }

    @GetMapping("/upload")
    public String showUploadForm() {
        return "upload";
    }

    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file, Model model) {
        fileService.storeFile(file);
        model.addAttribute("message", "You successfully uploaded " + file.getOriginalFilename() + "!");
        return "redirect:/files";
    }

    // Silme ve düzenleme için ek metodlar
}
