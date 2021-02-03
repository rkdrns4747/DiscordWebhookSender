package org.dr_romantic.Utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ResourceUtils {
    public static final String RESOURCE_PATH = "src/resources/";
    public static Image getImageFromPath(String path) throws IOException {
        BufferedImage resourceImage = ImageIO.read(new File(path));
        if(resourceImage == null)
            throw new IOException();

        return resourceImage;
    }
}
