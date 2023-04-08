package com.starpony.imageuploader.web;

import com.starpony.imageuploader.web.dto.ErrorDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ExceptionsController {
    private static Logger LOGGER = LoggerFactory.getLogger(ExceptionsController.class);

    @ExceptionHandler({NoSuchFieldException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorDTO handleUploadFileRequestException(RuntimeException ex) {
        LOGGER.warn(ex.getMessage());
        return new ErrorDTO(ex.getMessage());
    }

    @ExceptionHandler({RuntimeException.class})
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDTO handleRuntimeException(RuntimeException ex) {
        LOGGER.error("Server error", ex);
        return new ErrorDTO("Internal Server Error");
    }
}
