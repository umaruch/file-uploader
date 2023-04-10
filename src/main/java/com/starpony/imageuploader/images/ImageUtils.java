package com.starpony.imageuploader.images;

import com.starpony.imageuploader.images.errors.ImagesException;
import com.starpony.imageuploader.images.models.ImageFormat;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import net.coobird.thumbnailator.tasks.UnsupportedFormatException;

import java.io.*;


public class ImageUtils {
    public static ByteArrayInputStream resize(InputStream stream, ImageFormat format) throws ImagesException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            Thumbnails.of(stream).crop(Positions.CENTER).size(format.getWidth(), format.getHeight())
                    .keepAspectRatio(true).outputFormat(format.getType()).toOutputStream(outputStream);
        } catch (UnsupportedFormatException ex) {
            throw new ImagesException("The image must be in format .png, .jpg or jpeg");
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        return new ByteArrayInputStream(outputStream.toByteArray());
    }
}
