package com.starpony.imageuploader.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;


@Component
@ConfigurationProperties(prefix = "upload")
public class UploadConfiguration {
    private Map<String, ImageSize> formats;

    public Map<String, ImageSize> getFormats() {
        return formats;
    }

    public void setFormats(Map<String, ImageSize> formats) {
        this.formats = formats;
    }

    public static class ImageSize {
        private int width;
        private int height;

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
