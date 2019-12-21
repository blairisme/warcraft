/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.selector;

import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.framework.BasicAction;
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
public class SelectorCreate extends BasicAction
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
