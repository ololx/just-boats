package io.github.ololx.sleepwalker;

import io.github.ololx.mooncake.math.VectorXYD;

/**
 * project sleepwalker
 * created 08.11.2022 20:01
 *
 * @author Alexander A. Kropotin
 */
public class Player {

    public VectorXYD position;

    public Player(int x, int y) {
        this.position = new VectorXYD(x, y);
    }

    public void move() {
        this.position = this.position.add(new VectorXYD(0, 0.1));
    }
}
