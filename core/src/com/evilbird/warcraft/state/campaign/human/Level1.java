/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

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
import com.evilbird.engine.common.lang.NamedIdentifier;
import com.evilbird.engine.common.lang.Objects;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.item.*;
import com.evilbird.engine.item.specialized.layer.Layer;
import com.evilbird.warcraft.item.data.DataType;
import com.evilbird.warcraft.item.data.player.Player;
import com.evilbird.warcraft.item.layer.LayerType;
import com.evilbird.warcraft.item.unit.UnitType;
import com.evilbird.warcraft.item.unit.resource.ResourceType;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

//TODO: Move into serialzation library
public class Level1 implements AssetProvider<ItemRoot>
{
    private AssetManager assets;
    private ItemFactory itemFactory;

    @Inject
    public Level1(Device device, ItemFactory itemFactory) {
        this.assets = device.getAssetStorage().getAssets();
        this.itemFactory = itemFactory;
    }

    @Override
    public void load() {
    }

    @Override
    public ItemRoot get() {
        TiledMap level = loadLevel("data/levels/human/level1.tmx");
        return loadLevelItem(level);
    }

    private TiledMap loadLevel(String path) {
        TmxMapLoader.Parameters parameters = new TmxMapLoader.Parameters();
        parameters.textureMinFilter = Texture.TextureFilter.Linear;
        parameters.textureMagFilter = Texture.TextureFilter.Nearest;

        assets.load(path, TiledMap.class, parameters);
        assets.finishLoadingAsset(path);
        return assets.get(path, TiledMap.class);
    }

    private ItemRoot loadLevelItem(TiledMap level) {
        ItemRoot result = new ItemRoot(new ItemGraph(32, 32, 32, 32));
        result.setViewport(new ScreenViewport());
        addItems(level, result);
        return result;
    }

    private ItemRoot addItems(TiledMap level, ItemRoot root) {
        Map<String, ItemComposite> rootItems = getComposites(root);
        addPrimaryItems(level, rootItems);

        Map<String, ItemComposite> primaryItems = getComposites(root.getItems());
        addSecondaryItems(level, primaryItems);

        return root;
    }

    private Map<String, ItemComposite> getComposites(ItemRoot root) {
        Map<String, ItemComposite> rootItems = new HashMap<String, ItemComposite>();
        rootItems.put("root", root);
        return rootItems;
    }

    private Map<String, ItemComposite> getComposites(Collection<Item> items) {
        Map<String, ItemComposite> result = new HashMap<String, ItemComposite>();
        for (Item item : items) {
            if (item instanceof ItemComposite) {
                String identifier = item.getIdentifier().toString();
                result.put(identifier, (ItemComposite) item);
            }
        }
        return result;
    }

    private void addPrimaryItems(TiledMap level, Map<String, ItemComposite> parents) {
        for (MapLayer layer : level.getLayers()) {
            if (isPrimaryItem(layer)) {
                addItems(layer, parents);
            }
        }
    }

    private void addSecondaryItems(TiledMap level, Map<String, ItemComposite> parents) {
        for (MapLayer layer : level.getLayers()) {
            if (isSecondaryItem(layer)) {
                addItems(layer, parents);
            }
        }
    }

    private void addItems(MapLayer layer, Map<String, ItemComposite> parents) {
        if (isLayerItem(layer)) {
            addLayerItems(layer, parents);
        } else {
            addObjectItems(layer, parents);
        }
    }

    private void addLayerItems(MapLayer layer, Map<String, ItemComposite> parents) {
        Layer item = (Layer) itemFactory.newItem(LayerType.valueOf(layer.getName()));
        item.setLayer((TiledMapTileLayer) layer);

        ItemComposite parent = parents.get("root");
        parent.addItem(item);
    }

    private void addObjectItems(MapLayer layer, Map<String, ItemComposite> parents) {
        for (MapObject object : layer.getObjects()) {
            Item item = newItem(getItemType(layer), object);
            ItemComposite parent = getParent(parents, object);
            parent.addItem(item);
        }
    }

    private Item newItem(ItemType type, MapObject object) {
        Item item = createItem(type, object);
        setGeneralAttributes(item, object.getProperties());
        setCustomAttributes(item, object.getProperties());
        return item;
    }

    private Item createItem(ItemType type, MapObject object) {
        Item item = itemFactory.newItem(type);
        item.setId(new NamedIdentifier(object.getName()));
        item.setVisible(object.isVisible());
        return item;
    }

    private void setGeneralAttributes(Item item, MapProperties properties) {
        item.setTouchable(getTouchable(properties));
        item.setPosition((Float)properties.get("x"), (Float)properties.get("y"));
    }

    private void setCustomAttributes(Item item, MapProperties properties) {
        if (item instanceof Player) {
            Player player = (Player)item;
            player.setHumanPlayer(! properties.get("AI", Boolean.class));
            player.setResource(ResourceType.Gold, properties.get("Gold", Float.class));
            player.setResource(ResourceType.Oil, properties.get("Oil", Float.class));
            player.setResource(ResourceType.Wood, properties.get("Wood", Float.class));
        }
    }

    private ItemComposite getParent(Map<String, ItemComposite> parents, MapObject object) {
        MapProperties properties = object.getProperties();
        String owner = getOwner(properties);
        return parents.get(owner);
    }

    private boolean isPrimaryItem(MapLayer layer) {
        return isLayerItem(layer) || isDataItem(layer);
    }

    private boolean isSecondaryItem(MapLayer layer) {
        return !isPrimaryItem(layer);
    }

    private boolean isLayerItem(MapLayer layer) {
        return Objects.equals(layer.getName(), "Map") ||
                Objects.equals(layer.getName(), "Forest") ||
                Objects.equals(layer.getName(), "OpaqueFog") ||
                Objects.equals(layer.getName(), "TransparentFog");
    }

    private boolean isDataItem(MapLayer layer) {
        return isPlayerItem(layer) || isCameraItem(layer) ;
    }

    private boolean isPlayerItem(MapLayer layer) {
        return Objects.equals(layer.getName(), "Player");
    }

    private boolean isCameraItem(MapLayer layer) {
        return Objects.equals(layer.getName(), "Camera");
    }

    private ItemType getItemType(MapLayer layer) {
        if (isLayerItem(layer)) {
            return LayerType.valueOf(layer.getName());
        }
        if (isDataItem(layer)) {
            return DataType.valueOf(layer.getName());
        }
        return UnitType.valueOf(layer.getName());
    }

    private Touchable getTouchable(MapProperties properties) {
        Boolean touchable = (Boolean) properties.get("Touchable");
        return touchable == Boolean.FALSE ? Touchable.childrenOnly : Touchable.enabled;
    }

    private String getOwner(MapProperties properties) {
        String owner = (String) properties.get("Owner");
        return owner == null ? "root" : owner;
    }
}
