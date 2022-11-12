package io.github.ololx.sleepwalker;

import io.github.ololx.mooncake.math.VectorXYD;

import java.awt.*;
import java.util.logging.Logger;

/**
 * project sleepwalker
 * created 08.11.2022 20:01
 *
 * @author Alexander A. Kropotin
 */
public class StupidSquare implements GameObject {

    private final Logger log = Logger.getLogger(this.getClass().getName());

    private VectorXYD position;

    private VectorXYD direction;

    private final VectorXYD velocity = new VectorXYD(1, 1);

    private final double rotation = Math.toRadians(-90);

    int frameLimit = 30;

    int currentFrames = 0;

    int state = 0;

    public StupidSquare(double x, double y) {
        this.position = new VectorXYD(x, y);
        this.direction = new VectorXYD(0, 1).normalize();
    }

    @Override
    public void update() {
        if (++currentFrames >= frameLimit) {
            currentFrames = 0;
            state++;

            this.direction = this.direction.rotate(this.rotation);
            log.info("Current direction: " + this.direction.toString());
        }

        this.position = this.position.add(this.velocity.multiply(direction));

        currentFrames++;
    }

    @Override
    public void render(Scene scene) {
        for (int i = 0; i < Scene.SCENE_WIDTH; i++) {
            for (int j = 0; j < Scene.SCENE_HEIGHT; j++) {
                if (i < this.position.getX() - 2 || i > this.position.getX() + 2) {
                    continue;
                }

                if (j < this.position.getY() - 2 || j > this.position.getY() + 2) {
                    continue;
                }

                scene.pixels[i + j * Scene.SCENE_WIDTH] = Color.GREEN.getRGB();
            }
        }
    }
}
