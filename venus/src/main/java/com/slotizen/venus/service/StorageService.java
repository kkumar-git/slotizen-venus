package com.slotizen.venus.service;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
    /**
     * Store the file and return a public URL.
     * @param contextType e.g. "business-logo" or "user-avatar"
     * @param refId identifier (businessId or userId)
     */
    String store(String contextType, String refId, MultipartFile file);

    /**
     * Delete files for the given contextType and refId. Returns true if any file was deleted.
     */
    boolean delete(String contextType, String refId);
}