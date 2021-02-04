package org.dr_romantic.Utils;

import org.dr_romantic.main.DiscordWebhookSender;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ResourceUtils {
    public static BufferedImage getImageFromPath(String fileName) throws IOException {
        BufferedImage resourceImage = ImageIO.read(DiscordWebhookSender.class.getResource("resources/"+fileName));
        if(resourceImage == null)
            throw new IOException();

        return resourceImage;
    }

    public static BufferedImage cropAsAvatarImage(BufferedImage img){
        int width = img.getWidth();
        int height = img.getHeight();
        int base = Math.min(width, height);

        return width <= height ? img.getSubimage(0, Math.abs(width - height) / 2, base, base) : img.getSubimage(Math.abs(width - height) / 2, 0, base, base);
    }
}
