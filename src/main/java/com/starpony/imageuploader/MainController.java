package com.starpony.imageuploader;

import com.starpony.imageuploader.configuration.ImageType;
import com.starpony.imageuploader.configuration.UploadConfiguration;
import com.starpony.imageuploader.dto.ResultDTO;
import com.starpony.imageuploader.errors.UploadFileRequestException;
import com.starpony.imageuploader.repositories.ImageRepository;
import com.starpony.imageuploader.utils.ImageUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.UUID;


@RestController
public class MainController {
    private static Logger LOGGER = LoggerFactory.getLogger(MainController.class);

    private final UploadConfiguration uploadConfiguration;
    private final ImageRepository imageRepository;

    @Autowired
    public MainController(UploadConfiguration uploadConfiguration, ImageRepository imageRepository) {
        this.uploadConfiguration = uploadConfiguration;
        this.imageRepository = imageRepository;
    }

    @PostMapping(path = "/upload/{typeName}")
    public ResultDTO uploadImage(@PathVariable String typeName,
                                 @RequestParam MultipartFile image){
        LOGGER.info(String.format("[/upload/%s] Upload file %s", typeName, image.getOriginalFilename()));

        ImageType imageType = uploadConfiguration.getTypes().get(typeName);
        if (imageType == null)
            throw new UploadFileRequestException("Invalid upload type name.");

        //  Обработка изображения
        ByteArrayInputStream resizedImageStream = null;
        try {
            resizedImageStream = ImageUtils.resize(image.getInputStream(), imageType);
        } catch (ImageUtils.IncorrectInputFileType ex) {
            throw new UploadFileRequestException("The image to be uploaded must be in the format .png, .jpeg or .jpg.");
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

        //  Сохранение файла в хранилище
        String filename = String.format("%s.%s", UUID.randomUUID(), imageType.getFormat());
        try {
            imageRepository.save(typeName, filename, resizedImageStream);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        LOGGER.info(String.format("File saved as %s", filename));

        return new ResultDTO(String.format("/%s/%s", typeName, filename));
    }
}
