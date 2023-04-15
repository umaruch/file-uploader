package com.starpony.imageuploader.images;

import com.starpony.imageuploader.images.errors.ImagesException;
import com.starpony.imageuploader.images.models.ImageFormat;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.*;


public class ImageUtils {
    public static ByteArrayInputStream resize(InputStream stream, ImageFormat format) throws ImagesException {

        try {
            BufferedImage croppedImage = cropImage(ImageIO.read(stream), format);
            BufferedImage resultImage = new BufferedImage(
                    format.getWidth(), format.getHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics2D = resultImage.createGraphics();
            graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            graphics2D.drawImage(croppedImage, 0, 0, format.getWidth(), format.getHeight(), null);
            graphics2D.dispose();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ImageIO.write(resultImage, format.getType(), outputStream);
            return new ByteArrayInputStream(outputStream.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /*
        Вырезает из центральной части передаваемого изображения
        кусок с соотношением сторон, соответствующим передаваемому формату
     */
    private static BufferedImage cropImage(BufferedImage sourceImage, ImageFormat format) {
        // Получение соотношений сторон исходного изображения и выходного формата
        double sourceRatio = (double)sourceImage.getWidth() / (double)sourceImage.getHeight();
        double resultRatio = (double)format.getWidth() / (double)format.getHeight();

        int x, y, width, height;

        // Получение начальных координат, ширины и высоты вырезаемого куска изображения
        if (resultRatio <= sourceRatio) {
            height = sourceImage.getHeight();
            width = (int)(height * resultRatio);
            y = 0;
            x = (sourceImage.getWidth() - width) / 2;
        } else {
            width = sourceImage.getWidth();
            resultRatio = (double)format.getHeight() / (double)format.getWidth();
            height = (int) (width * resultRatio);
            x = 0;
            y = (sourceImage.getHeight() - height) / 2;
        }

        return sourceImage.getSubimage(x, y, width, height);
    }
}
