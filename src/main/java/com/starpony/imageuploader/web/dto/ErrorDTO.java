package com.starpony.imageuploader.web.dto;

public class ErrorDTO {
    private final String detail;

    public ErrorDTO(String detail) {
        this.detail = detail;
    }

    public String getDetail() {
        return detail;
    }
}
