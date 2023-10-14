package org.example.utils;

import org.example.command.AppBotCommand;
import org.example.func.FilterOperations;
import org.example.func.ImagesOperation;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ImageUtils {

    public static BufferedImage getImage(String path) throws IOException {
        final File file = new File(path);
        return ImageIO.read(file);
    }

    public static void saveImage(BufferedImage image, String path) throws IOException {
        ImageIO.write(image, "png", new File(path));
    }

    static float[] rgbIntToArray(int pixel) {
        Color color = new Color(pixel);
        return color.getRGBComponents(null);
    }


    public static ImagesOperation getOperation(String operationName) {
        FilterOperations filterOperations = new FilterOperations();
        Method[] classMethods = filterOperations.getClass().getDeclaredMethods();
        for (Method method : classMethods) {
            if (method.isAnnotationPresent(AppBotCommand.class)) {
                AppBotCommand command =
                        method.getAnnotation(AppBotCommand.class);
                if (command.name().equals(operationName)) {
                    return (f) -> {
                        try {
                            return (float[]) method.invoke(filterOperations, f);
                        } catch (IllegalAccessException |
                                 InvocationTargetException e) {
                            throw new RuntimeException(e);
                        }
                    };
                }
            }
        }
        return null;
    }

    static int arrayToRgbInt(float[] pixel) throws Exception {
        Color color = null;
        if (pixel.length == 3) {
            color = new Color(pixel[0], pixel[1], pixel[2]);
        } else if (pixel.length == 4) {
            color = new Color(pixel[0], pixel[1], pixel[2], pixel[3]);
        }
        if (color != null) {
            return color.getRGB();
        }
        throw  new Exception("invalide color");
    }
}
