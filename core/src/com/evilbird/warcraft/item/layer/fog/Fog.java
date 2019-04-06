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
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.common.serialization.SerializedType;
import com.evilbird.engine.events.EventQueue;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemGroup;
import com.evilbird.warcraft.action.move.MoveEvent;
import com.evilbird.warcraft.item.common.query.UnitOperations;
import com.evilbird.warcraft.item.layer.Layer;
import com.evilbird.warcraft.item.layer.LayerAdapter;
import com.evilbird.warcraft.item.layer.common.BitMatrix;
import com.evilbird.warcraft.item.unit.Unit;
import com.google.gson.annotations.JsonAdapter;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Instances of this class represent fog of war: a layer of darkness that
 * recedes when units walk near and discover new territory.
 *
 * @author Blair Butterworth
 */
//TODO: Enhancement: Use BitSet to update cell textures
//TODO: Bug (potential): respond to creation events (when they exist :s)
//TODO: Bug: thin peninsulas rendered incorrectly. Fix: don't render them.
//TODO: Bug: revealing doesn't include item size, only position. Fix: use logic from ItemGraph.
//TODO: update sight to cells, not pixels
@SerializedType("Fog")
@JsonAdapter(LayerAdapter.class)
public class Fog extends Layer
{
    private static final int EDGE_MATRIX_SIZE = 5;
    private static final int EDGE_MATRIX_CENTER = 2;
    private static final int PATTERN_MATRIX_SIZE = 3;
    private static final int PATTERN_MATRIX_CENTER = 1;

    private transient FogStyle style;
    private transient EventQueue events;
    private transient boolean initialized;

    public Fog() {
    }

    public void setEvents(EventQueue events) {
        this.events = events;
    }

    public void setSkin(Skin skin) {
        this.style = skin.get(FogStyle.class);
    }

    @Override
    public void setLayer(TiledMapTileLayer layer) {
        super.setLayer(new TiledMapTileLayer(
            layer.getWidth(),
            layer.getHeight(),
            (int)layer.getTileWidth(),
            (int)layer.getTileHeight()));
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
                layer.setCell(x, y, style.full);
            }
        }
    }

    private void revealItems(Collection<Item> items) {
        for (Item item: items){
            revealItem(item);
        }
    }

    private void revealItem(Item item) {
        Collection<GridPoint2> revealedCells = getRevealedCells(item);
        for (GridPoint2 revealedCell: revealedCells){
            layer.setCell(revealedCell.x, revealedCell.y, style.empty);
        }
        for (GridPoint2 revealedCell: revealedCells){
            updateCellEdges(revealedCell.x, revealedCell.y);
        }
    }

    private Collection<GridPoint2> getRevealedCells(Item item) {
        Collection<GridPoint2> result = new ArrayList<>();

        Vector2 position = item.getPosition();
        int itemX = position.x != 0 ? Math.round(position.x / 32) : 0;
        int itemY = position.y != 0 ? Math.round(position.y / 32) : 0;
        int width = layer.getWidth();
        int height = layer.getHeight();
        int radius = getSight(item);

        for (int x = itemX - radius; x <= itemX + radius; x++){
            for (int y = itemY - radius; y <= itemY + radius; y++){
                if (x >= 0 && y >= 0 && x < width && y < height){
                    if (isCellOccupied(x, y)) {
                        result.add(new GridPoint2(x, y));
                    }
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

    private void updateCellEdges(int x, int y) {
        BitMatrix cellEdges = getCellEdges(x, y);
        if (! cellEdges.isEmpty()) {
            updateCellEdges(x, y, cellEdges);
        }
    }

    private BitMatrix getCellEdges(int x, int y) {
        BitMatrix occupation = new BitMatrix(EDGE_MATRIX_SIZE);
        for (int i = 0; i < EDGE_MATRIX_SIZE; i++) {
            for (int j = 0; j < EDGE_MATRIX_SIZE; j++) {
                int xIndex = x + (i - EDGE_MATRIX_CENTER);
                int yIndex = y + (j - EDGE_MATRIX_CENTER);
                occupation.set(i, j, isCellOccupied(xIndex, yIndex));
            }
        }
        return occupation;
    }

    private void updateCellEdges(int x, int y, BitMatrix cellEdges) {
        for (int i = 0; i < PATTERN_MATRIX_SIZE; i++) {
            for (int j = 0; j < PATTERN_MATRIX_SIZE; j++) {
                BitMatrix edgePattern = cellEdges.getSubMatrix(i, j, PATTERN_MATRIX_SIZE);
                Cell edgeStyle = style.edges.get(edgePattern);

                if (edgeStyle != null) {
                    int xIndex = x + (i - PATTERN_MATRIX_CENTER);
                    int yIndex = y + (j - PATTERN_MATRIX_CENTER);
                    layer.setCell(xIndex, yIndex, edgeStyle);
                }
            }
        }
    }

    private boolean isCellOccupied(int x, int y) {
        Cell cell = layer.getCell(x, y);
        return cell != style.empty;
    }
}
