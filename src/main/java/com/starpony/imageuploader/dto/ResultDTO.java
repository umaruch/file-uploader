package com.starpony.imageuploader.dto;

public class ResultDTO {
    private final String url;

    public ResultDTO(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
