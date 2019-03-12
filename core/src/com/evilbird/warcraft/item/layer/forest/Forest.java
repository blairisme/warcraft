/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.layer.forest;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.common.maps.MapLayerEntry;
import com.evilbird.engine.common.maps.MapLayerIterable;
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
@JsonAdapter(ForestAdapter.class)
public class Forest extends Layer
{
    private transient static final float DEFAULT_WOOD = 100;
    private transient ForestStyle style;

    public Forest() {
    }

    public void setSkin(Skin skin) {
        style = skin.get(ForestStyle.class);
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
        setTexture(tile.x, tile.y, style.deadCenter);
        setTexture(tile.x + 1, tile.y, style.deadEast);
        setTexture(tile.x + 1, tile.y + 1, style.deadSouthEast);
        setTexture(tile.x, tile.y + 1, style.deadSouth);
        setTexture(tile.x - 1, tile.y + 1, style.deadSouthWest);
        setTexture(tile.x - 1, tile.y, style.deadWest);
        setTexture(tile.x - 1, tile.y - 1, style.deadNorthWest);
        setTexture(tile.x, tile.y - 1, style.deadNorth);
        setTexture(tile.x + 1, tile.y - 1, style.deadNorthEast);
    }

    private void setTexture(int x, int y, TextureRegion texture) {
        Cell cell = layer.getCell(x, y);
        if (cell != null) {
            cell.setTile(new StaticTiledMapTile(texture));
        }
    }
}
