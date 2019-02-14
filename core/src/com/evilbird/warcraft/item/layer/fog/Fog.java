/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.layer.fog;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.evilbird.engine.action.events.EventQueue;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemGroup;
import com.evilbird.engine.item.specialized.layer.Layer;
import com.evilbird.warcraft.action.move.MoveEvent;
import com.evilbird.warcraft.item.common.query.UnitOperations;
import com.evilbird.warcraft.item.layer.LayerType;
import com.evilbird.warcraft.item.unit.Unit;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Instances of this class represent fog of war: a layer of darkness that
 * recedes when units walk near and discover new territory.
 *
 * @author Blair Butterworth
 */
//TODO: Bug: thin peninsulas rendered incorrectly. Fix: don't render them.
//TODO: Bug: revealing doesn't include item size, only position. Fix: use logic from ItemGraph.
public class Fog extends Layer
{
    private EventQueue events;
    private FogTileSet tileSet;
    private boolean initialized;
    private int xMax;
    private int yMax;

    public Fog(FogTileSet tileSet, EventQueue events) {
        this.events = events;
        this.tileSet = tileSet;
        this.initialized = false;
        setType(LayerType.OpaqueFog);
        setTouchable(Touchable.disabled);
    }

    @Override
    public void setLayer(TiledMapTileLayer layer) {
        super.setLayer(new TiledMapTileLayer(
            layer.getWidth(),
            layer.getHeight(),
            (int)layer.getTileWidth(),
            (int)layer.getTileHeight()));
        xMax = layer.getWidth() - 1;
        yMax = layer.getHeight() - 1;
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        if (!initialized){
            initialized = true;
            initialize();
        }
        else {
            update();
        }
    }

    private void initialize() {
        conceal();
        ItemGroup player = UnitOperations.getHumanPlayer(getRoot());
        revealItems(player.getItems());
    }

    private void update() {
        for (MoveEvent moveEvent: events.getEvents(MoveEvent.class)) {
            revealItem(moveEvent.getSubject());
        }
    }

    private void conceal(){
        for (int x = 0; x < layer.getWidth(); ++x){
            for (int y = 0; y < layer.getHeight(); ++y){
                layer.setCell(x, y, tileSet.full);
            }
        }
    }

    private void revealItems(Collection<Item> items) {
        for (Item item: items){
            revealItem(item);
        }
    }

    private void revealItem(Item item) {
        Collection<Pair<Integer, Integer>> revealedCells = getRevealedCells(item);
        for (Pair<Integer, Integer> revealedGrid: revealedCells){
            layer.setCell(revealedGrid.getLeft(), revealedGrid.getRight(), null);
        }
        for (Pair<Integer, Integer> revealedGrid: revealedCells){
            updateCellEdge(revealedGrid.getLeft(), revealedGrid.getRight());
        }
    }

    private Collection<Pair<Integer, Integer>> getRevealedCells(Item item) {
        Collection<Pair<Integer, Integer>> result = new ArrayList<>();

        Vector2 position = item.getPosition();
        int itemX = position.x != 0 ? Math.round(position.x / 32) : 0;
        int itemY = position.y != 0 ? Math.round(position.y / 32) : 0;
        int width = layer.getWidth();
        int height = layer.getHeight();
        int radius = getSight(item);

        for (int x = itemX - radius; x <= itemX + radius; x++){
            for (int y = itemY - radius; y <= itemY + radius; y++){
                if (x >= 0 && y >= 0 && x < width && y < height){
                    result.add(Pair.of(x, y));
                }
            }
        }
        return result;
    }

    private int getSight(Item item){
        if (item instanceof Unit){
            Unit unit = (Unit)item;
            return unit.getSight() / 32;
        }
        return 0;
    }

    private void updateCellEdge(int x, int y) {
        boolean north = cellEmpty(x, y + 1);
        boolean northEast = cellEmpty(x + 1, y + 1);
        boolean east = cellEmpty(x + 1, y);
        boolean southEast = cellEmpty(x + 1, y - 1);
        boolean south = cellEmpty(x, y - 1);
        boolean southWest = cellEmpty(x - 1, y - 1);
        boolean west = cellEmpty(x - 1, y);
        boolean northWest = cellEmpty(x - 1, y + 1);

        if (x == 0 || x == xMax) {
            if (! (y == 0 || y == yMax)) {
                if (!north) {
                    layer.setCell(x, y, tileSet.bottom);
                } else if (!south) {
                    layer.setCell(x, y, tileSet.top);
                }
            }
        }
        else if (y == 0 || y == yMax) {
            if (!west) {
                layer.setCell(x, y, tileSet.right);
            } else if (!east) {
                layer.setCell(x, y, tileSet.left);
            }
        }
        else if (!north && !west && !northWest){
            layer.setCell(x, y, tileSet.bottomRightInternal);
        }
        else if (!north && !east && !northEast){
            layer.setCell(x, y, tileSet.bottomLeftInternal);
        }
        else if (!south && !west && !southWest){
            layer.setCell(x, y, tileSet.topRightInternal);
        }
        else if (!south && !east && !southEast){
            layer.setCell(x, y, tileSet.topLeftInternal);
        }
        else if (north && west && !northWest){
            layer.setCell(x, y, tileSet.bottomRightExternal);
        }
        else if (north && east && !northEast){
            layer.setCell(x, y, tileSet.bottomLeftExternal);
        }
        else if (south && west && !southWest){
            layer.setCell(x, y, tileSet.topRightExternal);
        }
        else if (south && east && !southEast){
            layer.setCell(x, y, tileSet.topLeftExternal);
        }
        else if (east && !west){
            layer.setCell(x, y, tileSet.right);
        }
        else if (west && !east){
            layer.setCell(x, y, tileSet.left);
        }
        else if (south && !north){
            layer.setCell(x, y, tileSet.bottom);
        }
        else if (north && !south){
            layer.setCell(x, y, tileSet.top);
        }
    }

    private boolean cellEmpty(int x, int y) {
        if (x >= 0 && x < layer.getWidth() && y >= 0 && y < layer.getHeight()){
            Cell cell = layer.getCell(x, y);
            return cell == null || cell != tileSet.full;
        }
        return false;
    }
}
