/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.selector;

import com.evilbird.engine.action.framework.AbstractAction;
import com.evilbird.engine.object.GameObjectGroup;
import com.evilbird.warcraft.object.selector.Selector;
import com.evilbird.warcraft.object.unit.Unit;

import javax.inject.Inject;

import static com.evilbird.engine.action.ActionConstants.ActionComplete;

/**
 * Instances of this class stop the use of a selector, removing it from the
 * game.
 *
 * @author Blair Butterworth
 */
public class SelectorCancel extends AbstractAction
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
        Selector selector = subject.getSelector();

        if (selector != null) {
            GameObjectGroup parent = selector.getParent();
            parent.removeObject(selector);

            subject.setSelector(null);
            events.notifySelectorRemoved(subject, selector);
        }
        return ActionComplete;
    }
}
