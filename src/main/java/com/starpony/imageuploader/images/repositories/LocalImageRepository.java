package com.starpony.imageuploader.images.repositories;

import com.starpony.imageuploader.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Repository
@ConditionalOnProperty(value = "configuration.storage.local.enabled", havingValue = "true")
public class LocalImageRepository implements ImageRepository {
    private final Path root;

    @Autowired
    public LocalImageRepository(Configuration configuration) {
        root = Paths.get(configuration.getStorageConfiguration("local").get("path"));
    }

    @Override
    public void save(String path, String filename, InputStream inputStream) {
        try {
            Files.copy(inputStream, root.resolve(path).resolve(filename));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
