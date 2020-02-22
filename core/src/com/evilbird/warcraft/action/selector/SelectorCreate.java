/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.selector;

import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.framework.AbstractAction;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectContainer;
import com.evilbird.engine.object.GameObjectFactory;
import com.evilbird.engine.object.spatial.GameObjectGraph;
import com.evilbird.warcraft.object.selector.Selector;
import com.evilbird.warcraft.object.selector.SelectorType;
import com.evilbird.warcraft.object.unit.Unit;

import javax.inject.Inject;

import static com.evilbird.engine.action.ActionConstants.ActionComplete;
import static com.evilbird.engine.object.utility.GameObjectOperations.getScreenCenter;

/**
 * An {@link Action} that adds a selector to the game world.
 *
 * @author Blair Butterworth
 */
public class SelectorCreate extends AbstractAction
{
    private GameObjectFactory factory;
    private SelectorEvents events;

    @Inject
    public SelectorCreate(SelectorEvents events, GameObjectFactory factory) {
        this.events = events;
        this.factory = factory;
    }

    @Override
    public boolean act(float time) {
        Selector selector = (Selector)factory.get(selectorType());

        Unit subject = (Unit)getSubject();
        selector.setPosition(selectorPosition(subject));

        GameObjectContainer container = subject.getRoot();
        container.addObject(selector);

        subject.setSelector(selector);
        events.notifySelectorAdded(subject, selector);

        return ActionComplete;
    }

    private SelectorType selectorType() {
        SelectorActions action = (SelectorActions)getIdentifier();
        return action.getSelector();
    }

    private Vector2 selectorPosition(GameObject builder) {
        GameObjectContainer container = builder.getRoot();
        GameObjectGraph graph = container.getSpatialGraph();
        Vector2 tileSize = graph.getNodeSize();

        Vector2 screenCenter = getScreenCenter(builder);
        screenCenter.x = Math.round(screenCenter.x/tileSize.x) * tileSize.x;
        screenCenter.y = Math.round(screenCenter.y/tileSize.y) * tileSize.y;
        return screenCenter;
    }
}
