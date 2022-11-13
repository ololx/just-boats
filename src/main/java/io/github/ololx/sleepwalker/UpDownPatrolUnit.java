package io.github.ololx.sleepwalker;

import io.github.ololx.mooncake.math.VectorXYD;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * project sleepwalker
 * created 08.11.2022 20:01
 *
 * @author Alexander A. Kropotin
 */
public class UpDownPatrolUnit implements GameObject {

    private final Logger log = Logger.getLogger(this.getClass().getName());

    private VectorXYD position;

    private VectorXYD direction;

    private final VectorXYD velocity = new VectorXYD(1, 1);

    private final double rotation = Math.toRadians(-180);

    int frameLimit = 100;

    int currentFrames = 0;

    int state = 0;

    List<SpriteImage> spriteImages = List.of(
            new SpriteImage("Boat1_water_frame2.png", 0.25, 0.25),
            new SpriteImage("Boat1_water_frame1.png", 0.25, 0.25)
    );

    SpriteImage spriteImage = spriteImages.get(0);

    public UpDownPatrolUnit(double x, double y) {
        this.position = new VectorXYD(x, y);
        this.direction = new VectorXYD(0, -1).normalize();
    }

    @Override
    public void update() {
        if (++currentFrames >= frameLimit) {
            currentFrames = 0;
            state++;

            this.direction = this.direction.rotate(this.rotation);
        }

        this.position = this.position.add(this.velocity.multiply(direction));

        this.spriteImage = this.spriteImages.get(state % 2 == 0 ? 0 : 1);

        currentFrames++;
    }

    @Override
    public void render(Scene scene) {
        for (int x = 0; x < this.spriteImage.getWidth(); x++) {
            for (int y = 0; y < this.spriteImage.getHeight(); y++) {
                scene.setPixel(
                        (int) (x + this.position.getX() - this.spriteImage.getWidth() / 2),
                        (int) (y + this.position.getY() - this.spriteImage.getHeight() / 2),
                        this.spriteImage.getPixel(x, y)
                );
            }
        }
    }
}
