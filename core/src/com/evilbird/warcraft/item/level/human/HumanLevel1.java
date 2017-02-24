package com.evilbird.warcraft.item.level.human;

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
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.graphics.DirectionalAnimation;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemFactory;
import com.evilbird.engine.item.ItemGroup;
import com.evilbird.engine.utility.AssetObjectProvider;
import com.evilbird.engine.utility.Identifier;
import com.evilbird.warcraft.item.camera.Camera;
import com.evilbird.warcraft.item.layer.Fog;
import com.evilbird.warcraft.item.layer.Map;
import com.evilbird.warcraft.item.unit.Units;
import com.evilbird.warcraft.item.unit.common.AnimatedItem;

import org.apache.commons.lang3.Range;

import java.util.HashMap;
import java.util.Objects;

import javax.inject.Inject;

import static com.badlogic.gdx.Gdx.graphics;

public class HumanLevel1 implements AssetObjectProvider<ItemGroup>
{
    private AssetManager assets;
    private ItemFactory itemFactory;

    @Inject
    public HumanLevel1(Device device)
    {
        this.assets = device.getAssetStorage().getAssets();
    }

    public void setItemFactory(ItemFactory itemFactory)
    {
        this.itemFactory = itemFactory;
    }

    @Override
    public void load()
    {
    }

    @Override
    public ItemGroup get()
    {
        TmxMapLoader.Parameters parameters = new TmxMapLoader.Parameters();
        parameters.textureMinFilter = Texture.TextureFilter.Linear;
        parameters.textureMagFilter = Texture.TextureFilter.Nearest;

        assets.load("data/levels/human/level1.tmx", TiledMap.class, parameters);
        assets.finishLoading();
        TiledMap map = assets.get("data/levels/human/level1.tmx", TiledMap.class);

        ItemGroup world = new ItemGroup();
        addItems(world, map);

        return world;
    }

    private void addItems(Stage stage, TiledMap map)
    {
        addMapItem(stage, map);
        for (MapLayer layer: map.getLayers())
        {
            addAggregateItems(stage, layer);
            addObjectItems(stage, layer);
        }
        addFog(stage, map);
    }

    private void addMapItem(Stage stage, TiledMap tiledMap)
    {
        Map map = new Map((TiledMapTileLayer)tiledMap.getLayers().get(0)); //TODO Get by name
        map.setSize(1024, 1024); //TODO get dimensions from map data
        map.setPosition(0, 0);
        map.setScale(1f);
        map.setProperty(new Identifier("Id"), new Identifier("Map"));
        map.setProperty(new Identifier("Type"), new Identifier("Map"));
        stage.addActor(map);
    }

    private void addFog(Stage stage, TiledMap tiledMap)
    {
        Fog fog = new Fog(assets, 32, 32, 32, 32); //TODO get dimensions from map data
        stage.addActor(fog);
    }

    private void addAggregateItems(Stage stage, MapLayer layer)
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

                        AnimatedItem unit = (AnimatedItem)itemFactory.newItem(Units.Tree);
                        unit.setSize(width, height);
                        unit.setAnimationDefinition(new Identifier("Idle"), animation);
                        unit.setAnimationDefinition(new Identifier("GatherWood"), animation);
                        unit.setAnimation(new Identifier("Idle"));
                        unit.setProperty(new Identifier("Id"), new Identifier());

                        //unit.setZIndex(5);
                        unit.setPosition(worldX, worldY);

                        stage.addActor(unit);
                    }
                }
            }
        }
    }

    private void addObjectItems(Stage stage, MapLayer layer)
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

                stage.addActor(cameraActor);
                stage.setViewport(new ScreenViewport(orthographicCamera));
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

                stage.addActor(player);
            }
            else
            {
                Float x = (Float) properties.get("x");
                Float y = (Float) properties.get("y");
                Float width = (Float)properties.get("width");
                Float height = (Float)properties.get("height");
                String owner = (String)properties.get("Owner");
                String id = (String)properties.get("Name");

                Item unit = itemFactory.newItem(Units.valueOf(type));
                unit.setSize(width, height);
                //unit.setZIndex(10);
                unit.setPosition(x, y);
                unit.setProperty(new Identifier("Id"), new Identifier());

                if (owner != null)
                {
                    unit.setProperty(new Identifier("Owner"), new Identifier(owner));
                }
                stage.addActor(unit);
            }
        }
    }
}
