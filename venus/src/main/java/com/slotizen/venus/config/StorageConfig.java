package com.slotizen.venus.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.slotizen.venus.service.LocalStorageService;
import com.slotizen.venus.service.S3StorageService;
import com.slotizen.venus.service.StorageService;

@Configuration
public class StorageConfig {

    @Bean
    @ConditionalOnProperty(name = "app.storage.provider", havingValue = "local", matchIfMissing = true)
    public StorageService localStorage(StorageProperties props) {
        return new LocalStorageService(props);
    }

    @Bean
    @ConditionalOnProperty(name = "app.storage.provider", havingValue = "s3")
    public StorageService s3Storage(StorageProperties props) {
        return new S3StorageService(props);
    }
}