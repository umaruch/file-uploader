package com.starpony.imageuploader.errors;

public class UploadFileRequestException extends RuntimeException{
    public UploadFileRequestException(String message) {
        super(message);
    }
}
