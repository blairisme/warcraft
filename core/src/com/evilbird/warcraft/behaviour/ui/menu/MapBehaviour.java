/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ui.menu;

import com.evilbird.engine.behaviour.Behaviour;
import com.evilbird.engine.behaviour.BehaviourElement;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.events.Event;
import com.evilbird.engine.events.EventQueue;
import com.evilbird.engine.events.Events;
import com.evilbird.engine.events.RecipientEvent;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectContainer;
import com.evilbird.engine.state.State;
import com.evilbird.warcraft.action.common.create.CreateEvent;
import com.evilbird.warcraft.action.construct.ConstructEvent;
import com.evilbird.warcraft.action.death.RemoveEvent;
import com.evilbird.warcraft.action.gather.GatherEvent;
import com.evilbird.warcraft.action.move.MoveEvent;
import com.evilbird.warcraft.object.display.components.map.MapPane;

import javax.inject.Inject;
import java.util.Collection;
import java.util.List;

import static com.evilbird.engine.object.utility.GameObjectPredicates.itemWithId;
import static com.evilbird.warcraft.object.display.components.UserInterfaceComponent.MinimapPane;

/**
 * A {@link Behaviour} implementation that updates the in-game minimap.
 *
 * @author Blair Butterworth
 */
public class MapBehaviour implements BehaviourElement
{
    private boolean loaded;
    private Events events;
    private Collection<MapPane> mapPanes;

    @Inject
    public MapBehaviour(EventQueue events) {
        this.events = events;
    }

    @Override
    public void apply(State state, List<UserInput> inputs, float time) {
        if (! loaded) {
            loadMap(state);
        } else {
            updateMap(state);
        }
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public void loadMap(State state) {
        GameObjectContainer hud = state.getHud();
        mapPanes = (Collection)hud.findAll(itemWithId(MinimapPane));
        loaded = true;
    }

    private void updateMap(State state) {
        initialize(state);
        invalidateSubject(MoveEvent.class);
        invalidateSubject(CreateEvent.class);
        invalidateSubject(RemoveEvent.class);
        invalidateRecipient(ConstructEvent.class);
        invalidateRecipient(GatherEvent.class);
    }

    private void initialize(State state) {
        for (MapPane mapPane: mapPanes) {
            if (!mapPane.initialized()) {
                GameObjectContainer world = state.getWorld();
                mapPane.initialize(world);
            }
        }
    }

    private void invalidateSubject(Class<? extends Event> type) {
        for (Event event: events.getEvents(type)) {
            invalidate(event.getSubject());
        }
    }

    private void invalidateRecipient(Class<? extends RecipientEvent> type) {
        for (Event event: events.getEvents(type)) {
            RecipientEvent recipientEvent = (RecipientEvent)event;
            invalidate(recipientEvent.getSubject());
            invalidate(recipientEvent.getRecipient());
        }
    }

    private void invalidate(GameObject object) {
        if (object != null) {
            for (MapPane mapPane : mapPanes) {
                mapPane.invalidate(object);
            }
        }
    }
}
