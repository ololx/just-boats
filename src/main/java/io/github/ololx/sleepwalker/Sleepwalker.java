package io.github.ololx.sleepwalker;

import javax.swing.*;
import java.awt.*;

/**
 * project sleepwalker
 * created 08.11.2022 19:28
 *
 * @author Alexander A. Kropotin
 */
public class Sleepwalker {

    public static void main(String[] args) {
        Scene scene = new Scene();
        scene.init();

        GameLoop gameLoop = new GameLoop();
        gameLoop.controller = new GameController(scene);
        Game game = new Game(gameLoop);
        game.start();
    }
}