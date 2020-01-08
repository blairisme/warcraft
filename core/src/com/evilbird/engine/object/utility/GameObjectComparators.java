/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
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
    /**
     * Disable construction of this static helper class.
     */
    private GameObjectComparators() {
    }

    /**
     * Returns a {@link Comparator} that sorts {@link GameObject GameObjects}
     * by proximity to the given target.
     */
    public static Comparator<GameObject> closestItem(GameObject target) {
        return new ClosestItem(target);
    }

    private static class ClosestItem implements Comparator<GameObject> {
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
