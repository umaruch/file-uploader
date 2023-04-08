package com.starpony.imageuploader;

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

    public String getStorageType() {
        return storage.keySet().stream().findFirst()
                .orElseThrow(() -> new NoSuchElementException("Image storage configuration not found"));
    }

    public Map<String, String> getStorageConfiguration(String storageType) {
        return storage.get(storageType);
    }

    public ImageFormat getFormat(String path) {
        return formats.get(path);
    }

    public static class ImageFormat {
        private String type;
        private int width;
        private int height;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }
    }
}
