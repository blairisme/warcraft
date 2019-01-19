/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.layer.forest;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.evilbird.engine.common.function.Predicate;
import com.evilbird.engine.common.lang.NamedIdentifier;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.specialized.layer.Layer;
import com.evilbird.warcraft.item.layer.LayerType;
import com.evilbird.warcraft.item.unit.resource.tree.Tree;
import com.evilbird.warcraft.item.unit.resource.tree.TreeProvider;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

/**
 * Instances of this class represent a forest, a collection of trees.
 *
 * @author Blair Butterworth
 */
public class Forest extends Layer
{
    private TreeProvider treeProvider;
    private Map<Cell, Tree> trees;

    @Inject
    public Forest(TreeProvider treeProvider) {
        this.treeProvider = treeProvider;
        this.trees = new HashMap<>();
        setType(LayerType.Forest);
        setTouchable(Touchable.disabled);
    }

    @Override
    public void draw(Batch batch, float alpha) {
        super.draw(batch, alpha);
        for (Tree tree: trees.values()){
            tree.draw(batch, alpha);
        }
    }

    @Override
    public Item hit(Vector2 coordinates, boolean respectTouchability) {
        return childHit(coordinates, respectTouchability);
    }

    @Override
    public void setLayer(TiledMapTileLayer layer) {
        super.setLayer(layer);
        trees.clear();
        populateTrees();
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        for (Tree tree: trees.values()){
            tree.update(delta);
        }
    }

    private void populateTrees() {
        for (int x = 0; x < layer.getTileWidth(); ++x){
            for (int y = 0; y < layer.getTileWidth(); ++y){
                Cell cell = layer.getCell(x, y);
                if (cell != null) {
                    Tree tree = getTree(x, y, cell);
                    addItem(tree);
                }
            }
        }
    }

    private Tree getTree(int x, int y, Cell cell) {
        Tree result = trees.get(cell);
        if (result == null) {
            cell = copyCell(cell);
            layer.setCell(x, y, cell);

            result = treeProvider.get();
            result.setCell(cell);
            result.setSize(layer.getTileWidth(), layer.getTileHeight());
            result.setPosition(x * layer.getTileWidth(), y * layer.getTileHeight());

            trees.put(cell, result);
        }
        return result;
    }

    private Cell copyCell(Cell cell) {
        Cell result = new Cell();
        result.setTile(copyTile(cell.getTile()));
        return result;
    }

    private TiledMapTile copyTile(TiledMapTile tile) {
        if (tile instanceof StaticTiledMapTile){
            return new StaticTiledMapTile((StaticTiledMapTile)tile);
        }
        throw new UnsupportedOperationException();
    }
}
