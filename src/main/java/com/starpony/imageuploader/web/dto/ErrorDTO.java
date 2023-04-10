package com.starpony.imageuploader.web.dto;

import java.util.Date;

public class ErrorDTO {
    private final Date timestamp;
    private final String detail;

    public ErrorDTO(String detail) {
        this.timestamp = new Date();
        this.detail = detail;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getDetail() {
        return detail;
    }
}
