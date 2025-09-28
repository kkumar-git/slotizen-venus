package com.slotizen.venus.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Instant;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.slotizen.venus.config.StorageProperties;
import com.slotizen.venus.util.FileValidationUtil;

public class LocalStorageService implements StorageService {

    private final StorageProperties props;

    public LocalStorageService(StorageProperties props) {
        this.props = props;
    }

    @Override
    public String store(String contextType, String refId, MultipartFile file) {
        FileValidationUtil.validate(file, props);
        String original = file.getOriginalFilename();
        String ext = "";
        if (original != null && original.contains(".")) {
            ext = original.substring(original.lastIndexOf("."));
        }
    String dateTime = java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
    String filename = contextType + "_" + refId + "_" + dateTime + ext;

        Path baseDir = Paths.get(props.getLocal().getBaseDir(), contextType);
        try {
            Files.createDirectories(baseDir);
            Path target = baseDir.resolve(filename);
            Files.copy(file.getInputStream(), target, StandardCopyOption.REPLACE_EXISTING);
            return props.getLocal().getPublicBaseUrl().replaceAll("/$", "")
                    + "/" + contextType + "/" + filename;
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file locally", e);
        }
    }
    
        @Override
        public boolean delete(String contextType, String refId) {
            Path baseDir = Paths.get(props.getLocal().getBaseDir(), contextType);
            try {
                // Find files matching the pattern contextType_refId_*
                boolean deleted = false;
                try (var files = Files.list(baseDir)) {
                    for (Path file : (Iterable<Path>) files::iterator) {
                        String fname = file.getFileName().toString();
                        if (fname.startsWith(contextType + "_" + refId + "_")) {
                            Files.deleteIfExists(file);
                            deleted = true;
                        }
                    }
                }
                return deleted;
            } catch (IOException e) {
                return false;
            }
        }


}