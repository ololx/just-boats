package io.github.ololx.just.boats.unit;

import io.github.ololx.just.boats.GameObject;
import io.github.ololx.mooncake.math.VectorXYD;
import io.github.ololx.just.boats.gfx.Scene;
import io.github.ololx.just.boats.gfx.SpriteAnimation;
import io.github.ololx.just.boats.gfx.SpriteImage;

import java.util.List;
import java.util.Map;
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

    private final VectorXYD velocity = new VectorXYD(1, 2);

    private final double rotation = Math.toRadians(-180);

    int frameLimit = 150;

    int currentFrames = 0;

    int state = 0;

    List<SpriteAnimation> spriteImages = List.of(
            new SpriteAnimation(
                    Map.of(
                            new SpriteImage("Boat1_water_frame5.png", 0.25, 0.25), 2,
                            new SpriteImage("Boat1_water_frame6.png", 0.25, 0.25), 2,
                            new SpriteImage("Boat1_water_frame7.png", 0.25, 0.25), 2,
                            new SpriteImage("Boat1_water_frame8.png", 0.25, 0.25), 2
                    )
            ),
            new SpriteAnimation(
                    Map.of(
                            new SpriteImage("Boat1_water_frame1.png", 0.25, 0.25), 2,
                            new SpriteImage("Boat1_water_frame2.png", 0.25, 0.25), 2,
                            new SpriteImage("Boat1_water_frame3.png", 0.25, 0.25), 2,
                            new SpriteImage("Boat1_water_frame4.png", 0.25, 0.25), 2
                    )
            )
    );

    SpriteAnimation spriteImage = spriteImages.get(0);

    public UpDownPatrolUnit(double x, double y) {
        this.position = new VectorXYD(x, y);
        this.direction = new VectorXYD(0, -1).normalize();
    }

    @Override
    public void update() {
        if (++currentFrames >= frameLimit) {
            currentFrames = 0;
            state++;

            this.spriteImage = this.spriteImages.get(state % 2 == 0 ? 0 : 1);
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