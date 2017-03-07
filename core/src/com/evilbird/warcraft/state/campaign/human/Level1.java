package com.evilbird.warcraft.state.campaign.human;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.evilbird.engine.common.graphics.DirectionalAnimation;
import com.evilbird.engine.common.inject.AssetProvider;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemFactory;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.engine.item.control.AnimatedItem;
import com.evilbird.warcraft.item.layer.Camera;
import com.evilbird.warcraft.item.layer.Fog;
import com.evilbird.warcraft.item.layer.Map;
import com.evilbird.warcraft.item.unit.UnitType;

import org.apache.commons.lang3.Range;

import java.util.HashMap;
import java.util.Objects;

import javax.inject.Inject;

import static com.badlogic.gdx.Gdx.graphics;

public class Level1 implements AssetProvider<ItemRoot>
{
    private AssetManager assets;
    private ItemFactory itemFactory;

    @Inject
    public Level1(Device device, ItemFactory itemFactory)
    {
        this.assets = device.getAssetStorage().getAssets();
        this.itemFactory = itemFactory;
    }

    @Override
    public void load()
    {
    }

    @Override
    public ItemRoot get()
    {
        TmxMapLoader.Parameters parameters = new TmxMapLoader.Parameters();
        parameters.textureMinFilter = Texture.TextureFilter.Linear;
        parameters.textureMagFilter = Texture.TextureFilter.Nearest;

        assets.load("data/levels/human/level1.tmx", TiledMap.class, parameters);
        assets.finishLoading();
        TiledMap map = assets.get("data/levels/human/level1.tmx", TiledMap.class);

        ItemRoot world = new ItemRoot();
        addItems(world, map);

        return world;
    }

    private void addItems(ItemRoot world, TiledMap map)
    {
        addMapItem(world, map);
        for (MapLayer layer: map.getLayers())
        {
            addAggregateItems(world, layer);
            addObjectItems(world, layer);
        }
        addFog(world, map);
    }

    private void addMapItem(ItemRoot world, TiledMap tiledMap)
    {
        Map map = new Map((TiledMapTileLayer)tiledMap.getLayers().get(0)); //TODO Get by name
        map.setSize(1024, 1024); //TODO get dimensions from map data
        map.setPosition(0, 0);
       //map.setScale(1f);
        map.setProperty(new Identifier("Id"), new Identifier("Map"));
        map.setProperty(new Identifier("Type"), new Identifier("Map"));
        world.addItem(map);
    }

    private void addFog(ItemRoot world, TiledMap tiledMap)
    {
        Fog fog = new Fog(assets, 32, 32, 32, 32); //TODO get dimensions from map data
        world.addItem(fog);
    }

    private void addAggregateItems(ItemRoot world, MapLayer layer)
    {
        if (Objects.equals(layer.getName(), "Wood")) // TODO: swap for property aggregated=true
        {
            TiledMapTileLayer tileLayer = (TiledMapTileLayer)layer;

            for (int x = 0; x < tileLayer.getWidth(); x++)
            {
                for (int y = 0; y < tileLayer.getHeight(); ++y)
                {
                    TiledMapTileLayer.Cell cell = tileLayer.getCell(x, y);

                    if (cell != null)
                    {
                        TiledMapTile tile = cell.getTile();
                        Array<TextureRegion> textures = Array.with(tile.getTextureRegion());

                        java.util.Map<Range<Float>, Array<TextureRegion>> frames = new HashMap<Range<Float>, Array<TextureRegion>>(1);
                        frames.put(Range.between(0.0f, 360.0f), textures);

                        DirectionalAnimation animation = new DirectionalAnimation(0f, 0.15f, frames, Animation.PlayMode.LOOP);

                        Float width = tileLayer.getTileWidth();
                        Float height = tileLayer.getTileHeight();
                        Float worldX = x * width;
                        Float worldY = y * height;

                        AnimatedItem unit = (AnimatedItem)itemFactory.newItem(UnitType.Tree);
                        unit.setSize(width, height);
                        unit.setAvailableAnimation(new Identifier("Idle"), animation);
                        unit.setAvailableAnimation(new Identifier("GatherWood"), animation);
                        unit.setAnimation(new Identifier("Idle"));
                        unit.setProperty(new Identifier("Id"), new Identifier());

                        //unit.setZIndex(5);
                        unit.setPosition(worldX, worldY);

                        world.addItem(unit);
                    }
                }
            }
        }
    }

    private void addObjectItems(ItemRoot world, MapLayer layer)
    {
        for (MapObject object : layer.getObjects())
        {
            MapProperties properties = object.getProperties();
            String type = (String) properties.get("type");

            if (Objects.equals(type, "Camera")) // TODO
            {
                Float x = (Float)properties.get("x");
                Float y = (Float)properties.get("y");

                OrthographicCamera orthographicCamera = new OrthographicCamera(graphics.getWidth(), graphics.getHeight());
                orthographicCamera.setToOrtho(false, 30, 20);
                orthographicCamera.position.x = x;
                orthographicCamera.position.y = y;
                orthographicCamera.zoom = 1f;

                Camera cameraActor = new Camera(orthographicCamera);
                cameraActor.setTouchable(Touchable.disabled);
                cameraActor.setVisible(false);
                cameraActor.setProperty(new Identifier("Id"), new Identifier("Camera"));
                cameraActor.setProperty(new Identifier("Type"), new Identifier("Camera"));
                cameraActor.setProperty(new Identifier("OriginalZoom"), 0f); //TODO add default value if missing automatically

                world.addItem(cameraActor);
                world.setViewport(new ScreenViewport(orthographicCamera));
            }
            else if (Objects.equals(type, "Player")) // TODO
            {
                Float gold = (Float)properties.get("Gold");
                Float wood = (Float)properties.get("Wood");
                String id = (String)properties.get("Id");

                Item player = new Item();
                player.setTouchable(Touchable.disabled);
                player.setVisible(false);
                player.setProperty(new Identifier("Id"), new Identifier(id));
                player.setProperty(new Identifier("Type"), new Identifier("Player"));
                player.setProperty(new Identifier("Gold"), gold);
                player.setProperty(new Identifier("Wood"), wood);

                world.addItem(player);
            }
            else
            {
                Float x = (Float) properties.get("x");
                Float y = (Float) properties.get("y");
                Float width = (Float)properties.get("width");
                Float height = (Float)properties.get("height");
                String owner = (String)properties.get("Owner");
                String name = object.getName();



                Item unit = itemFactory.newItem(UnitType.valueOf(type));
                unit.setSize(width, height);
                //unit.setZIndex(10);
                unit.setPosition(x, y);
                unit.setProperty(new Identifier("Id"), new Identifier(name));

                if (owner != null)
                {
                    unit.setProperty(new Identifier("Owner"), new Identifier(owner));
                }
                world.addItem(unit);
            }
        }
    }
}
