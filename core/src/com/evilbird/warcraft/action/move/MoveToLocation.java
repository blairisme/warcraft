/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.move;

import com.evilbird.engine.action.framework.Action;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemRoot;

import javax.inject.Inject;

/**
 * Instances of this {@link Action action} move an {@link Item} from its
 * current location to a given destination, specified as world position. The
 * moving item will be animated with a movement animation, as well choose a
 * path that avoids obstacles.
 *
 * @author Blair Butterworth
 */
public class MoveToLocation extends MoveSequence
{
    @Inject
    public MoveToLocation() {
        super();
    }

    @Override
    public void setCause(UserInput cause) {
        super.setCause(cause);
        Item item = getItem();
        ItemRoot root = item.getRoot();
        setDestination(root.unproject(cause.getPosition()));
    }
}
