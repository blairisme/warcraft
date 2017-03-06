package com.evilbird.warcraft.behaviour.user.interaction;

import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.item.Item;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class CompositeInteraction implements Interaction
{
    private Collection<Interaction> children;

    public CompositeInteraction()
    {
        children = new ArrayList<Interaction>();
    }

    public void add(Interaction child)
    {
        children.add(child);
    }

    @Override
    public boolean update(UserInput input, Item target, Item worldSelection, Item hudSelection)
    {
        for (Interaction child: children)
        {
            if (child.update(input, target, worldSelection, hudSelection))
            {
                return true;
            }
        }
        return false;
    }
}
