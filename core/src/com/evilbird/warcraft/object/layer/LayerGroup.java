/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.layer;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.common.collection.BitMatrix;
import com.evilbird.engine.common.maps.MapLayerEntry;
import com.evilbird.engine.common.maps.MapLayerIterable;
import com.evilbird.engine.object.GameObject;
import org.apache.commons.lang3.Validate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static com.evilbird.engine.common.collection.BitMatrix.matrix3;

/**
 * Represents a {@link Layer} made up of a number of {@link LayerCell
 * cells}. Each cell has an independent value that when it reaches zero will
 * be assigned the cell, as specified by the {@link LayerGroupStyle} contained
 * in the {@link Skin} given when the LayerGroup is constructed. When setting a
 * cell to empty the surrounding cells will also be evaluated, comparing each
 * to the patterns in the LayerGroupStyle and updating their cells accordingly.
 *
 * @author Blair Butterworth
 */
public class LayerGroup extends Layer
{
    private static final transient int EDGE_MATRIX_SIZE = 5;
    private static final transient int EDGE_MATRIX_CENTER = 2;
    private static final transient int PATTERN_MATRIX_SIZE = 3;
    private static final transient int PATTERN_MATRIX_CENTER = 1;
    private static final transient BitMatrix FULL_PATTERN = matrix3("1,1,1,1,1,1,1,1,1");

    protected transient Skin skin;
    protected transient LayerGroupStyle style;
    protected transient Map<GridPoint2, LayerCell> cells;

    public LayerGroup(Skin skin) {
        this.skin = skin;
        this.style = skin.get(LayerGroupStyle.class);
        this.cells = new HashMap<>();
    }

    public Skin getSkin() {
        return skin;
    }

    @Override
    public void addObject(GameObject object) {
        Validate.isAssignableFrom(LayerCell.class, object.getClass());
        super.addObject(object);

        LayerCell cell = (LayerCell)object;
        cells.put(cell.getLocation(), cell);
    }

    @Override
    public void removeObject(GameObject object) {
        Validate.isAssignableFrom(LayerCell.class, object.getClass());
        super.removeObject(object);

        LayerCell cell = (LayerCell) object;
        cells.remove(cell.getLocation());
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        if (!hasObjects()) {
            createCells();
        }
    }

    protected void createCells() {
        for (MapLayerEntry entry: new MapLayerIterable(layer)) {
            addObject(createCell(entry.getPosition()));
        }
    }

    protected LayerCell createCell(GridPoint2 location) {
        return new LayerCell(style, location);
    }

    protected LayerCell createCell(GridPoint2 location, float value) {
        return new LayerCell(style, location, value);
    }

    public Collection<GridPoint2> setAdjacentTextures(GridPoint2 tile) {
        Collection<GridPoint2> updated = new ArrayList<>();
        setAdjacentTextures(tile, updated);
        return updated;
    }

    public Collection<GridPoint2> setAdjacentTextures(Collection<GridPoint2> tiles) {
        Collection<GridPoint2> updated = new ArrayList<>();
        for (GridPoint2 tile: tiles) {
            setAdjacentTextures(tile, updated);
        }
        return updated;
    }

    protected void setAdjacentTextures(GridPoint2 tile, Collection<GridPoint2> updated) {
        BitMatrix cellEdges = getCellEdges(tile);
        if (! cellEdges.isEmpty()) {
            updateCellEdges(tile, cellEdges, updated);
        }
    }

    protected BitMatrix getCellEdges(GridPoint2 tile) {
        BitMatrix occupation = new BitMatrix(EDGE_MATRIX_SIZE);
        for (int i = 0; i < EDGE_MATRIX_SIZE; i++) {
            for (int j = 0; j < EDGE_MATRIX_SIZE; j++) {
                int xIndex = tile.x + (i - EDGE_MATRIX_CENTER);
                int yIndex = tile.y + (j - EDGE_MATRIX_CENTER);
                occupation.set(i, j, isCellOccupied(xIndex, yIndex));
            }
        }
        return occupation;
    }

    protected void updateCellEdges(GridPoint2 tile, BitMatrix cellEdges, Collection<GridPoint2> updated) {
        for (int i = 0; i < PATTERN_MATRIX_SIZE; i++) {
            for (int j = 0; j < PATTERN_MATRIX_SIZE; j++) {
                int xIndex = tile.x + (i - PATTERN_MATRIX_CENTER);
                int yIndex = tile.y + (j - PATTERN_MATRIX_CENTER);
                GridPoint2 index = new GridPoint2(xIndex, yIndex);
                if (! updated.contains(index)) {
                    LayerCell cell = cells.get(index);
                    if (cell != null) {
                        BitMatrix edgePattern = cellEdges.subMatrix(i, j, PATTERN_MATRIX_SIZE);
                        if (updateCellEdges(cell, edgePattern)) {
                            updated.add(index);
                        }
                    }
                }
            }
        }
    }

    protected boolean updateCellEdges(LayerCell cell, BitMatrix edgePattern) {
        if (edgePattern.equals(FULL_PATTERN)) {
            cell.showFull();
            return true;
        }
        if (cell.showEdge(edgePattern)) {
            return true;
        }
        return false;
    }

    //TODO: Use LayerCell to determine empty
    protected boolean isCellOccupied(int x, int y) {
        if (x < 0 || x >= layer.getWidth()) { return true; }
        if (y < 0 || y >= layer.getHeight()) { return true; }

        Cell cell = layer.getCell(x, y);
        return cell != null && cell != style.empty;
    }
}
