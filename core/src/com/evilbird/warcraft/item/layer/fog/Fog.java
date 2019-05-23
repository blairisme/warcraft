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
import com.evilbird.engine.common.collection.BitMatrix;
import com.evilbird.engine.events.EventQueue;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemGroup;
import com.evilbird.warcraft.action.common.create.CreateEvent;
import com.evilbird.warcraft.action.move.MoveEvent;
import com.evilbird.warcraft.item.common.query.UnitOperations;
import com.evilbird.warcraft.item.data.player.Player;
import com.evilbird.warcraft.item.layer.Layer;
import com.evilbird.warcraft.item.layer.LayerAdapter;
import com.evilbird.warcraft.item.unit.Unit;
import com.google.gson.annotations.JsonAdapter;

import java.util.ArrayList;
import java.util.Collection;

import static com.evilbird.warcraft.item.WarcraftItemConstants.TILE_WIDTH;

/**
 * Instances of this class represent fog of war: a layer of darkness that
 * recedes when units walk near and discover new territory.
 *
 * @author Blair Butterworth
 */
@JsonAdapter(LayerAdapter.class)
public class Fog extends Layer
{
    private static final transient int EDGE_MATRIX_SIZE = 5;
    private static final transient int EDGE_MATRIX_CENTER = 2;
    private static final transient int PATTERN_MATRIX_SIZE = 3;
    private static final transient int PATTERN_MATRIX_CENTER = 1;

    private transient Skin skin;
    private transient FogStyle style;
    private transient EventQueue events;
    private transient boolean initialized;

    public Fog(Skin skin) {
        this.skin = skin;
        this.style = skin.get(FogStyle.class);
    }

    public Skin getSkin() {
        return skin;
    }

    public void setEvents(EventQueue events) {
        this.events = events;
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
            evaluateEvents();
        }
    }

    private void initialize() {
        concealItems();
        ItemGroup player = UnitOperations.getHumanPlayer(getRoot());
        revealItems(player.getItems());
    }

    private void concealItems(){
        for (int x = 0; x < layer.getWidth(); ++x){
            for (int y = 0; y < layer.getHeight(); ++y){
                layer.setCell(x, y, style.full);
            }
        }
    }

    private void evaluateEvents() {
        for (MoveEvent moveEvent: events.getEvents(MoveEvent.class)) {
            evaluateEvent(moveEvent.getSubject());
        }
        for (CreateEvent createEvent: events.getEvents(CreateEvent.class)) {
            evaluateEvent(createEvent.getSubject());
        }
    }

    private void evaluateEvent(Item item) {
        Player player = UnitOperations.getPlayer(item);
        if (player.isCorporeal()) {
            revealItem(item);
        }
    }

    private void revealItems(Collection<Item> items) {
        for (Item item: items){
            revealItem(item);
        }
    }

    private void revealItem(Item item) {
        Collection<GridPoint2> revealedCells = getRevealedCells(item);
        for (GridPoint2 revealedCell : revealedCells) {
            layer.setCell(revealedCell.x, revealedCell.y, style.empty);
        }
        for (GridPoint2 revealedCell : revealedCells) {
            updateCellEdges(revealedCell.x, revealedCell.y);
        }
    }

    private Collection<GridPoint2> getRevealedCells(Item item) {
        Collection<GridPoint2> result = new ArrayList<>();

        int radius = getSight(item);
        GridPoint2 position = toGridPoint(item.getPosition());
        GridPoint2 itemSize = toGridPoint(item.getSize());

        for (int x = position.x - radius; x <= position.x + radius + itemSize.x; x++){
            for (int y = position.y - radius; y <= position.y + radius + itemSize.x; y++){
                if (withinBounds(x, y) && isOccupied(x, y)){
                    result.add(new GridPoint2(x, y));
                }
            }
        }
        return result;
    }

    private GridPoint2 toGridPoint(Vector2 vector) {
        GridPoint2 result = new GridPoint2();
        result.x = vector.x != 0 ? Math.round(vector.x / TILE_WIDTH) : 0;
        result.y = vector.y != 0 ? Math.round(vector.y / TILE_WIDTH) : 0;
        return result;
    }

    private int getSight(Item item){
        if (item instanceof Unit){
            Unit unit = (Unit)item;
            return unit.getSightTiles();
        }
        return 0;
    }

    private boolean withinBounds(int x, int y) {
        return x >= 0 && y >= 0 && x < layer.getWidth() && y < layer.getHeight();
    }

    private void updateCellEdges(int x, int y) {
        BitMatrix cellEdges = getCellEdges(x, y);
        if (! cellEdges.isEmpty()) {
            updateCellEdges(x, y, cellEdges);
        }
    }

    private void updateCellEdges(int x, int y, BitMatrix cellEdges) {
        for (int i = 0; i < PATTERN_MATRIX_SIZE; i++) {
            for (int j = 0; j < PATTERN_MATRIX_SIZE; j++) {
                BitMatrix edgePattern = cellEdges.subMatrix(i, j, PATTERN_MATRIX_SIZE);
                Cell edgeStyle = style.edges.get(edgePattern);

                if (edgeStyle != null) {
                    int xIndex = x + (i - PATTERN_MATRIX_CENTER);
                    int yIndex = y + (j - PATTERN_MATRIX_CENTER);
                    layer.setCell(xIndex, yIndex, edgeStyle);
                }
            }
        }
    }

    private BitMatrix getCellEdges(int x, int y) {
        BitMatrix occupation = new BitMatrix(EDGE_MATRIX_SIZE);
        for (int i = 0; i < EDGE_MATRIX_SIZE; i++) {
            for (int j = 0; j < EDGE_MATRIX_SIZE; j++) {
                int xIndex = x + (i - EDGE_MATRIX_CENTER);
                int yIndex = y + (j - EDGE_MATRIX_CENTER);
                occupation.set(i, j, isOccupied(xIndex, yIndex));
            }
        }
        return occupation;
    }

    private boolean isOccupied(int x, int y) {
        if (x < 0 || x >= layer.getWidth()) { return true; }
        if (y < 0 || y >= layer.getHeight()) { return true; }

        Cell cell = layer.getCell(x, y);
        return cell != style.empty;
    }
}
