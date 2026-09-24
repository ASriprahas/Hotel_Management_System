package com.LakeView.LakeView.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.UUID;

@Service
public class FileStorageService {

    private static final String UPLOAD_DIR = "uploads";

    public String saveImage(MultipartFile file) {

        if (file == null || file.isEmpty()) {
            return null;
        }

        try {

            Path uploadPath = Paths.get(UPLOAD_DIR);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            String originalFileName = file.getOriginalFilename();

            String fileExtension = "";

            if (originalFileName != null &&
                    originalFileName.contains(".")) {

                fileExtension =
                        originalFileName.substring(
                                originalFileName.lastIndexOf("."));
            }

            String fileName =
                    UUID.randomUUID() + fileExtension;

            Path filePath =
                    uploadPath.resolve(fileName);

            Files.copy(
                    file.getInputStream(),
                    filePath,
                    StandardCopyOption.REPLACE_EXISTING
            );

            return "/uploads/" + fileName;

        } catch (IOException e) {

            throw new RuntimeException(
                    "Failed to store image: "
                            + e.getMessage());
        }
    }

    public void deleteImage(String imagePath) {

        try {

            if (imagePath == null || imagePath.isBlank()) {
                return;
            }

            String fileName =
                    Paths.get(imagePath)
                            .getFileName()
                            .toString();

            Path path =
                    Paths.get(UPLOAD_DIR, fileName);

            Files.deleteIfExists(path);

        } catch (IOException e) {

            throw new RuntimeException(
                    "Failed to delete image: "
                            + e.getMessage());
        }
    }
}