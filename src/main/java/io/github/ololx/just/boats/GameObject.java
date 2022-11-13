package io.github.ololx.just.boats;

import io.github.ololx.just.boats.gfx.Scene;

/**
 * project sleepwalker
 * created 08.11.2022 20:00
 *
 * @author Alexander A. Kropotin
 */
public interface GameObject {

    public void update();

    public void render(Scene scene);
}
