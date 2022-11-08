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
public class Player implements GameObject {

    private final Logger log = Logger.getLogger(this.getClass().getName());

    private VectorXYD position;

    public Player(int x, int y) {
        this.position = new VectorXYD(x, y);
    }

    @Override
    public void update() {
        this.position = this.position.add(new VectorXYD(0, 0.1));
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

                scene.pixels[i + j * scene.SCENE_WIDTH] = Color.GREEN.getRGB();
            }
        }

        log.info("Current position: " + this.position.toString());
    }
}
