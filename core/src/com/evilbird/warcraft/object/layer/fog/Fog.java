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
import com.evilbird.engine.events.EventQueue;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectContainer;
import com.evilbird.warcraft.action.construct.ConstructEvent;
import com.evilbird.warcraft.action.death.RemoveEvent;
import com.evilbird.warcraft.action.move.MoveEvent;
import com.evilbird.warcraft.action.produce.ProduceEvent;
import com.evilbird.warcraft.object.common.query.UnitOperations;
import com.evilbird.warcraft.object.data.player.Player;
import com.evilbird.warcraft.object.layer.LayerGroup;
import com.evilbird.warcraft.object.layer.LayerGroupCell;
import com.evilbird.warcraft.object.unit.Unit;
import com.google.gson.annotations.JsonAdapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static com.evilbird.warcraft.object.common.query.UnitOperations.getCorporealPlayer;
import static com.evilbird.warcraft.object.common.query.UnitOperations.getViewablePlayers;
import static com.evilbird.warcraft.object.layer.LayerUtils.toCellDimensions;
import static java.util.Collections.emptyList;

/**
 * Instances of this class represent fog of war: a layer of darkness that
 * recedes when units walk near and discover new territory.
 *
 * @author Blair Butterworth
 */
@JsonAdapter(FogAdapter.class)
public class Fog extends LayerGroup
{
    protected transient EventQueue events;
    protected transient Map<Identifier, Collection<GridPoint2>> revealed;

    public Fog(Skin skin, EventQueue events) {
        super(skin);
        this.events = events;
        this.revealed = new HashMap<>();
    }

    @Override
    public void addObject(GameObject object) {
        super.addObject(object);
        FogCell cell = (FogCell)object;
        if (cell.isRevealed()) {
            setAdjacentTextures(cell.getLocation());
        }
    }

    @Override
    public void addObjects(Collection<GameObject> gameObjects) {
        Collection<GridPoint2> revealed = new ArrayList<>();
        for (GameObject gameObject : gameObjects) {
            super.addObject(gameObject);

            FogCell cell = (FogCell)gameObject;
            if (cell.isRevealed()) {
                revealed.add(cell.getLocation());
            }
        }
        setAdjacentTextures(revealed);
    }

    @Override
    protected void createCells() {
        for (int x = 0; x < layer.getWidth(); ++x) {
            for (int y = 0; y < layer.getHeight(); ++y) {
                addObject(createCell(new GridPoint2(x, y)));
            }
        }
        evaluatePlayers();
    }

    @Override
    protected LayerGroupCell createCell(GridPoint2 location) {
        return new FogCell(this, style, location);
    }

    @Override
    protected LayerGroupCell createCell(GridPoint2 location, float value) {
        return new FogCell(this, style, location, value);
    }

    public boolean isRevealed(int x, int y) {
        FogCell cell = (FogCell)getCell(x, y);
        return cell != null && cell.isRevealed();
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        evaluateMoveEvents();
        evaluateConstructEvents();
        evaluateProduceEvents();
        evaluateRemoveEvents();
    }

    protected void evaluateMoveEvents() {
        for (MoveEvent moveEvent: events.getEvents(MoveEvent.class)) {
            if (moveEvent.isUpdate() || moveEvent.isFinished()) {
                evaluateObject(moveEvent.getSubject());
            }
        }
    }

    protected void evaluateConstructEvents() {
        for (ConstructEvent constructEvent: events.getEvents(ConstructEvent.class)) {
            if (constructEvent.isConstructing()) {
                evaluateObject(constructEvent.getSubject());
            }
        }
    }

    protected void evaluateProduceEvents() {
        for (ProduceEvent produceEvent : events.getEvents(ProduceEvent.class)) {
            if (produceEvent.isComplete()) {
                evaluateObject(produceEvent.getSubject());
            }
        }
    }

    protected void evaluateRemoveEvents() {
        for (RemoveEvent removeEvent : events.getEvents(RemoveEvent.class)) {
            GameObject subject = removeEvent.getSubject();
            Identifier identifier = subject.getIdentifier();
            revealed.remove(identifier);
        }
    }

    protected void evaluateObject(GameObject gameObject) {
        Player player = UnitOperations.getPlayer(gameObject);
        if (player != null && player.isCorporeal() || player.isViewable()) {
            evaluateItem(gameObject);
        }
    }

    protected void evaluatePlayers() {
        GameObjectContainer root = getRoot();
        evaluatePlayer(getCorporealPlayer(root));
        evaluatePlayers(getViewablePlayers(root));
    }

    protected void evaluatePlayers(Collection<Player> players) {
        for (Player player: players) {
            evaluatePlayer(player);
        }
    }

    protected void evaluatePlayer(Player player) {
        if (player != null) {
            evaluateItems(player.getObjects());
        }
    }

    protected void evaluateItems(Collection<GameObject> gameObjects) {
        for (GameObject gameObject : gameObjects) {
            evaluateItem(gameObject);
        }
    }

    protected void evaluateItem(GameObject gameObject) {
        if (gameObject instanceof Unit) {
            evaluateUnit((Unit)gameObject);
        }
    }

    protected void evaluateUnit(Unit unit) {
        Identifier identifier = unit.getIdentifier();
        Collection<GridPoint2> oldLocations = Maps.getOrDefault(revealed, identifier, emptyList());
        Collection<GridPoint2> newLocations = getRevealedLocations(unit);
        Collection<GridPoint2> locations = CollectionUtils.delta(newLocations, oldLocations);
        revealLocations(locations);
        setAdjacentTextures(locations);
        revealed.put(identifier, newLocations);
    }

    protected Collection<GridPoint2> getRevealedLocations(Unit unit) {
        return getRevealedLocations(unit.getPosition(), unit.getSize(), unit.getSight());
    }

    protected Collection<GridPoint2> getRevealedLocations(Vector2 worldPosition, Vector2 worldSize, int worldRadius) {
        GridPoint2 position = toCellDimensions(layer, worldPosition);
        GridPoint2 size = toCellDimensions(layer, worldSize);
        int radius = toCellDimensions(layer, worldRadius);
        int startX = Math.max(position.x - radius, 0);
        int startY = Math.max(position.y - radius, 0);
        int endX = Math.min(position.x + radius + size.x, layer.getWidth() - 1);
        int endY = Math.min(position.y + radius + size.y, layer.getHeight() - 1);
        return getRevealedLocations(startX, startY, endX, endY);
    }

    protected Collection<GridPoint2> getRevealedLocations(int startX, int startY, int endX, int endY) {
        Collection<GridPoint2> result = new ArrayList<>();
        for (int x = startX; x <= endX; x++) {
            for (int y = startY; y <= endY; y++) {
                if (! isCorner(x, y, startX, startY, endX, endY)) {
                    result.add(new GridPoint2(x, y));
                }
            }
        }
        return result;
    }

    private boolean isCorner(int x, int y, int startX, int startY, int endX, int endY) {
        return x == startX && y == startY
            || x == endX && y == startY
            || x == startX && y == endY
            || x == endX && y == endY;
    }

    protected void revealLocations(Collection<GridPoint2> locations) {
        for (GridPoint2 location: locations) {
            revealLocation(location);
        }
    }

    protected void revealLocation(GridPoint2 location) {
        FogCell cell = (FogCell)getCell(location.x, location.y);
        if (cell != null) {
            cell.reveal();
        }
    }
}
