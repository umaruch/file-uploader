package com.starpony.imageuploader.utils;

import com.starpony.imageuploader.configuration.ImageType;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import net.coobird.thumbnailator.tasks.UnsupportedFormatException;


import java.io.*;

public class ImageUtils {
    public static ByteArrayInputStream resize(InputStream stream, ImageType format) throws IncorrectInputFileType, Exception{
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            Thumbnails.of(stream).crop(Positions.CENTER).size(format.getWidth(), format.getHeight())
                    .keepAspectRatio(true).outputFormat(format.getFormat()).toOutputStream(outputStream);
        } catch (UnsupportedFormatException ex) {
            throw new IncorrectInputFileType();
        }
        return new ByteArrayInputStream(outputStream.toByteArray());
    }

    //  Ошибка, говорящая о том что входной поток не является изображением
    public static class IncorrectInputFileType extends Exception {
    }
}
