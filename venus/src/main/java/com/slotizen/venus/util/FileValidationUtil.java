package com.slotizen.venus.util;

import com.slotizen.venus.config.StorageProperties;
import org.springframework.web.multipart.MultipartFile;

public class FileValidationUtil {

    public static void validate(MultipartFile file, StorageProperties props) {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("File is empty");
        }
        if (file.getSize() > props.getMaxSizeBytes()) {
            throw new IllegalArgumentException("File exceeds max size " + props.getMaxSizeBytes());
        }
        String ct = file.getContentType();
        if (ct == null || props.getAllowedContentTypes() == null ||
                props.getAllowedContentTypes().stream().noneMatch(a -> a.equalsIgnoreCase(ct))) {
            throw new IllegalArgumentException("Unsupported content type: " + ct);
        }
    }
}