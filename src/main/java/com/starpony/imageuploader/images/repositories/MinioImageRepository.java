package com.starpony.imageuploader.images.repositories;

import io.minio.*;

import java.io.InputStream;
import java.util.Map;


public class MinioImageRepository implements ImageRepository {
    private final MinioClient minioClient;

    public MinioImageRepository(Map<String, String> configuration) {
        minioClient = MinioClient.builder().endpoint(configuration.get("address"))
                .credentials(configuration.get("accessKey"), configuration.get("secretKey")).build();
    }

    @Override
    public void save(String path, String filename, InputStream inputStream) {
        try {
            minioClient.putObject(PutObjectArgs.builder()
                    .bucket(path).object(filename).contentType("image/jpeg").stream(inputStream, -1, 10485760).build());
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
