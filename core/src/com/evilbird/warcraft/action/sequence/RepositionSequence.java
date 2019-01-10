/*
 * Blair Butterworth (c) 2018
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.sequence;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.engine.action.common.PositionAction;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.warcraft.action.ActionProvider;

import javax.inject.Inject;

/**
 * Instances of this class reposition a given item to the location of the given
 * input.
 *
 * @author Blair Butterworth
 */
// TODO: Inefficient
// TODO: Specific to building sites
// TODO: Should block camera when input.count > 1 (session capability)
// TODO: Repositions to center of item - should reposition with respect to where the item is touched
public class RepositionSequence implements ActionProvider
{
    @Inject
    public RepositionSequence()
    {
    }

    @Override
    public Action get(ActionIdentifier action, Item item, Item target, UserInput input)
    {
        ItemRoot root = item.getRoot();
        Vector2 position = root.unproject(input.getPosition());
        position.x -= item.getWidth() / 2;
        position.y -= item.getHeight() / 2;
        return new PositionAction(item, position);
    }
}
