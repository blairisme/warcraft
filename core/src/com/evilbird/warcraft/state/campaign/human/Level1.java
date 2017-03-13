package com.evilbird.warcraft.state.campaign.human;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.evilbird.engine.common.inject.AssetProvider;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.common.lang.Objects;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemComposite;
import com.evilbird.engine.item.ItemFactory;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.engine.item.ItemType;
import com.evilbird.engine.item.specialized.Layer;
import com.evilbird.warcraft.item.data.DataType;
import com.evilbird.warcraft.item.layer.LayerType;
import com.evilbird.warcraft.item.unit.UnitType;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

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
        TiledMap level = loadLevel("data/levels/human/level1.tmx");
        return loadLevelItem(level);
    }

    private TiledMap loadLevel(String path)
    {
        TmxMapLoader.Parameters parameters = new TmxMapLoader.Parameters();
        parameters.textureMinFilter = Texture.TextureFilter.Linear;
        parameters.textureMagFilter = Texture.TextureFilter.Nearest;

        assets.load(path, TiledMap.class, parameters);
        assets.finishLoadingAsset(path);
        return assets.get(path, TiledMap.class);
    }

    private ItemRoot loadLevelItem(TiledMap level)
    {
        ItemRoot result = new ItemRoot();
        result.setViewport(new ScreenViewport());
        addItems(level, result);
        return result;
    }

    private ItemRoot addItems(TiledMap level, ItemRoot root)
    {
        Map<String, ItemComposite> rootItems = getComposites(root);
        addPrimaryItems(level, rootItems);

        Map<String, ItemComposite> primaryItems = getComposites(root.getItems());
        addSecondaryItems(level, primaryItems);

        return root;
    }

    private Map<String, ItemComposite> getComposites(ItemRoot root)
    {
        Map<String, ItemComposite> rootItems = new HashMap<String, ItemComposite>();
        rootItems.put("root", root);
        return rootItems;
    }

    private Map<String, ItemComposite> getComposites(Collection<Item> items)
    {
        Map<String, ItemComposite> result = new HashMap<String, ItemComposite>();
        for (Item item: items){
            if (item instanceof ItemComposite){
                String identifier = item.getId().toString();
                result.put(identifier, (ItemComposite)item);
            }
        }
        return result;
    }

    private void addPrimaryItems(TiledMap level, Map<String, ItemComposite> parents)
    {
        for (MapLayer layer: level.getLayers()){
            if (isPrimaryItem(layer)){
                addItems(layer, parents);
            }
        }
    }

    private void addSecondaryItems(TiledMap level, Map<String, ItemComposite> parents)
    {
        for (MapLayer layer: level.getLayers()){
            if (isSecondaryItem(layer)){
                addItems(layer, parents);
            }
        }
    }

    private void addItems(MapLayer layer, Map<String, ItemComposite> parents)
    {
        if (isLayerItem(layer)){
            addLayerItems(layer, parents);
        }
        else{
            addObjectItems(layer, parents);
        }
    }

    private void addLayerItems(MapLayer layer, Map<String, ItemComposite> parents)
    {
        Layer item = (Layer)itemFactory.newItem(LayerType.valueOf(layer.getName()));
        item.setLayer((TiledMapTileLayer)layer);

        ItemComposite parent = parents.get("root");
        parent.addItem(item);
    }

    private void addObjectItems(MapLayer layer, Map<String, ItemComposite> parents)
    {
        for (MapObject object : layer.getObjects())
        {
            MapProperties properties = object.getProperties();

            Item item = itemFactory.newItem(getItemType(layer));
            item.setId(new Identifier(object.getName()));
            item.setVisible(object.isVisible());
            item.setTouchable(getTouchable(properties));
            item.setSize((Float)properties.get("width"), (Float)properties.get("height"));
            item.setPosition((Float)properties.get("x"), (Float)properties.get("y"));

            ItemComposite parent = parents.get(getOwner(properties));
            parent.addItem(item);
        }
    }

    private boolean isPrimaryItem(MapLayer layer)
    {
        return isLayerItem(layer) || isDataItem(layer);
    }

    private boolean isSecondaryItem(MapLayer layer)
    {
        return ! isPrimaryItem(layer);
    }

    private boolean isLayerItem(MapLayer layer)
    {
        return Objects.equals(layer.getName(), "Map") ||
               Objects.equals(layer.getName(), "OpaqueFog") ||
                Objects.equals(layer.getName(), "Wood");
    }

    private boolean isDataItem(MapLayer layer)
    {
        return Objects.equals(layer.getName(), "Player") ||
               Objects.equals(layer.getName(), "Camera");
    }

    private ItemType getItemType(MapLayer layer)
    {
        if (isLayerItem(layer)){
            return LayerType.valueOf(layer.getName());
        }
        if (isDataItem(layer)){
            return DataType.valueOf(layer.getName());
        }
        return UnitType.valueOf(layer.getName());
    }

    private Touchable getTouchable(MapProperties properties)
    {
        Boolean touchable = (Boolean)properties.get("Touchable");
        return touchable == Boolean.FALSE ? Touchable.childrenOnly : Touchable.enabled;
    }

    private String getOwner(MapProperties properties)
    {
        String owner = (String)properties.get("Owner");
        return owner == null ? "root" : owner;
    }



    /*
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

                        Unit unit = (Unit)itemFactory.newItem(ResourceType.Tree);
                        unit.setSize(width, height);
                        unit.setAvailableAnimation(new Identifier("Idle"), animation);
                        unit.setAvailableAnimation(new Identifier("GatherWood"), animation);
                        unit.setAnimation(new Identifier("Idle"));
                        unit.setId(new Identifier());
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

                Camera camera = (Camera)itemFactory.newItem(DataType.Camera);
                camera.setPosition(x, y);

                world.addItem(camera);
                world.setViewport(new ScreenViewport(camera.asOrthographicCamera()));
            }
            else if (Objects.equals(type, "Player")) // TODO
            {
                Float gold = (Float)properties.get("Gold");
                Float wood = (Float)properties.get("Wood");
                String name = object.getName();
                Boolean ai = (Boolean)properties.get("AI");
                String betterType = ai == Boolean.TRUE ? "CurrentPlayer" : "AIPlayer";

                Unit player = new Unit();
                player.setTouchable(Touchable.disabled);
                player.setVisible(false);
                player.setId(new Identifier(name));
                player.setType(new Identifier(betterType));

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

                Unit unit = (Unit)itemFactory.newItem(UnitType.valueOf(type));
                unit.setSize(width, height);
                unit.setPosition(x, y);
                unit.setId(new Identifier(name));

                world.addItem(unit);
            }
        }
    }
    */
}
