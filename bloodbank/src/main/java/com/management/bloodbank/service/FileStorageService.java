package com.management.bloodbank.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileStorageService {

	private final Path uploadDir;

	public FileStorageService(@Value("${app.upload.dir:uploads/requests}") String uploadDirProp) {
		this.uploadDir = Paths.get(uploadDirProp).toAbsolutePath().normalize();
		try {
			Files.createDirectories(this.uploadDir);
		} catch (IOException e) {
			throw new RuntimeException("Could not create upload directory: " + this.uploadDir, e);
		}
	}

	/*
	 * Saves the file and returns the generated filename, or null if no file was
	 * provided.
	 */
	public String store(MultipartFile file) {
		if (file == null || file.isEmpty()) {
			return null;
		}
		String original = StringUtils.cleanPath(file.getOriginalFilename() == null ? "" : file.getOriginalFilename());
		String extension = original.contains(".") ? original.substring(original.lastIndexOf(".")) : "";
		String storedName = UUID.randomUUID() + extension;

		try {
			Path target = uploadDir.resolve(storedName);
			Files.copy(file.getInputStream(), target, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			throw new RuntimeException("Failed to store file: " + original, e);
		}
		return storedName;
	}
}