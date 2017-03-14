package com.evilbird.engine.item;

import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class ItemReference extends Item
{
    private Item reference;

    public ItemReference(Item reference)
    {
        this.reference = reference;
    }

    @Override
    protected Actor initializeDelegate()
    {
        return reference.delegate;
    }
}
