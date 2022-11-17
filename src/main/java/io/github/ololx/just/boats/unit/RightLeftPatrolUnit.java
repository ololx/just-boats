package io.github.ololx.just.boats.unit;

import io.github.ololx.just.boats.CollisionObject;
import io.github.ololx.just.boats.GameObject;
import io.github.ololx.mooncake.math.VectorXYD;
import io.github.ololx.just.boats.gfx.Scene;
import io.github.ololx.just.boats.gfx.SpriteAnimation;
import io.github.ololx.just.boats.gfx.SpriteImage;

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
public class RightLeftPatrolUnit implements GameObject, CollisionObject {

    private final Logger log = Logger.getLogger(this.getClass().getName());

    private VectorXYD position;

    private VectorXYD direction;

    private final VectorXYD velocity = new VectorXYD(4, 4);

    private final double rotation = Math.toRadians(-180);

    int frameLimit = 300;

    int currentFrames = 0;

    int state = 0;

    List<SpriteAnimation> spriteImages = List.of(
            new SpriteAnimation(
                    Map.of(
                            new SpriteImage("Boat4_water_frame1.png", 0.25, 0.25), 1,
                            new SpriteImage("Boat4_water_frame2.png", 0.25, 0.25), 1,
                            new SpriteImage("Boat4_water_frame3.png", 0.25, 0.25), 1,
                            new SpriteImage("Boat4_water_frame4.png", 0.25, 0.25), 1
                    )
            ),
            new SpriteAnimation(
                    Map.of(
                            new SpriteImage("Boat4_water_frame5.png", 0.25, 0.25), 1,
                            new SpriteImage("Boat4_water_frame6.png", 0.25, 0.25), 1,
                            new SpriteImage("Boat4_water_frame7.png", 0.25, 0.25), 1,
                            new SpriteImage("Boat4_water_frame8.png", 0.25, 0.25), 1
                    )
            ),
            new SpriteAnimation(
                    Map.of(
                            new SpriteImage("Boat1_water_frame_boom_1.png", 0.25, 0.25), 1,
                            new SpriteImage("Boat1_water_frame_boom_2.png", 0.25, 0.25), 2,
                            new SpriteImage("Boat1_water_frame_boom_3.png", 0.25, 0.25), 2
                    )
            )
    );

    SpriteAnimation spriteImage = spriteImages.get(0);

    public RightLeftPatrolUnit(double x, double y) {
        this.position = new VectorXYD(x, y);
        this.direction = new VectorXYD(1, 0).normalize();
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

    @Override
    public Rectangle getAABB() {
        double cx = this.position.getX();
        double cy = this.position.getY();
        int x = (int) (cx - 10);
        int y = (int) (cy + 10);
        int width = 20, height = 20;

        return new Rectangle(x, y, width, height);
    }

    @Override
    public boolean checkCollision(CollisionObject collisionObject) {
        return this.getAABB().intersects(collisionObject.getAABB());
    }

    @Override
    public void resolveCollision(CollisionObject collisionObject) {
        spriteImage = spriteImages.get(2);
    }
}
