package com.starpony.imageuploader.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;


@Component
@ConfigurationProperties(prefix = "upload")
public class UploadConfiguration {
    private Map<String, ImageType> types;

    public Map<String, ImageType> getTypes() {
        return types;
    }

    public void setTypes(Map<String, ImageType> types) {
        this.types = types;
    }
}
