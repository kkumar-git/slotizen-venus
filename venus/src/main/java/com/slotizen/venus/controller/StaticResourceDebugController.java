package com.slotizen.venus.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/static")
public class StaticResourceDebugController {

    @Value("${app.storage.local.base-dir:/opt/slotizen/uploads}")
    private String baseDir;

    @GetMapping("/user-image/{filename}")
    public ResponseEntity<Resource> getStaticFile(@PathVariable("filename") String filename) {
        try {
            // Fix: Use the actual base directory, not relative path
            Path filePath = Paths.get(baseDir, "user-image", filename);
            Resource resource = new UrlResource(filePath.toUri());
            
            System.out.println("=== Debug Static File Access ===");
            System.out.println("Base directory: " + baseDir);
            System.out.println("Requested filename: " + filename);
            System.out.println("Full file path: " + filePath.toAbsolutePath());
            System.out.println("File exists: " + resource.exists());
            System.out.println("File readable: " + resource.isReadable());
            
            if (resource.exists() && resource.isReadable()) {
                // Determine content type based on file extension
                String contentType = determineContentType(filename);
                
                return ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType(contentType))
                        .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + filename + "\"")
                        .header(HttpHeaders.CACHE_CONTROL, "max-age=3600") // Cache for 1 hour
                        .body(resource);
            } else {
                System.out.println("File not found or not readable");
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            System.out.println("Error serving file: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
    
    @GetMapping("/info")
    public ResponseEntity<String> getDebugInfo() {
        String info = "Base Directory: " + baseDir + "\n" +
                     "Expected uploads path: " + Paths.get(baseDir).toAbsolutePath() + "\n" +
                     "Expected user-image path: " + Paths.get(baseDir, "user-image").toAbsolutePath() + "\n" +
                     "Directory exists: " + Paths.get(baseDir).toFile().exists() + "\n" +
                     "User-image directory exists: " + Paths.get(baseDir, "user-image").toFile().exists();
        return ResponseEntity.ok(info);
    }
    
    private String determineContentType(String filename) {
        String extension = filename.substring(filename.lastIndexOf('.') + 1).toLowerCase();
        switch (extension) {
            case "jpg":
            case "jpeg":
                return "image/jpeg";
            case "png":
                return "image/png";
            case "gif":
                return "image/gif";
            case "webp":
                return "image/webp";
            default:
                return "application/octet-stream";
        }
    }
}