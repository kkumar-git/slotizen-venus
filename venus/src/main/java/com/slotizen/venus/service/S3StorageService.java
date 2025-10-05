package com.slotizen.venus.service;

import java.time.Instant;

import org.springframework.web.multipart.MultipartFile;

import com.slotizen.venus.config.StorageProperties;
import com.slotizen.venus.util.FileValidationUtil;

import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

public class S3StorageService implements StorageService {

    private final StorageProperties props;
    private final S3Client s3Client;

    public S3StorageService(StorageProperties props) {
        this.props = props;
        this.s3Client = S3Client.builder()
                .region(Region.of(props.getS3().getRegion()))
                .credentialsProvider(DefaultCredentialsProvider.create())
                .build();
    }
        @Override
        public boolean delete(String contextType, String refId) {
            String prefix = props.getS3().getPrefix().replaceAll("/$", "") + "/" + contextType + "/" + refId + "/";
            try {
                var listResp = s3Client.listObjectsV2(builder -> builder
                    .bucket(props.getS3().getBucket())
                    .prefix(prefix)
                );
                boolean deleted = false;
                for (var obj : listResp.contents()) {
                    s3Client.deleteObject(builder -> builder
                        .bucket(props.getS3().getBucket())
                        .key(obj.key())
                    );
                    deleted = true;
                }
                return deleted;
            } catch (Exception e) {
                return false;
            }
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
        String key = props.getS3().getPrefix().replaceAll("/$", "")
                + "/" + contextType + "/" + filename;

        try {
            PutObjectRequest put = PutObjectRequest.builder()
                    .bucket(props.getS3().getBucket())
                    .key(key)
                    .contentType(file.getContentType())
                    .build();

            s3Client.putObject(put, RequestBody.fromBytes(file.getBytes()));

            // If using CloudFront / public bucket structure
            return props.getS3().getPublicBaseUrl().replaceAll("/$", "") + "/" + key;
        } catch (Exception e) {
            throw new RuntimeException("Failed to put object into S3", e);
        }
    }


}