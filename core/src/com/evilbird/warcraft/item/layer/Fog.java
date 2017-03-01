package com.evilbird.warcraft.item.layer;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.evilbird.engine.common.function.Predicate;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemRoot;

import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.Collection;

import static com.evilbird.engine.common.function.Predicates.both;
import static com.evilbird.engine.item.ItemPredicates.itemWithAction;
import static com.evilbird.engine.item.ItemPredicates.itemWithProperty;

//TODO: Use map object or common utility class to draw
//TODO: Move asset loading into factory.
//TODO: Move Texture/Cell types into separate class - I.E., TextureMap
public class Fog extends Item
{
    private boolean populated;
    private TiledMapTileLayer layer;
    private OrthogonalTiledMapRenderer renderer;

    private Cell full;
    private Cell bottom;
    private Cell right;
    private Cell left;
    private Cell top;
    private Cell bottomRightInternal;
    private Cell bottomLeftInternal;
    private Cell topRightInternal;
    private Cell topLeftInternal;
    private Cell bottomRightExternal;
    private Cell bottomLeftExternal;
    private Cell topRightExternal;
    private Cell topLeftExternal;

    public Fog(AssetManager assets, int width, int height, int tileWidth, int tileHeight)
    {
        renderer = new OrthogonalTiledMapRenderer(null);
        layer = new TiledMapTileLayer(width, height, tileWidth, tileHeight);
        populated = false;

        assets.load("data/textures/neutral/winter/terrain.png", Texture.class);
        assets.finishLoadingAsset("data/textures/neutral/winter/terrain.png");


        Pixmap blackRectangle = new Pixmap(32, 32, Pixmap.Format.RGBA8888);
        blackRectangle.setColor(0, 0, 0, 1);
        blackRectangle.fillRectangle(0, 0, width, height);
        full = createCell(new TextureRegion(new Texture(blackRectangle)));

        Texture texture = assets.get("data/textures/neutral/winter/terrain.png", Texture.class);
        texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Nearest);
        bottomRightInternal = createCell(new TextureRegion(texture, 32, 0, 32, 32));
        bottom = createCell(new TextureRegion(texture, 64, 0, 32, 32));
        bottomLeftInternal = createCell(new TextureRegion(texture, 96, 0, 32, 32));
        right = createCell(new TextureRegion(texture, 128, 0, 32, 32));

        left = createCell(new TextureRegion(texture, 192, 0, 32, 32));
        topRightInternal = createCell(new TextureRegion(texture, 224, 0, 32, 32));
        top = createCell(new TextureRegion(texture, 256, 0, 32, 32));
        topLeftInternal = createCell(new TextureRegion(texture, 288, 0, 32, 32));

        bottomRightExternal = createCell(new TextureRegion(texture, 320, 0, 32, 32));
        bottomLeftExternal = createCell(new TextureRegion(texture, 352, 0, 32, 32));
        topRightExternal = createCell(new TextureRegion(texture, 384, 0, 32, 32));
        topLeftExternal = createCell(new TextureRegion(texture, 416, 0, 32, 32));

        conceal();
    }

    private Cell createCell(TextureRegion region)
    {
        TiledMapTile tile = new StaticTiledMapTile(region);
        Cell cell = new Cell();
        cell.setTile(tile);
        return cell;
    }

    @Override
    public void draw(Batch batch, float alpha)
    {
        OrthogonalTiledMapRenderer renderer = new OrthogonalTiledMapRenderer(null, batch);
        renderer.setView(getCamera());
        renderer.renderTileLayer(layer);
    }

    private OrthographicCamera getCamera()
    {
        ItemRoot root = getRoot();
        Viewport viewport = root.getViewport();
        return (OrthographicCamera)viewport.getCamera();
    }

    @Override
    public void update(float delta)
    {
        super.update(delta);
        if (!populated){
            populated = true;
            initialize();
        }
        else {
            update();
        }
    }

    private void initialize()
    {
        ItemRoot world = getRoot();
        Collection<Item> revealedItems = world.findAll(itemOwnedByPlayer());
        revealItems(revealedItems);
    }

    private void update()
    {
        ItemRoot world = getRoot();
        Collection<Item> revealedItems = world.findAll(both(itemWithAction(), itemOwnedByPlayer()));
        revealItems(revealedItems);
    }

    private Predicate<Item> itemOwnedByPlayer()
    {
        return itemWithProperty(new Identifier("Owner"), new Identifier("Player1"));
    }

    private void conceal(){
        for (int x = 0; x < layer.getWidth(); ++x)
            for (int y = 0; y < layer.getHeight(); ++y){
                layer.setCell(x, y, full);
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

    private Collection<Pair<Integer, Integer>> getRevealedCells(Item item)
    {
        Collection<Pair<Integer, Integer>> result = new ArrayList<Pair<Integer, Integer>>();

        Vector2 position = item.getPosition();
        int itemX = (int)(position.x / 32);
        int itemY = (int)(position.y / 32);
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
            layer.setCell(x, y, bottomRightInternal);
        }
        else if (!north && !east && !northEast){
            layer.setCell(x, y, bottomLeftInternal);
        }
        else if (!south && !west && !southWest){
            layer.setCell(x, y, topRightInternal);
        }
        else if (!south && !east && !southEast){
            layer.setCell(x, y, topLeftInternal);
        }
        else if (north && west && !northWest){
            layer.setCell(x, y, bottomRightExternal);
        }
        else if (north && east && !northEast){
            layer.setCell(x, y, bottomLeftExternal);
        }
        else if (south && west && !southWest){
            layer.setCell(x, y, topRightExternal);
        }
        else if (south && east && !southEast){
            layer.setCell(x, y, topLeftExternal);
        }
        else if (east && !west){
            layer.setCell(x, y, right);
        }
        else if (west && !east){
            layer.setCell(x, y, left);
        }
        else if (south && !north){
            layer.setCell(x, y, bottom);
        }
        else if (north && !south){
            layer.setCell(x, y, top);
        }
    }

    private boolean cellEmpty(int x, int y)
    {
        if (x >= 0 && x < layer.getWidth() && y >= 0 && y < layer.getHeight()){
            Cell cell = layer.getCell(x, y);
            return cell == null || cell != full;
        }
        return false;
    }
}
