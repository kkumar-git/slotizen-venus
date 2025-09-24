package com.slotizen.venus.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

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
        registry.addResourceHandler("/static/**")
                .addResourceLocations("file:" + baseDir + "/");
    }
}