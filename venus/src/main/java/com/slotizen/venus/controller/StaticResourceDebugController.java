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

    @Value("${storage.local.base-dir}")
    private String baseDir;

    @GetMapping("/user-image/{filename}")
    public ResponseEntity<Resource> getStaticFile(@PathVariable("filename") String filename) {
        try {
            Path filePath = Paths.get("uploads", "user-image", filename);
            Resource resource = new UrlResource(filePath.toUri());
            
            System.out.println("=== Debug Static File Access ===");
            System.out.println("Requested filename: " + filename);
            System.out.println("Full file path: " + filePath);
            System.out.println("File exists: " + resource.exists());
            System.out.println("File readable: " + resource.isReadable());
            
            if (resource.exists() && resource.isReadable()) {
                return ResponseEntity.ok()
                        .contentType(MediaType.IMAGE_JPEG)
                        .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + filename + "\"")
                        .body(resource);
            } else {
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
                     "Expected uploads path: " + Paths.get(baseDir, "uploads").toAbsolutePath() + "\n" +
                     "Expected user-image path: " + Paths.get(baseDir, "uploads", "user-image").toAbsolutePath();
        return ResponseEntity.ok(info);
    }
}