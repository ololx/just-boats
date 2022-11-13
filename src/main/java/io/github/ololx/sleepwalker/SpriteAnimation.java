package io.github.ololx.sleepwalker;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * project sleepwalker
 * created 13.11.2022 19:14
 *
 * @author Alexander A. Kropotin
 */
public class SpriteAnimation {

    private final List<SpriteImage> sprites = new ArrayList<>();

    private int frame = 0;

    public SpriteAnimation(Map<SpriteImage, Integer> frames) {
        Objects.requireNonNull(frames).forEach((spriteImage, framesCount) -> {
            IntStream.range(0, framesCount)
                    .mapToObj(frameIndex -> spriteImage)
                    .forEach(this.sprites::add);
        });
    }

    public SpriteImage getAndReset() {
        SpriteImage currentSprite = this.getCurrent();
        frame = 0;

        return currentSprite;
    }

    public SpriteImage getAndNext() {
        SpriteImage currentSprite = this.getCurrent();
        frame++;

        return currentSprite;
    }

    private SpriteImage getCurrent() {
        if (this.frame >= this.sprites.size()) {
            this.frame = 0;
        }

        return this.sprites.get(this.frame);
    }
}
