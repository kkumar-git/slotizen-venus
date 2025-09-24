package com.slotizen.venus.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "app.storage")
public class StorageProperties {

    private String provider = "local"; // local | s3
    private long maxSizeBytes = 2 * 1024 * 1024;
    private List<String> allowedContentTypes;

    private Local local = new Local();
    private S3 s3 = new S3();

    public static class Local {
        private String baseDir = "uploads";
        private String publicBaseUrl = "http://localhost:8081/static";

        public String getBaseDir() { return baseDir; }
        public void setBaseDir(String baseDir) { this.baseDir = baseDir; }
        public String getPublicBaseUrl() { return publicBaseUrl; }
        public void setPublicBaseUrl(String publicBaseUrl) { this.publicBaseUrl = publicBaseUrl; }
    }

    public static class S3 {
        private String bucket;
        private String publicBaseUrl;
        private String region;
        private String prefix = "uploads";

        public String getBucket() { return bucket; }
        public void setBucket(String bucket) { this.bucket = bucket; }
        public String getPublicBaseUrl() { return publicBaseUrl; }
        public void setPublicBaseUrl(String publicBaseUrl) { this.publicBaseUrl = publicBaseUrl; }
        public String getRegion() { return region; }
        public void setRegion(String region) { this.region = region; }
        public String getPrefix() { return prefix; }
        public void setPrefix(String prefix) { this.prefix = prefix; }
    }

    public String getProvider() { return provider; }
    public void setProvider(String provider) { this.provider = provider; }
    public long getMaxSizeBytes() { return maxSizeBytes; }
    public void setMaxSizeBytes(long maxSizeBytes) { this.maxSizeBytes = maxSizeBytes; }
    public List<String> getAllowedContentTypes() { return allowedContentTypes; }
    public void setAllowedContentTypes(List<String> allowedContentTypes) { this.allowedContentTypes = allowedContentTypes; }
    public Local getLocal() { return local; }
    public S3 getS3() { return s3; }
}