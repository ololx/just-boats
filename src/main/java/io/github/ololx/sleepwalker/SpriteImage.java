package io.github.ololx.sleepwalker;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.IOException;
import java.io.Serializable;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Objects;

/**
 * project sleepwalker
 * created 13.11.2022 15:43
 *
 * @author Alexander A. Kropotin
 */
public class SpriteImage {

    private int[] pixels;

    private BufferedImage image;

    private int width;

    private int height;

    private final int emptyPixel = 0x0;

    public SpriteImage(String imageUri) {
        this(imageUri, 1, 1);
    }

    public SpriteImage(String imageUri, double scaleWidth, double scaleHeight) {
        URL resource = getClass().getClassLoader().getResource(imageUri);

        if (resource == null) {
            throw new IllegalArgumentException("The image '" + imageUri + "' not found!");
        }

        try {
            BufferedImage image = ImageIO.read(resource);
            this.width = (int) (image.getWidth() * scaleWidth);
            this.height = (int) (image.getHeight() * scaleHeight);

            BufferedImage resized = new BufferedImage(this.width, this.height, image.getType());
            Graphics2D g = resized.createGraphics();
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g.drawImage(image, 0, 0, this.width, this.height, 0, 0, image.getWidth(),
                    image.getHeight(), null);
            g.dispose();

            this.image = resized;
            this.pixels = new int[this.width * this.height];
            resized.getRGB(
                    0,
                    0,
                    this.width,
                    this.height,
                    this.pixels,
                    0,
                    this.width);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int[] getPixels() {
        return pixels;
    }

    public BufferedImage getImage() {
        return image;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public int getEmptyPixel() {
        return this.emptyPixel;
    }

    public int getPixel(int x, int y) {
        Objects.checkIndex(x, this.width);
        Objects.checkIndex(y, this.height);

        return this.pixels[x + y * this.width];
    }

    public int getPixelSafety(int x, int y) {
        if (x < 0) {
            x = -x;
        }

        if (x >= this.width) {
            x = x % this.width;
        }

        if (y < 0) {
            y = -y;
        }

        if (y >= this.height) {
            y = y % this.height;
        }

        return this.getPixel(x, y);
    }
}
