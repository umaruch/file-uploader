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
            BufferedImage originalImage = ImageIO.read(stream);
            //originalImage.getSubimage(); Сделать получение фрагмента изображения с необходимым соотношением сторон
            BufferedImage resultImage = new BufferedImage(
                    format.getWidth(), format.getHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics2D = resultImage.createGraphics();
            graphics2D.drawImage(originalImage, 0, 0, format.getWidth(), format.getHeight(), null);
            graphics2D.dispose();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ImageIO.write(resultImage, format.getType(), outputStream);
            return new ByteArrayInputStream(outputStream.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
