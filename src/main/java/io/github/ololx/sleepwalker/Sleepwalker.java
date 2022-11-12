package io.github.ololx.sleepwalker;

import javax.swing.*;
import java.util.List;
import java.util.Random;
import java.util.function.DoubleFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

        gameLoop.controller = new GameController(
                scene,
                List.of(
                        new StupidSquare(50, 50),
                        new StupidSquare(250, 50),
                        new StupidSquare(500, 50),
                        new StupidSquare(100, 150),
                        new StupidSquare(300, 250),
                        new StupidSquare(450, 450)
                )
        );
        Game game = new Game(gameLoop);
        game.start();
    }
}