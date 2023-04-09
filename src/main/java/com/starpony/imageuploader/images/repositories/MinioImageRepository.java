package com.starpony.imageuploader.images.repositories;

import com.starpony.imageuploader.Configuration;

import io.minio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

import java.io.InputStream;
import java.util.Map;


@Repository
@ConditionalOnProperty(value = "configuration.storage.minio.enabled", havingValue = "true")
public class MinioImageRepository implements ImageRepository {
    private final MinioClient minioClient;

    @Autowired
    public MinioImageRepository(
            Configuration configuration
    ) {
        Map<String, String> storageConfig = configuration.getStorageConfiguration("minio");
        minioClient = MinioClient.builder().endpoint(storageConfig.get("address"))
                .credentials(storageConfig.get("accessKey"), storageConfig.get("secretKey")).build();
    }

    @Override
    public void save(String path, String filename, InputStream inputStream) {
        try {
            minioClient.putObject(PutObjectArgs.builder()
                    .bucket(path).object(filename)
                    .contentType("image/jpeg")
                    .stream(inputStream, -1, 10485760)
                    .build());
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
