package com.starpony.imageuploader.images.repositories;


import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;


public class LocalImageRepository implements ImageRepository {
    private final Path root;

    public LocalImageRepository(Map<String, String> configuration) {
        root = Paths.get(configuration.get("path"));
    }

    @Override
    public void save(String path, String filename, InputStream inputStream) {
        System.out.println(root.toAbsolutePath());
        try {
            Files.copy(inputStream, root.resolve(path).resolve(filename));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
