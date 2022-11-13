package io.github.ololx.sleepwalker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.*;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.stream.IntStream;

public class Scene extends JFrame {

    /**
     * The constant SCENE_WIDTH.
     */
    public static final int SCENE_WIDTH = 600;

    /**
     * The constant SCENE_HEIGHT.
     */
    public static final int SCENE_HEIGHT = 337;

    /**
     * The constant SCENE_WIDTH.
     */
    public static final int WIDTH = 1366;

    /**
     * The constant SCENE_HEIGHT.
     */
    public static final int HEIGHT = 768;

    private BufferedImage image;

    public int[] pixels;

    public Scene() {
        setSize(WIDTH, HEIGHT);
        setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
                new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB),
                new Point(0, 0),
                "blank")
        );
        setResizable(true);
        setTitle("Sleepwalker");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(Color.black);
        setLocationRelativeTo(null);
        setVisible(true);

        image = new BufferedImage(SCENE_WIDTH, SCENE_HEIGHT, BufferedImage.TYPE_INT_RGB);
        pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
    }

    /**
     * Init.
     */
    public void init() {
        requestFocus();
    }

    public void clear() {
        IntStream.range(0, this.pixels.length)
                .parallel()
                .forEach(index -> this.pixels[index] = 0);
    }

    public void render() {
        int height = this.getSize().height;
        int width = this.getSize().width;

        BufferStrategy bs = getBufferStrategy();

        if (bs == null) {
            createBufferStrategy(2);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        g.drawImage(image, 0, 0, width, height,null);
        bs.show();
    }

    public void setPixel(int x, int y, int color) {
        if (x + y * SCENE_WIDTH >= this.pixels.length) {
            return;
        }

        this.pixels[x + y * SCENE_WIDTH] = color;
    }
}
