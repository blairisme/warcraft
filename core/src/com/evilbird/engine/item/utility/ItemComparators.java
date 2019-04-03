/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.item.utility;

import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.common.function.Comparator;
import com.evilbird.engine.item.Item;

public class ItemComparators
{
    private ItemComparators() {
    }

    public static Comparator<Item> closestItem(Item target) {
        return new ClosestItem(target);
    }

    public static class ClosestItem implements Comparator<Item> {
        private Vector2 target;

        public ClosestItem(Item item) {
            this.target = item.getPosition();
        }

        @Override
        public int compare(Item var1, Item var2) {
            Vector2 position1 = var1.getPosition();
            Vector2 position2 = var2.getPosition();

            float distance1 = target.dst2(position1);
            float distance2 = target.dst2(position2);

            return Float.compare(distance1, distance2);
        }
    }
}
