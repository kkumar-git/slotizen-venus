package com.slotizen.venus.service.storage;

import com.slotizen.venus.config.StorageProperties;
import com.slotizen.venus.service.StorageService;
import com.slotizen.venus.util.FileValidationUtil;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.time.Instant;
import java.util.UUID;

@Service
@Profile("dev")
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
        String filename = contextType + "_" + refId + "_" + Instant.now().toEpochMilli()
                + "_" + UUID.randomUUID() + ext;

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
}