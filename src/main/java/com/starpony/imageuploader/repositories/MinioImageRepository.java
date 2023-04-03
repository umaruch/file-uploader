package com.starpony.imageuploader.repositories;

import io.minio.*;

import java.io.InputStream;
import java.util.Map;
import java.util.UUID;


public class MinioImageRepository implements ImageRepository{
    private final MinioClient minioClient;

    public MinioImageRepository(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    @Override
    public void save(String formatName, String filename, InputStream inputStream) {
        try {
            minioClient.putObject(PutObjectArgs.builder()
                    .bucket(formatName).object(filename).contentType("image/jpeg").stream(inputStream, -1, 10485760).build());
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
