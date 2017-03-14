package com.evilbird.warcraft.item.layer.forest;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.specialized.Layer;
import com.evilbird.warcraft.item.unit.resource.tree.Tree;
import com.evilbird.warcraft.item.unit.resource.tree.TreeProvider;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
//TODO: Prune "dead" trees
public class Forest extends Layer
{
    private TreeProvider treeProvider;
    private Map<Cell, Tree> trees;

    @Inject
    public Forest(TreeProvider treeProvider)
    {
        this.treeProvider = treeProvider;
        this.trees = new HashMap<Cell, Tree>();

        setType(new Identifier("Forest"));
        setTouchable(Touchable.enabled);
    }

    @Override
    public void setLayer(TiledMapTileLayer layer)
    {
        super.setLayer(layer);
        trees.clear();
    }

    @Override
    public Item hit(Vector2 position, boolean touchable)
    {
        if (touchable && !getTouchable()) return null;

        int x = Math.round(position.x / layer.getTileWidth());
        int y = Math.round(position.y / layer.getTileHeight());

        Cell cell = layer.getCell(x, y);
        return cell != null ? getTree(x, y, cell) : null;
    }

    @Override
    public void draw(Batch batch, float alpha)
    {
        super.draw(batch, alpha);
        for (Tree tree: trees.values()){
            tree.draw(batch, alpha);
        }
    }

    @Override
    public void update(float delta)
    {
        super.update(delta);
        for (Tree tree: trees.values()){
            tree.update(delta);
        }
    }

    private Tree getTree(int x, int y, Cell cell)
    {
        Tree result = trees.get(cell);
        if (result == null){
            cell = copyCell(cell);
            layer.setCell(x, y, cell);

            result = treeProvider.get();
            result.setCell(cell);

            trees.put(cell, result);
        }
        return result;
    }

    private Cell copyCell(Cell cell)
    {
        Cell result = new Cell();
        result.setTile(copyTile(cell.getTile()));
        return result;
    }

    private TiledMapTile copyTile(TiledMapTile tile)
    {
        if (tile instanceof StaticTiledMapTile){
            return new StaticTiledMapTile((StaticTiledMapTile)tile);
        }
        throw new UnsupportedOperationException();
    }
}
