package io.github.ololx.just.boats;

import java.util.Objects;

/**
 * project sleepwalker
 * created 08.11.2022 19:30
 *
 * @author Alexander A. Kropotin
 */
public class Game {

    private final GameLoop gameLoop;

    public Game(GameLoop gameLoop) {
        this.gameLoop = Objects.requireNonNull(gameLoop);
    }

    public void start() {
        this.gameLoop.run();
    }

    public void pause() {
        this.gameLoop.pause();
    }

    public void stop() {
        this.gameLoop.stop();
    }
}
