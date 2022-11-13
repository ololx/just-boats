package io.github.ololx.just.boats;

import io.github.ololx.just.boats.gfx.Scene;
import io.github.ololx.just.boats.unit.RightLeftPatrolUnit;
import io.github.ololx.just.boats.unit.SquarePatrolUnit;
import io.github.ololx.just.boats.unit.UpDownPatrolUnit;

import java.util.ArrayList;

/**
 * project sleepwalker
 * created 08.11.2022 19:28
 *
 * @author Alexander A. Kropotin
 */
public class JustBoats {

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
                    add(new RightLeftPatrolUnit(400, 120));
                    add(new UpDownPatrolUnit(300, 300));
                }}
        );
        Game game = new Game(gameLoop);
        game.start();
    }
}