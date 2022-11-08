package io.github.ololx.sleepwalker;

/**
 * project sleepwalker
 * created 08.11.2022 22:18
 *
 * @author Alexander A. Kropotin
 */
public class GameController {

    Player player = new Player(1, 1);

    Scene scene;

    GameController(Scene scene) {
        this.scene = scene;
    }

    public void update() {
        player.update();
    }

    public void render() {
        player.render(scene);
        scene.render();
    }
}
