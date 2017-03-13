package com.evilbird.engine.item;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public interface ItemFactory
{
    void load();

    Item newItem(ItemType type);
}
