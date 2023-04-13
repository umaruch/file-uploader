package com.starpony.imageuploader.images;

import com.starpony.imageuploader.images.errors.ImagesException;
import com.starpony.imageuploader.images.models.ImageFormat;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;


public class ImageUtils {
    public static ByteArrayInputStream resize(InputStream stream, ImageFormat format) throws ImagesException {
        try {
            BufferedImage image =
        //

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
