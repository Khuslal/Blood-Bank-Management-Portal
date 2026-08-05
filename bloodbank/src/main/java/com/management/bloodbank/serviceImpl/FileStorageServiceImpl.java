package com.management.bloodbank.serviceImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.management.bloodbank.service.FileStorageService;

@Service
public class FileStorageServiceImpl implements FileStorageService {

    @Value("${app.upload.dir}")
    private String uploadDir;

    @Override
    public String storeFile(MultipartFile file) throws IOException {

        Path uploadPath = Paths.get(uploadDir);

        // Debug: Print upload directory
        System.out.println("====================================");
        System.out.println("Upload Directory : " + uploadPath.toAbsolutePath());

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
            System.out.println("Upload directory created.");
        } else {
            System.out.println("Upload directory already exists.");
        }

        String originalFilename = file.getOriginalFilename();
        String extension = "";

        if (originalFilename != null && originalFilename.contains(".")) {
            extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        }

        String storedFileName = UUID.randomUUID().toString() + extension;

        Path targetPath = uploadPath.resolve(storedFileName);

        // Debug: Print target file path
        System.out.println("Original File Name : " + originalFilename);
        System.out.println("Stored File Name   : " + storedFileName);
        System.out.println("Target File Path   : " + targetPath.toAbsolutePath());

        Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);

        // Debug: Verify file exists
        System.out.println("File Exists        : " + Files.exists(targetPath));
        System.out.println("====================================");

        return storedFileName;
    }

    @Override
    public Path loadFile(String storedFileName) {
        return Paths.get(uploadDir).resolve(storedFileName).normalize();
    }
}