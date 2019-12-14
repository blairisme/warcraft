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
import com.evilbird.warcraft.action.move.MoveEvent;
import com.evilbird.warcraft.object.display.HudControl;
import com.evilbird.warcraft.object.display.control.minimap.MinimapPane;

import javax.inject.Inject;
import java.util.List;

import static com.evilbird.engine.object.utility.GameObjectPredicates.itemWithId;

/**
 * A {@link Behaviour} implementation that updates the in-game minimap.
 *
 * @author Blair Butterworth
 */
public class MinimapBehaviour implements Behaviour
{
    private Events events;
    private MinimapPane mapPane;
    private boolean loaded;

    @Inject
    public MinimapBehaviour(EventQueue events) {
        this.events = events;
    }

    @Override
    public void update(State state, List<UserInput> inputs, float time) {
        if (! loaded) {
            loadMap(state);
        } else if (mapPane != null) {
            updateMap(state);
        }
    }

    public void loadMap(State state) {
        GameObjectContainer hud = state.getHud();
        mapPane = (MinimapPane)hud.find(itemWithId(HudControl.MinimapPane));
        loaded = true;
    }

    private void updateMap(State state) {
        generateMap(state);
        updateMap(MoveEvent.class);
        updateMap(CreateEvent.class);
        updateMap(RemoveEvent.class);
    }

    private void updateMap(Class<? extends Event> type) {
        for (Event event: events.getEvents(type)) {
            GameObject subject = event.getSubject();
            if (subject != null) {
                mapPane.update(subject.getPosition(), subject.getSize());
            }
        }
    }

    private void generateMap(State state) {
        if (! mapPane.initialized()) {
            GameObjectContainer world = state.getWorld();
            mapPane.initialize(world);
        }
    }
}
