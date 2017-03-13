package com.evilbird.warcraft.item.layer;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemGroup;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.engine.item.specialized.Layer;

import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.Collection;

import static com.evilbird.engine.item.ItemPredicates.itemWithAction;
import static com.evilbird.engine.item.ItemPredicates.itemWithId;

public class Fog extends Layer
{
    private FogTileSet tileSet;
    private boolean initialized;

    public Fog(FogTileSet tileSet)
    {
        this.tileSet = tileSet;
        this.initialized = false;
        setType(new Identifier("Fog"));
    }

    @Override
    public void setLayer(TiledMapTileLayer layer)
    {
        super.setLayer(new TiledMapTileLayer(
            layer.getWidth(),
            layer.getHeight(),
            (int)layer.getTileWidth(),
            (int)layer.getTileHeight()));
    }

    @Override
    public void update(float delta)
    {
        super.update(delta);
        if (!initialized){
            initialized = true;
            initialize();
        }
        else {
            update();
        }
    }

    private void initialize()
    {
        conceal();
        ItemGroup player = getPlayer();
        revealItems(player.getItems());
    }

    private void update()
    {
        ItemGroup player = getPlayer();
        Collection<Item> revealedItems = player.findAll(itemWithAction());
        revealItems(revealedItems);
    }

    private ItemGroup getPlayer()
    {
        ItemRoot world = getRoot();
        return (ItemGroup)world.find(itemWithId(new Identifier("Player1"))); //TODO
    }

    private void conceal(){
        for (int x = 0; x < layer.getWidth(); ++x){
            for (int y = 0; y < layer.getHeight(); ++y){
                layer.setCell(x, y, tileSet.full);
            }
        }
    }

    private void revealItems(Collection<Item> items)
    {
        for (Item item: items){
            revealItem(item);
        }
    }

    private void revealItem(Item item)
    {
        Collection<Pair<Integer, Integer>> revealedCells = getRevealedCells(item);
        for (Pair<Integer, Integer> revealedGrid: revealedCells){
            layer.setCell(revealedGrid.getLeft(), revealedGrid.getRight(), null);
        }
        for (Pair<Integer, Integer> revealedGrid: revealedCells){
            updateCellEdge(revealedGrid.getLeft(), revealedGrid.getRight());
        }
    }

    @SuppressWarnings("SuspiciousNameCombination")
    //TODO: Dont include corners as in original game
    private Collection<Pair<Integer, Integer>> getRevealedCells(Item item)
    {
        Collection<Pair<Integer, Integer>> result = new ArrayList<Pair<Integer, Integer>>();

        Vector2 position = item.getPosition();
        int itemX = position.x != 0 ? Math.round(position.x / 32) : 0;
        int itemY = position.y != 0 ? Math.round(position.y / 32) : 0;
        int width = layer.getWidth();
        int height = layer.getHeight();
        int radius = 5;

        for (int x = itemX - radius; x <= itemX + radius; x++){
            for (int y = itemY - radius; y <= itemY + radius; y++){
                if (x >= 0 && y >= 0 && x < width && y < height){
                    result.add(Pair.of(x, y));
                }
            }
        }
        return result;
    }

    private void updateCellEdge(int x, int y)
    {
        boolean north = cellEmpty(x, y + 1);
        boolean northEast = cellEmpty(x + 1, y + 1);
        boolean east = cellEmpty(x + 1, y);
        boolean southEast = cellEmpty(x + 1, y - 1);
        boolean south = cellEmpty(x, y - 1);
        boolean southWest = cellEmpty(x - 1, y - 1);
        boolean west = cellEmpty(x - 1, y);
        boolean northWest = cellEmpty(x - 1, y + 1);

        if (!north && !west && !northWest){
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

    private boolean cellEmpty(int x, int y)
    {
        if (x >= 0 && x < layer.getWidth() && y >= 0 && y < layer.getHeight()){
            Cell cell = layer.getCell(x, y);
            return cell == null || cell != tileSet.full;
        }
        return false;
    }
}
