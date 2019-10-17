/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.layer.fog;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.common.maps.MapLayerEntry;
import com.evilbird.engine.events.EventQueue;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemGroup;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.warcraft.action.construct.ConstructEvent;
import com.evilbird.warcraft.action.move.MoveEvent;
import com.evilbird.warcraft.action.produce.ProduceEvent;
import com.evilbird.warcraft.item.common.query.UnitOperations;
import com.evilbird.warcraft.item.data.player.Player;
import com.evilbird.warcraft.item.layer.LayerGroup;
import com.evilbird.warcraft.item.layer.LayerGroupCell;
import com.evilbird.warcraft.item.unit.Unit;
import com.google.gson.annotations.JsonAdapter;

import java.util.ArrayList;
import java.util.Collection;

import static com.evilbird.warcraft.item.common.query.UnitOperations.getCorporealPlayer;
import static com.evilbird.warcraft.item.common.query.UnitOperations.getViewablePlayers;
import static com.evilbird.warcraft.item.layer.LayerUtils.toCellDimensions;

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
    public void addItem(Item item) {
        super.addItem(item);
        FogCell cell = (FogCell)item;
        if (cell.isRevealed()) {
            setAdjacentTextures(cell.getLocation());
        }
    }

    @Override
    public void addItems(Collection<Item> items) {
        Collection<GridPoint2> revealed = new ArrayList<>();
        for (Item item: items) {
            super.addItem(item);

            FogCell cell = (FogCell)item;
            if (cell.isRevealed()) {
                revealed.add(cell.getLocation());
            }
        }
        setAdjacentTextures(revealed);
    }

    @Override
    protected void addCells() {
        super.addCells();
        revealItems();
    }

    @Override
    protected LayerGroupCell createCell(MapLayerEntry entry) {
        return new FogCell(entry.getPosition(), false);
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

    private void evaluateEvent(Item item) {
        Player player = UnitOperations.getPlayer(item);
        if (player.isCorporeal() || player.isViewable()) {
            revealItem(item);
        }
    }

    private void revealItems() {
        ItemRoot root = getRoot();
        revealPlayer(getCorporealPlayer(root));
        revealPlayers(getViewablePlayers(root));
    }

    private void revealPlayers(Collection<Player> players) {
        for (Player player: players) {
            revealPlayer(player);
        }
    }

    private void revealPlayer(Player player) {
        revealItems(player.getItems());
    }

    private void revealItems(Collection<Item> items) {
        for (Item item: items) {
            revealItem(item);
        }
    }

    private void revealItem(Item item) {
        if (item instanceof Unit) {
            Collection<GridPoint2> locations = getRevealedLocations((Unit)item);
            revealLocations(locations);
            setAdjacentTextures(locations);
        }
    }

    private Collection<GridPoint2> getRevealedLocations(Unit item) {
        GridPoint2 position = toCellDimensions(layer, item.getPosition());
        GridPoint2 size = toCellDimensions(layer, item.getSize());
        int radius = toCellDimensions(layer, item.getSight());
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

    private void revealLocations(Collection<GridPoint2> locations) {
        for (GridPoint2 location: locations) {
            revealLocation(location);
        }
    }

    private void revealLocation(GridPoint2 location) {
        FogCell cell = (FogCell)cells.get(location);
        cell.reveal();
    }
}
