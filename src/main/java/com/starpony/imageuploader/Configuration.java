package com.starpony.imageuploader;

import com.starpony.imageuploader.images.models.ImageFormat;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;


@Component
@ConfigurationProperties(prefix = "configuration")
public class Configuration {
    private Map<String, Map<String, String>> storage;
    private Map<String, ImageFormat> formats;

    public void setStorage(Map<String, Map<String, String>> storage) {
        this.storage = storage;
    }

    public void setFormats(Map<String, ImageFormat> formats) {
        this.formats = formats;
    }

    public Map<String, String> getStorageConfiguration(String storageType) {
        return storage.get(storageType);
    }

    public Optional<ImageFormat> getFormat(String path) {
        return Optional.ofNullable(formats.get(path));
    }
}
