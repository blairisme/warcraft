package com.evilbird.engine.item;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class ItemComposite extends Item
{
    private Collection<Item> children;

    public ItemComposite()
    {
        this.children = new ArrayList<Item>();
    }

}
