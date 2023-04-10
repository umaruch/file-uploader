package com.starpony.imageuploader.images.repositories;

import com.starpony.imageuploader.images.errors.ImagesException;

import java.io.InputStream;


public interface ImageRepository {
    void save(String path, String filename, InputStream inputStream) throws ImagesException;
}
