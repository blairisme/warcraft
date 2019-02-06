/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.move;

import com.evilbird.engine.item.Item;

import javax.inject.Inject;

public class MoveToItem extends MoveSequence
{
    @Inject
    public MoveToItem() {
        super();
    }

    @Override
    public void setTarget(Item target) {
        super.setTarget(target);
        setDestination(target);
    }
}
