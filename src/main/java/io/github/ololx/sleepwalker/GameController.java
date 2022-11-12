package io.github.ololx.sleepwalker;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * project sleepwalker
 * created 08.11.2022 22:18
 *
 * @author Alexander A. Kropotin
 */
public class GameController {

    final List<GameObject> units = new ArrayList<>();

    Scene scene;

    GameController(Scene scene) {
        this.scene = Objects.requireNonNull(scene);
    }

    public GameController(Scene scene, List<StupidSquare> units) {
        this.scene = Objects.requireNonNull(scene);
        this.units.addAll(Objects.requireNonNull(units));
    }

    public void update() {
        units.forEach(GameObject::update);
    }

    public void render() {
        scene.clear();
        units.forEach(unit -> unit.render(scene));
        scene.render();
    }
}
