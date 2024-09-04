package com.project.graduation.services.implementations;

import com.project.graduation.services.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileServiceImpl implements FileService {

    @Override
    public String saveFile(MultipartFile file) {
        String uploadDir = "C:/thesis_files/";
        String originalFilename = file.getOriginalFilename();
        String filePath = uploadDir + originalFilename;

        try {
            File directory = new File(uploadDir);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            Path path = Paths.get(filePath);
            Files.write(path, file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to save file", e);
        }

        return filePath;
    }
}
