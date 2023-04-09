package com.starpony.imageuploader;

import com.starpony.imageuploader.images.models.ImageFormat;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.NoSuchElementException;


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
        Map<String, String> storageConfig = storage.get(storageType);
        if (storageConfig == null)
            throw new NoSuchElementException("Storage configuration not found");

        return storageConfig;
    }

    public ImageFormat getFormat(String path) {
        ImageFormat imageFormat = formats.get(path);
        if (imageFormat == null)
            throw new NoSuchElementException("Image format not found");

        return imageFormat;
    }
}
