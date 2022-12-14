package io.github.ololx.just.boats;

import io.github.ololx.just.boats.gfx.Scene;

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

    public GameController(Scene scene, List<GameObject> units) {
        this.scene = Objects.requireNonNull(scene);
        this.units.addAll(Objects.requireNonNull(units));
    }

    public void update() {
        units.forEach(GameObject::update);
        units.forEach(unit -> {
            units.stream()
                    .filter(other -> !other.equals(unit))
                    .filter(unit::checkCollision)
                    .forEach(unit::resolveCollision);
        });
    }

    public void render() {
        scene.clear();
        units.forEach(unit -> unit.render(scene));
        scene.render();
    }
}
