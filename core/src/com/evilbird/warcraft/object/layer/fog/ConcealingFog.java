/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.layer.fog;

import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.common.collection.CollectionUtils;
import com.evilbird.engine.common.collection.Maps;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.common.maps.MapLayerEntry;
import com.evilbird.engine.events.EventQueue;
import com.evilbird.engine.object.GameObject;
import com.evilbird.warcraft.action.death.RemoveEvent;
import com.evilbird.warcraft.object.layer.LayerCell;
import com.google.gson.annotations.JsonAdapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * A {@link Fog} specialization that re-conceals itself when units move out of
 * sight.
 *
 * @author Blair Butterworth
 */
@JsonAdapter(ConcealingFogAdapter.class)
public class ConcealingFog extends Fog
{
    private transient Map<Identifier, Collection<GridPoint2>> revealed;

    public ConcealingFog(Skin skin, EventQueue events) {
        super(skin, events);
        this.revealed = new HashMap<>();
    }

    @Override
    protected LayerCell createCell(MapLayerEntry entry) {
        return new ConcealingFogCell(entry.getPosition(), false);
    }

    @Override
    protected void evaluateEvents() {
        for (RemoveEvent removeEvent : events.getEvents(RemoveEvent.class)) {
            GameObject subject = removeEvent.getSubject();
            Identifier identifier = subject.getIdentifier();

            evaluateEvent(subject);
            revealed.remove(identifier);
        }
    }

    @Override
    protected void evaluateItem(GameObject gameObject, int radius) {
        Identifier identifier = gameObject.getIdentifier();
        Vector2 location = gameObject.getPosition();
        Vector2 size = gameObject.getSize();

        Collection<GridPoint2> oldLocations = Maps.getOrDefault(revealed, identifier, Collections.emptyList());
        Collection<GridPoint2> newLocations = getRevealedLocations(location, size, radius);

        Collection<GridPoint2> concealedLocations = CollectionUtils.delta(oldLocations, newLocations);
        Collection<GridPoint2> updatedLocations1 = concealLocations(identifier, concealedLocations);

        Collection<GridPoint2> revealedLocations = CollectionUtils.delta(newLocations, oldLocations);
        Collection<GridPoint2> updatedLocations2 = revealLocations(identifier, revealedLocations);

        revealed.put(identifier, newLocations);
    }

    protected Collection<GridPoint2> concealLocations(Identifier identifier, Collection<GridPoint2> locations) {
        Collection<GridPoint2> updated = new ArrayList<>();
        for (GridPoint2 location: locations) {
            if (concealLocation(identifier, location)) {
                updated.add(location);
            }
        }
        return updated;
    }

    protected boolean concealLocation(Identifier identifier, GridPoint2 location) {
        ConcealingFogCell cell = (ConcealingFogCell)cells.get(location);
        cell.removeOccupant(identifier);

        if (cell.isUnoccupied()) {
            cell.conceal();
            return true;
        }
        return false;
    }

    protected Collection<GridPoint2> revealLocations(Identifier identifier, Collection<GridPoint2> locations) {
        Collection<GridPoint2> updated = new ArrayList<>();
        for (GridPoint2 location: locations) {
            if (revealLocation(identifier, location)) {
                updated.add(location);
            }
        }
        return updated;
    }

    protected boolean revealLocation(Identifier identifier, GridPoint2 location) {
        ConcealingFogCell cell = (ConcealingFogCell)cells.get(location);
        boolean result = cell.isUnoccupied();

        cell.addOccupant(identifier);
        cell.reveal();

        return result;
    }
}
