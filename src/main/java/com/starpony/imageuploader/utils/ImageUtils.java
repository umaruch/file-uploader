package com.starpony.imageuploader.utils;

import com.starpony.imageuploader.Configuration;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import net.coobird.thumbnailator.tasks.UnsupportedFormatException;


import java.io.*;

public class ImageUtils {
    public static ByteArrayInputStream resize(InputStream stream, Configuration.ImageFormat format) throws Exception{
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            Thumbnails.of(stream).crop(Positions.CENTER).size(format.getWidth(), format.getHeight())
                    .keepAspectRatio(true).outputFormat(format.getType()).toOutputStream(outputStream);
        } catch (UnsupportedFormatException ex) {
            throw new IncorrectInputFileType();
        }
        return new ByteArrayInputStream(outputStream.toByteArray());
    }

    //  Ошибка, говорящая о том что входной поток не является изображением
    public static class IncorrectInputFileType extends Exception {
    }
}
