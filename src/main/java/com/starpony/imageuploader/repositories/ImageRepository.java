package com.starpony.imageuploader.repositories;

import java.io.InputStream;

public interface ImageRepository {
    void save(String formatName, String filename, InputStream inputStream) throws Exception;
}
