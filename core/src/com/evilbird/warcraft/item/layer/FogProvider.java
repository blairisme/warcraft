package com.evilbird.warcraft.item.layer;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.evilbird.engine.common.inject.IdentifiedAssetProvider;
import com.evilbird.engine.common.lang.IdentifierNew;
import com.evilbird.engine.common.lang.Objects;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.item.Item;

import javax.inject.Inject;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class FogProvider implements IdentifiedAssetProvider<Item>
{
    private AssetManager assets;

    @Inject
    public FogProvider(Device device)
    {
        assets = device.getAssetStorage().getAssets();
    }

    @Override
    public void load()
    {
        assets.load("data/textures/neutral/winter/terrain.png", Texture.class);
    }

    @Override
    public Item get(IdentifierNew identifier)
    {
        if (Objects.equals(identifier, LayerType.OpaqueFog)){
            return new Fog(getOpaqueTextureSet());
        }
        if (Objects.equals(identifier, LayerType.TransparentFog)){
            throw new UnsupportedOperationException("Not yet implemented");
        }
        return null;
    }

    private FogTileSet getOpaqueTextureSet()
    {
        Texture opaqueTexture = getOpaqueTexture();
        Texture terrainTexture = getTerrainTexture();

        FogTileSet result = new FogTileSet();
        result.full = createCell(new TextureRegion(opaqueTexture));

        result.bottomRightInternal = createCell(new TextureRegion(terrainTexture, 32, 0, 32, 32));
        result.bottom = createCell(new TextureRegion(terrainTexture, 64, 0, 32, 32));
        result.bottomLeftInternal = createCell(new TextureRegion(terrainTexture, 96, 0, 32, 32));
        result.right = createCell(new TextureRegion(terrainTexture, 128, 0, 32, 32));

        result.left = createCell(new TextureRegion(terrainTexture, 192, 0, 32, 32));
        result.topRightInternal = createCell(new TextureRegion(terrainTexture, 224, 0, 32, 32));
        result.top = createCell(new TextureRegion(terrainTexture, 256, 0, 32, 32));
        result.topLeftInternal = createCell(new TextureRegion(terrainTexture, 288, 0, 32, 32));

        result.bottomRightExternal = createCell(new TextureRegion(terrainTexture, 320, 0, 32, 32));
        result.bottomLeftExternal = createCell(new TextureRegion(terrainTexture, 352, 0, 32, 32));
        result.topRightExternal = createCell(new TextureRegion(terrainTexture, 384, 0, 32, 32));
        result.topLeftExternal = createCell(new TextureRegion(terrainTexture, 416, 0, 32, 32));

        return result;
    }

    private Texture getOpaqueTexture()
    {
        Pixmap pixmap = new Pixmap(32, 32, Pixmap.Format.RGBA8888);
        pixmap.setColor(0, 0, 0, 1);
        pixmap.fillRectangle(0, 0, 32, 32);
        return new Texture(pixmap);
    }

    private Texture getTerrainTexture()
    {
        Texture texture = assets.get("data/textures/neutral/winter/terrain.png", Texture.class);
        texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Nearest);
        return texture;
    }

    private Cell createCell(TextureRegion region)
    {
        TiledMapTile tile = new StaticTiledMapTile(region);
        Cell cell = new Cell();
        cell.setTile(tile);
        return cell;
    }
}


