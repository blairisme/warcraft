/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.common.associate;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.unit.Unit;

import javax.inject.Inject;

/**
 * Instances of this {@link Action} associate the subject and target of the
 * action with each other.
 *
 * @author Blair Butterworth
 */
public class AssociateAction extends BasicAction
{
    private boolean associate;

    @Inject
    public AssociateAction() {
        associate = true;
    }

    @Override
    public boolean act(float delta) {
        Item subject = getItem();
        Item target = getTarget();

        setAssociated(subject, associate ? target : null);
        setAssociated(target, associate ? subject : null);

        return true;
    }

    private void setAssociated(Item item, Item associated) {
        if (item instanceof Unit) {
            Unit unit = (Unit)item;
            unit.setAssociatedItem(associated);
        }
    }
}
