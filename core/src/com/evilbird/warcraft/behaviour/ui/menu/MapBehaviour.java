/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.behaviour.ui.menu;

import com.evilbird.engine.behaviour.Behaviour;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.events.Event;
import com.evilbird.engine.events.EventQueue;
import com.evilbird.engine.events.Events;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectContainer;
import com.evilbird.engine.state.State;
import com.evilbird.warcraft.action.common.create.CreateEvent;
import com.evilbird.warcraft.action.death.RemoveEvent;
import com.evilbird.warcraft.action.gather.GatherEvent;
import com.evilbird.warcraft.action.move.MoveEvent;
import com.evilbird.warcraft.object.display.components.UserInterfaceComponent;
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
public class MapBehaviour implements Behaviour
{
    private boolean loaded;
    private Events events;
    private Collection<MapPane> mapPanes;

    @Inject
    public MapBehaviour(EventQueue events) {
        this.events = events;
    }

    @Override
    public void update(State state, List<UserInput> inputs, float time) {
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
        updateMap(MoveEvent.class);
        updateMap(CreateEvent.class);
        updateMap(RemoveEvent.class);
        updateMapGatherEvents();
    }

    private void updateMap(Class<? extends Event> type) {
        for (Event event: events.getEvents(type)) {
            invalidate(event.getSubject());
        }
    }

    private void updateMapGatherEvents() {
        for (Event event: events.getEvents(GatherEvent.class)) {
            GatherEvent gatherEvent = (GatherEvent)event;
            invalidate(gatherEvent.getSubject());
            invalidate(gatherEvent.getRecipient());
        }
    }

    private void initialize(State state) {
        for (MapPane mapPane: mapPanes) {
            if (!mapPane.initialized()) {
                GameObjectContainer world = state.getWorld();
                mapPane.initialize(world);
            }
        }
    }

    private void invalidate(GameObject object) {
        for (MapPane mapPane: mapPanes) {
            mapPane.invalidate(object);
        }
    }
}
