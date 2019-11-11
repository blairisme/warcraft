/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.object.utility;

import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.object.GameObject;

import java.util.Comparator;

/**
 * Instances of this class define commonly used {@link Comparator Comparators}
 * that operate on {@link GameObject Items}.
 *
 * @author Blair Butterworth
 */
public class GameObjectComparators
{
    private GameObjectComparators() {
    }

    public static Comparator<GameObject> closestItem(GameObject target) {
        return new ClosestItem(target);
    }

    public static class ClosestItem implements Comparator<GameObject> {
        private Vector2 target;

        public ClosestItem(GameObject gameObject) {
            this.target = gameObject.getPosition();
        }

        @Override
        public int compare(GameObject var1, GameObject var2) {
            Vector2 position1 = var1.getPosition();
            Vector2 position2 = var2.getPosition();

            float distance1 = target.dst2(position1);
            float distance2 = target.dst2(position2);

            return Float.compare(distance1, distance2);
        }
    }
}
