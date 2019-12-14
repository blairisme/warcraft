/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.layer.fog;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.common.maps.MapLayerEntry;
import com.evilbird.engine.events.EventQueue;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectContainer;
import com.evilbird.warcraft.action.construct.ConstructEvent;
import com.evilbird.warcraft.action.move.MoveEvent;
import com.evilbird.warcraft.action.produce.ProduceEvent;
import com.evilbird.warcraft.object.common.query.UnitOperations;
import com.evilbird.warcraft.object.data.player.Player;
import com.evilbird.warcraft.object.layer.LayerCell;
import com.evilbird.warcraft.object.layer.LayerGroup;
import com.evilbird.warcraft.object.unit.Unit;
import com.google.gson.annotations.JsonAdapter;

import java.util.ArrayList;
import java.util.Collection;

import static com.evilbird.warcraft.object.common.query.UnitOperations.getCorporealPlayer;
import static com.evilbird.warcraft.object.common.query.UnitOperations.getViewablePlayers;
import static com.evilbird.warcraft.object.layer.LayerUtils.toCellDimensions;

/**
 * Instances of this class represent fog of war: a layer of darkness that
 * recedes when units walk near and discover new territory.
 *
 * @author Blair Butterworth
 */
@JsonAdapter(FogAdapter.class)
public class Fog extends LayerGroup
{
    private transient EventQueue events;

    public Fog(Skin skin, EventQueue events) {
        super(skin);
        this.events = events;
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

            FogCell cell = (FogCell) gameObject;
            if (cell.isRevealed()) {
                revealed.add(cell.getLocation());
            }
        }
        setAdjacentTextures(revealed);
    }

    @Override
    protected void addCells() {
        super.addCells();
        revealPlayers();
    }

    @Override
    protected LayerCell createCell(MapLayerEntry entry) {
        return new FogCell(entry.getPosition(), false);
    }

    public boolean isRevealed(int x, int y) {
        return isRevealed(new GridPoint2(x, y));
    }

    public boolean isRevealed(GridPoint2 location) {
        FogCell cell = (FogCell)cells.get(location);
        return cell != null && cell.isRevealed();
    }

    @Override
    public void setLayer(TiledMapTileLayer layer) {
        super.setLayer(layer);
        for (int x = 0; x < layer.getWidth(); ++x) {
            for (int y = 0; y < layer.getHeight(); ++y) {
                layer.setCell(x, y, style.full);
            }
        }
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        evaluateEvents();
    }

    private void evaluateEvents() {
        for (MoveEvent moveEvent: events.getEvents(MoveEvent.class)) {
            evaluateEvent(moveEvent.getSubject());
        }
        for (ConstructEvent constructEvent: events.getEvents(ConstructEvent.class)) {
            if (constructEvent.isConstructing()) {
                evaluateEvent(constructEvent.getSubject());
            }
        }
        for (ProduceEvent produceEvent : events.getEvents(ProduceEvent.class)) {
            if (produceEvent.isComplete()) {
                evaluateEvent(produceEvent.getSubject());
            }
        }
    }

    private void evaluateEvent(GameObject gameObject) {
        Player player = UnitOperations.getPlayer(gameObject);
        if (player.isCorporeal() || player.isViewable()) {
            revealItem(gameObject);
        }
    }

    private void revealPlayers() {
        GameObjectContainer root = getRoot();
        revealPlayer(getCorporealPlayer(root));
        revealPlayers(getViewablePlayers(root));
    }

    public void revealPlayers(Collection<Player> players) {
        for (Player player: players) {
            revealPlayer(player);
        }
    }

    public void revealPlayer(Player player) {
        revealItems(player.getObjects());
    }

    public void revealItems(Collection<GameObject> gameObjects) {
        for (GameObject gameObject : gameObjects) {
            revealItem(gameObject);
        }
    }

    public void revealItem(GameObject gameObject) {
        if (gameObject instanceof Unit) {
            Unit unit = (Unit) gameObject;
            revealItem(unit, unit.getSight());
        }
    }

    public void revealItem(GameObject gameObject, int radius) {
        revealLocations(gameObject.getPosition(), gameObject.getSize(), radius);
    }

    public void revealLocations(Vector2 location, Vector2 size, int radius) {
        Collection<GridPoint2> locations = getRevealedLocations(location, size, radius);
        revealLocations(locations);
        setAdjacentTextures(locations);
    }

    private void revealLocations(Collection<GridPoint2> locations) {
        for (GridPoint2 location: locations) {
            revealLocation(location);
        }
    }

    private void revealLocation(GridPoint2 location) {
        FogCell cell = (FogCell)cells.get(location);
        cell.reveal();
    }

    private Collection<GridPoint2> getRevealedLocations(Vector2 worldPosition, Vector2 worldSize, int worldRadius) {
        GridPoint2 position = toCellDimensions(layer, worldPosition);
        GridPoint2 size = toCellDimensions(layer, worldSize);
        int radius = toCellDimensions(layer, worldRadius);
        int startX = Math.max(position.x - radius, 0);
        int startY = Math.max(position.y - radius, 0);
        int endX = Math.min(position.x + radius + size.x, layer.getWidth() - 1);
        int endY = Math.min(position.y + radius + size.y, layer.getHeight() - 1);
        return getRevealedLocations(startX, startY, endX, endY);
    }

    private Collection<GridPoint2> getRevealedLocations(int startX, int startY, int endX, int endY) {
        Collection<GridPoint2> result = new ArrayList<>();
        for (int x = startX; x <= endX; x++){
            for (int y = startY; y <= endY; y++){
                result.add(new GridPoint2(x, y));
            }
        }
        return result;
    }
}
