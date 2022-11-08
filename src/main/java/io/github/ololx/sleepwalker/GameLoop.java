package io.github.ololx.sleepwalker;

import java.util.Random;
import java.util.logging.Logger;

/**
 * project sleepwalker
 * created 08.11.2022 19:32
 *
 * @author Alexander A. Kropotin
 */
public class GameLoop {

    private final Logger log = Logger.getLogger(this.getClass().getName());

    private volatile boolean active;

    private Thread gameThread;

    private int xPosition;

    public void run() {
        active = true;
        gameThread = new Thread(this::processGameLoop);
        gameThread.start();
    }

    public void stop() {
        active = false;
        gameThread.interrupt();
    }

    public void pause() {
        active = false;
    }

    public void idle() {
        try {
            var lag = new Random().nextInt(200) + 50;
            Thread.sleep(lag);
        } catch (InterruptedException e) {
            log.severe(e.getMessage());
        }
    }

    public void update() {
        xPosition++;
    }

    public void render() {
        log.info("Current position: " + xPosition);
    }

    public void processGameLoop() {
        while (active) {
            update();
            render();
            idle();
        }
    }
}
