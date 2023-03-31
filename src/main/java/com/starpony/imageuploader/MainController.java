package com.starpony.imageuploader;

import com.starpony.imageuploader.configuration.UploadConfiguration;
import com.starpony.imageuploader.configuration.MinioConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@RestController
public class MainController {
    @PostMapping(path = "/upload/{imageType}")
    public void uploadImage(@PathVariable String imageType,
                                          @RequestParam MultipartFile image) throws IOException {
        Path root = Paths.get("./temp");
        Files.copy(image.getInputStream(), root.resolve(image.getName()));
    }
}
