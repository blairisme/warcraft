package com.evilbird.engine.item;

import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.common.function.Comparator;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class ItemComparators
{
    public static Comparator<Item> closestItem(Item target)
    {
        return new ClosestItem(target); //TODO: Singleton?
    }

    public static class ClosestItem implements Comparator<Item>
    {
        private Vector2 target;

        public ClosestItem(Item item)
        {
            this.target = item.getPosition();
        }

        @Override
        public int compare(Item var1, Item var2)
        {
            Vector2 position1 = var1.getPosition();
            Vector2 position2 = var2.getPosition();

            float distance1 = target.dst(position1);
            float distance2 = target.dst(position2);

            return Float.compare(distance1, distance2);
        }
    }
}
