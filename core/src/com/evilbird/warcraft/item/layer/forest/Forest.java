/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.layer.forest;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.common.collection.BitMatrix;
import com.evilbird.engine.common.maps.MapLayerEntry;
import com.evilbird.engine.common.maps.MapLayerIterable;
import com.evilbird.engine.common.serialization.SerializedType;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.layer.Layer;
import com.google.gson.annotations.JsonAdapter;

/**
 * Instances of this class represent a forest layer. Forests consist of
 * individual cells, trees, which although represented and obtainable as
 * {@link Item Items }, are actually drawn by the forest. The forest is also
 * used to
 *
 * @author Blair Butterworth
 */
@SerializedType("Forest")
@JsonAdapter(ForestAdapter.class)
public class Forest extends Layer
{
    private static transient final int EDGE_MATRIX_SIZE = 5;
    private static transient final int EDGE_MATRIX_CENTER = 2;
    private static transient final int PATTERN_MATRIX_SIZE = 3;
    private static transient final int PATTERN_MATRIX_CENTER = 1;
    private static transient final float DEFAULT_WOOD = 100;

    private transient Skin skin;
    private transient ForestStyle style;

    public Forest(Skin skin) {
        this.skin = skin;
        this.style = skin.get(ForestStyle.class);
    }

    public Skin getSkin() {
        return skin;
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        if (!hasItems()) {
            addForestCells();
        }
    }

    private void addForestCells() {
        for (MapLayerEntry entry: new MapLayerIterable(layer)) {
            ForestCell forestCell = new ForestCell();
            forestCell.setWood(DEFAULT_WOOD);
            forestCell.setLocation(entry.getPosition());
            addItem(forestCell);
        }
    }

    public void setDeadTexture(GridPoint2 tile) {
        layer.setCell(tile.x, tile.y, style.deforested);
        BitMatrix cellEdges = getCellEdges(tile.x, tile.y);
        if (! cellEdges.isEmpty()) {
            updateCellEdges(tile.x, tile.y, cellEdges);
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
                BitMatrix edgePattern = cellEdges.subMatrix(i, j, PATTERN_MATRIX_SIZE);
                Cell edgeStyle = style.trees.get(edgePattern);

                if (edgeStyle != null) {
                    int xIndex = x + (i - PATTERN_MATRIX_CENTER);
                    int yIndex = y + (j - PATTERN_MATRIX_CENTER);
                    layer.setCell(xIndex, yIndex, edgeStyle);
                }
            }
        }
    }

    private boolean isCellOccupied(int x, int y) {
        if (x < 0 || x >= layer.getWidth()) return true;
        if (y < 0 || y >= layer.getHeight()) return true;

        Cell cell = layer.getCell(x, y);
        return cell != null && cell != style.deforested;
    }
}
