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

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

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
    private static final transient float DEFAULT_VALUE = 100;

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
            addCells();
        }
    }

    protected void addCells() {
        for (MapLayerEntry entry: new MapLayerIterable(layer)) {
            LayerCell groupCell = createCell(entry);
            addObject(groupCell);
        }
    }

    protected LayerCell createCell(MapLayerEntry entry) {
        return new LayerCell(entry.getPosition(), DEFAULT_VALUE);
    }

    public void setEmptyTexture(GridPoint2 tile) {
        layer.setCell(tile.x, tile.y, style.empty);
    }

    public void setFullTexture(GridPoint2 tile) {
        layer.setCell(tile.x, tile.y, style.full);
    }

    public void setAdjacentTextures(Collection<GridPoint2> tiles) {
        for (GridPoint2 tile: tiles) {
            setAdjacentTextures(tile);
        }
    }

    public void setAdjacentTextures(Collection<GridPoint2> tiles, int radius) {
        for (GridPoint2 tile: tiles) {
            setAdjacentTextures(tile, radius);
        }
    }

    public void setAdjacentTextures(GridPoint2 tile, int radius) {
        int startX = Math.max(tile.x - radius, 0);
        int startY = Math.max(tile.y - radius, 0);
        int endX = Math.min(tile.x + radius, layer.getWidth() - 1);
        int endY = Math.min(tile.y + radius, layer.getHeight() - 1);

        for (int x = startX; x <= endX; x++) {
            for (int y = startY; y <= endY; y++) {
                setAdjacentTextures(x, y);
            }
        }
    }

    public void setAdjacentTextures(GridPoint2 tile) {
        setAdjacentTextures(tile.x, tile.y);
    }

    public void setAdjacentTextures(int x, int y) {
        BitMatrix cellEdges = getCellEdges(x, y);
        if (! cellEdges.isEmpty()) {
            updateCellEdges(x, y, cellEdges);
        }
    }

    protected BitMatrix getCellEdges(int x, int y) {
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

    protected void updateCellEdges(int x, int y, BitMatrix cellEdges) {
        for (int i = 0; i < PATTERN_MATRIX_SIZE; i++) {
            for (int j = 0; j < PATTERN_MATRIX_SIZE; j++) {
                BitMatrix edgePattern = cellEdges.subMatrix(i, j, PATTERN_MATRIX_SIZE);
                Cell edgeStyle = style.patterns.get(edgePattern);

                if (edgeStyle != null) {
                    int xIndex = x + (i - PATTERN_MATRIX_CENTER);
                    int yIndex = y + (j - PATTERN_MATRIX_CENTER);
                    layer.setCell(xIndex, yIndex, edgeStyle);
                }
            }
        }
    }

    protected boolean isCellOccupied(int x, int y) {
        if (x < 0 || x >= layer.getWidth()) { return true; }
        if (y < 0 || y >= layer.getHeight()) { return true; }

        Cell cell = layer.getCell(x, y);
        return cell != null && cell != style.empty;
    }
}
