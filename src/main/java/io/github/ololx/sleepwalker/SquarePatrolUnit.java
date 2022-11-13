package io.github.ololx.sleepwalker;

import io.github.ololx.mooncake.math.VectorXYD;

import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * project sleepwalker
 * created 08.11.2022 20:01
 *
 * @author Alexander A. Kropotin
 */
public class SquarePatrolUnit implements GameObject {

    private final Logger log = Logger.getLogger(this.getClass().getName());

    private VectorXYD position;

    private VectorXYD direction;

    private final VectorXYD velocity = new VectorXYD(0.5, 0.5);

    private final double rotation = Math.toRadians(-90);

    int frameLimit = 200;

    int currentFrames = 0;

    int state = 0;

    List<SpriteAnimation> spriteImages = List.of(
            new SpriteAnimation(
                    Map.of(
                            new SpriteImage("Boat3_water_frame1.png", 0.25, 0.25), 3,
                            new SpriteImage("Boat3_water_frame2.png", 0.25, 0.25), 3,
                            new SpriteImage("Boat3_water_frame3.png", 0.25, 0.25), 3,
                            new SpriteImage("Boat3_water_frame4.png", 0.25, 0.25), 3
                    )
            ),
            new SpriteAnimation(
                    Map.of(
                            new SpriteImage("Boat3_water_frame1 2.png", 0.25, 0.25), 3,
                            new SpriteImage("Boat3_water_frame2 2.png", 0.25, 0.25), 3,
                            new SpriteImage("Boat3_water_frame3 2.png", 0.25, 0.25), 3,
                            new SpriteImage("Boat3_water_frame4 2.png", 0.25, 0.25), 3
                    )
            ),
            new SpriteAnimation(
                    Map.of(
                            new SpriteImage("Boat3_water_frame1 3.png", 0.25, 0.25), 3,
                            new SpriteImage("Boat3_water_frame2 3.png", 0.25, 0.25), 3,
                            new SpriteImage("Boat3_water_frame3 3.png", 0.25, 0.25), 3,
                            new SpriteImage("Boat3_water_frame4 3.png", 0.25, 0.25), 3
                    )
            ),
            new SpriteAnimation(
                    Map.of(
                            new SpriteImage("Boat3_water_frame1 4.png", 0.25, 0.25), 3,
                            new SpriteImage("Boat3_water_frame2 4.png", 0.25, 0.25), 3,
                            new SpriteImage("Boat3_water_frame3 4.png", 0.25, 0.25), 3,
                            new SpriteImage("Boat3_water_frame4 4.png", 0.25, 0.25), 3
                    )
            )
    );

    SpriteAnimation spriteImage = spriteImages.get(0);

    public SquarePatrolUnit(double x, double y) {
        this.position = new VectorXYD(x, y);
        this.direction = new VectorXYD(0, 1).normalize();
    }

    @Override
    public void update() {
        if (++currentFrames >= frameLimit) {
            currentFrames = 0;

            if (++state >= 4) {
                state = 0;
            }

            this.spriteImage = this.spriteImages.get(state);
            this.direction = this.direction.rotate(this.rotation);
        }

        this.position = this.position.add(this.velocity.multiply(direction));

        currentFrames++;
    }

    @Override
    public void render(Scene scene) {
        SpriteImage spriteImage = this.spriteImage.getAndNext();

        for (int x = 0; x < spriteImage.getWidth(); x++) {
            for (int y = 0; y < spriteImage.getHeight(); y++) {
                if (spriteImage.getEmptyPixel() == spriteImage.getPixel(x, y)) {
                    continue;
                }

                scene.setPixel(
                        (int) (x + this.position.getX() - spriteImage.getWidth() / 2),
                        (int) (y + this.position.getY() - spriteImage.getHeight() / 2),
                        spriteImage.getPixel(x, y)
                );
            }
        }
    }
}
