package io.github.ololx.sleepwalker;

/**
 * project sleepwalker
 * created 08.11.2022 19:28
 *
 * @author Alexander A. Kropotin
 */
public class Sleepwalker {

    public static void main(String[] args) {
        Game game = new Game(new GameLoop());
        game.start();
    }
}
