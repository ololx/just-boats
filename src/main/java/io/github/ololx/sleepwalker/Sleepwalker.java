package io.github.ololx.sleepwalker;

import java.util.ArrayList;

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
                new ArrayList<GameObject>() {{
                    add(new SquarePatrolUnit(50, 50));
                    add(new RightLeftPatrolUnit(250, 50));
                    add(new SquarePatrolUnit(500, 50));
                    add(new UpDownPatrolUnit(100, 150));
                    add(new SquarePatrolUnit(300, 250));
                    add(new RightLeftPatrolUnit(150, 300));
                    add(new RoundPatrolUnit(400, 120));
                    add(new UpDownPatrolUnit(450, 450));
                }}
        );
        Game game = new Game(gameLoop);
        game.start();
    }
}