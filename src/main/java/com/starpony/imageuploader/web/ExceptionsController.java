package com.starpony.imageuploader.web;

import com.starpony.imageuploader.images.errors.ImagesException;
import com.starpony.imageuploader.web.dto.ErrorDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;


@RestControllerAdvice
public class ExceptionsController {
    private static Logger LOGGER = LoggerFactory.getLogger(ExceptionsController.class);

    @ExceptionHandler({ImagesException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorDTO handleUploadFileRequestException(Exception ex) {
        LOGGER.warn(ex.getMessage());
        return new ErrorDTO(ex.getMessage());
    }

    @ExceptionHandler({IOException.class, RuntimeException.class})
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDTO handleRuntimeException(RuntimeException ex) {
        LOGGER.error("Server exception", ex);
        return new ErrorDTO("Server exception");
    }
}
