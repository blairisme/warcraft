/*
 * Blair Butterworth (c) 2018
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.action.common;

import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemRoot;

import javax.inject.Inject;

/**
 * Instances of this class reposition a given placeholder to the location of
 * the given input.
 *
 * @author Blair Butterworth
 */
// TODO: Should block camera when input.count > 1 (session capability)
// TODO: Repositions to center of item - should reposition with respect to where the item is touched
public class RepositionAction extends BasicAction
{
    @Inject
    public RepositionAction() {
    }

    @Override
    public boolean act(float delta) {
        Item item = getItem();
        UserInput input = getCause();
        ItemRoot root = item.getRoot();
        Vector2 position = root.unproject(input.getPosition());
        position.x -= item.getWidth() / 2;
        position.y -= item.getHeight() / 2;
        item.setPosition(position);
        return true;
    }
}
