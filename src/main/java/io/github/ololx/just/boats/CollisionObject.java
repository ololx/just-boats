package io.github.ololx.just.boats;

import java.awt.*;

/**
 * project just-boats
 * created 17/11/2022 20:32
 *
 * @author Alexander A. Kropotin
 */
public interface CollisionObject {

    public Rectangle getAABB();

    public boolean checkCollision(CollisionObject collisionObject);

    public void resolveCollision(CollisionObject collisionObject);
}
