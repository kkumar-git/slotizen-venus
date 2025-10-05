package com.slotizen.venus.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class StaticResourceConfig implements WebMvcConfigurer {

    private final StorageProperties props;

    public StaticResourceConfig(StorageProperties props) {
        this.props = props;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        Path baseDir = Paths.get(props.getLocal().getBaseDir()).toAbsolutePath().normalize();
        String uploadsPath = baseDir + File.separator + "uploads" + File.separator;
        
        // Debug logging
        System.out.println("=== Static Resource Configuration ===");
        System.out.println("Base Directory: " + baseDir);
        System.out.println("Uploads Path: " + uploadsPath);
        System.out.println("Resource Location: file:" + uploadsPath);
        
        // Check if uploads directory exists
        File uploadsDir = new File(uploadsPath);
        System.out.println("Uploads directory exists: " + uploadsDir.exists());
        System.out.println("Uploads directory path: " + uploadsDir.getAbsolutePath());
        
        // Map /static/** to the uploads directory
        registry.addResourceHandler("/static/**")
                .addResourceLocations("file:" + uploadsPath)
                .setCachePeriod(0) // Disable cache for debugging
                .resourceChain(false); // Disable resource chain for debugging
                
        // Backup mapping for /uploads/**
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:" + uploadsPath)
                .setCachePeriod(0);
    }
}