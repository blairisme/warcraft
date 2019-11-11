/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.selector;

import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectGroup;
import com.evilbird.warcraft.item.unit.Unit;

import javax.inject.Inject;

import static com.evilbird.engine.action.ActionConstants.ActionComplete;

/**
 * Instances of this class stop the use of a selector, removing it from the
 * game.
 *
 * @author Blair Butterworth
 */
public class SelectorCancel extends BasicAction
{
    private SelectorEvents events;

    @Inject
    public SelectorCancel(SelectorEvents events) {
        this.events = events;
        setIdentifier(SelectorActions.HideSelector);
    }

    @Override
    public boolean act(float time) {
        Unit subject = (Unit) getSubject();
        GameObject selector = subject.getAssociatedItem();

        GameObjectGroup parent = selector.getParent();
        parent.removeObject(selector);

        subject.setAssociatedItem(null);
        events.notifySelectorRemoved(subject, selector);

        return ActionComplete;
    }
}
