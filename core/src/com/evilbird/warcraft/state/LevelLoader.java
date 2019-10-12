/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.state;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.evilbird.engine.common.lang.TextIdentifier;
import com.evilbird.engine.common.maps.TiledMapFile;
import com.evilbird.engine.common.maps.TiledMapLoader;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemFactory;
import com.evilbird.engine.item.ItemGroup;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.engine.item.ItemType;
import com.evilbird.engine.item.spatial.ItemGraph;
import com.evilbird.warcraft.common.TeamColour;
import com.evilbird.warcraft.common.WarcraftFaction;
import com.evilbird.warcraft.common.WarcraftNation;
import com.evilbird.warcraft.item.common.resource.ResourceContainer;
import com.evilbird.warcraft.item.common.resource.ResourceType;
import com.evilbird.warcraft.item.data.camera.CameraType;
import com.evilbird.warcraft.item.data.player.Player;
import com.evilbird.warcraft.item.data.player.PlayerType;
import com.evilbird.warcraft.item.layer.LayerIdentifier;
import com.evilbird.warcraft.item.layer.LayerType;
import com.evilbird.warcraft.item.unit.UnitType;
import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import static com.evilbird.warcraft.item.common.resource.ResourceType.Food;
import static com.evilbird.warcraft.item.common.resource.ResourceType.Gold;
import static com.evilbird.warcraft.item.common.resource.ResourceType.Oil;
import static com.evilbird.warcraft.item.common.resource.ResourceType.Wood;

/**
 * Instances of this class load persisted game state from files. Game state is
 * stored in TMX format.
 *
 * @author Blair Butterworth
 */
public class LevelLoader
{
    private static final Logger logger = LoggerFactory.getLogger(LevelLoader.class);

    private static final String CORPOREAL_PLAYER_ID = "Player1";
    private static final String ENEMY_PLAYER_ID = "Player";
    private static final String NEUTRAL_PLAYER_ID = "Neutral";
    private static final String WORLD_ID = "world";

    private static final String TOUCHABLE_PROPERTY = "Touchable";
    private static final String X_PROPERTY = "x";
    private static final String Y_PROPERTY = "y";
    private static final String GOLD_PROPERTY = "Gold";
    private static final String OIL_PROPERTY = "Oil";
    private static final String WOOD_PROPERTY = "Wood";
    private static final String FOOD_PROPERTY = "Food";
    private static final String LEVEL_PROPERTY = "Level";
    private static final String ZINDEX_PROPERTY = "ZIndex";
    private static final String TYPE_PROPERTY = "type";
    private static final String NATION_PROPERTY = "Nation";
    private static final String FACTION_PROPERTY = "Faction";
    private static final String PARENT_PROPERTY = "Parent";
    private static final String TEAM_PROPERTY = "Team";
    private static final String COLOUR_PROPERTY = "Colour";
    
    private TiledMapLoader mapLoader;
    private ItemFactory itemFactory;

    @Inject
    public LevelLoader(Device device, ItemFactory itemFactory) {
        this(itemFactory, device.getAssetStorage());
    }

    public LevelLoader(ItemFactory itemFactory, AssetManager assetManager) {
        this.itemFactory = itemFactory;
        this.mapLoader = new TiledMapLoader(assetManager);
    }

    public ItemRoot load(String path) {
        TiledMapFile level = mapLoader.load(path);
        return load(level);
    }

    private ItemRoot load(TiledMapFile map) {
        GridPoint2 mapSize = map.getMapSize();
        GridPoint2 tileSize = map.getTileSize();

        ItemRoot result = new ItemRoot();
        result.setIdentifier(new TextIdentifier(WORLD_ID));
        result.setViewport(new ScreenViewport());
        result.setSpatialGraph(new ItemGraph(tileSize.x, tileSize.y, mapSize.x, mapSize.y));
        result.addItems(assignParents(getItems(map)));

        return result;
    }

    private Map<MapLayer, Item> getItems(TiledMapFile map) {
        Map<MapLayer, Item> result = new LinkedHashMap<>();
        for (MapLayer layer: map.getLayers()) {
            Item item = getItem(map, layer);
            if (item != null) {
                result.put(layer, item);
            }
        }
        return result;
    }

    private Collection<Item> assignParents(Map<MapLayer, Item> layerItems) {
        Map<String, Item> parents = getParents(layerItems);
        for (Entry<MapLayer, Item> layerItem: layerItems.entrySet()) {
            MapLayer layer = layerItem.getKey();
            if (hasParentOverride(layer)) {
                String parentId = getParentOverride(layer);
                ItemGroup parent = (ItemGroup)parents.get(parentId);
                if (parent != null) {
                    parent.addItem(layerItem.getValue());
                } else {
                    logger.warn("Unknown parent: {}", parentId);
                }
            }
        }
        return parents.values();
    }

    private Map<String, Item> getParents(Map<MapLayer, Item> layerItems) {
        Map<String, Item> result = new LinkedHashMap<>(layerItems.size());
        for (Entry<MapLayer, Item> layerItem: layerItems.entrySet()) {
            MapLayer layer = layerItem.getKey();
            if (!hasParentOverride(layer)) {
                result.put(layer.getName(), layerItem.getValue());
            }
        }
        return result;
    }

    private boolean hasParentOverride(MapLayer layer) {
        MapProperties properties = layer.getProperties();
        return properties.containsKey(PARENT_PROPERTY);
    }

    private String getParentOverride(MapLayer layer) {
        MapProperties properties = layer.getProperties();
        return getString(properties, PARENT_PROPERTY);
    }

    private Item getItem(TiledMapFile map, MapLayer layer) {
        if (isLayerItem(layer)) {
            return getLayerItem(map, layer);
        }
        else if (isPlayerItem(layer)) {
            return getPlayerItem(layer);
        }
        else {
            logger.warn("Unknown top level type: {}", layer.getName());
            return null;
        }
    }

    private boolean isLayerItem(MapLayer layer) {
        return EnumUtils.isValidEnumIgnoreCase(LayerType.class, layer.getName());
    }

    private boolean isPlayerItem(MapLayer layer) {
        return getPlayerType(layer) != null;
    }

    private Item getLayerItem(TiledMapFile map, MapLayer layer) {
        TiledMapTileLayer mapLayer = (TiledMapTileLayer)layer;
        LayerIdentifier identifier = new LayerIdentifier(map.getFile(), layer.getName(), mapLayer);
        return itemFactory.get(identifier);
    }

    private Item getPlayerItem(MapLayer layer) {
        MapProperties properties = layer.getProperties();
        PlayerType type = getPlayerType(layer);

        if (type != null) {
            Player player = (Player)itemFactory.get(type);
            player.setIdentifier(new TextIdentifier(layer.getName()));
            player.setLevel(getInt(properties, LEVEL_PROPERTY));
            player.setTeam(getInt(properties, TEAM_PROPERTY));
            player.setColour(getEnum(properties, COLOUR_PROPERTY, TeamColour.class, TeamColour.None));
            player.setNation(getEnum(properties, NATION_PROPERTY, WarcraftNation.class, WarcraftNation.Unknown));
            player.setFaction(getEnum(properties, FACTION_PROPERTY, WarcraftFaction.class, WarcraftFaction.Neutral));
            player.setResource(Gold, getFloat(properties, GOLD_PROPERTY));
            player.setResource(Oil, getFloat(properties, OIL_PROPERTY));
            player.setResource(Wood, getFloat(properties, WOOD_PROPERTY));
            player.setResource(Food, getFloat(properties, FOOD_PROPERTY));
            player.addItems(getObjectItems(layer.getObjects()));
            return player;
        }
        else {
            logger.warn("Unknown player type: {}", layer.getName());
            return null;
        }
    }

    private PlayerType getPlayerType(MapLayer layer) {
        String type = StringUtils.defaultString(layer.getName());

        if (type.equals(NEUTRAL_PLAYER_ID)) {
            return PlayerType.Neutral;
        }
        else if (type.equals(CORPOREAL_PLAYER_ID)) {
            return PlayerType.Corporeal;
        }
        else if (type.startsWith(ENEMY_PLAYER_ID)) {
            return PlayerType.Artificial;
        }
        return null;
    }

    private Collection<Item> getObjectItems(MapObjects objects) {
        Collection<Item> result = new ArrayList<>();
        for (MapObject object: objects) {
            Item item = getObjectItem(object);
            if (item != null) {
                result.add(item);
            }
        }
        return result;
    }

    private Item getObjectItem(MapObject object) {
        MapProperties properties = object.getProperties();
        ItemType type = getItemType(properties);

        if (type != null) {
            Item item = itemFactory.get(type);
            setIdentityProperties(item, object);
            setInteractionProperties(item, properties);
            setResourceProperties(item, properties);
            return item;
        }
        else {
            logger.warn("Unknown object type: {}", object.getName());
            return null;
        }
    }

    private ItemType getItemType(MapProperties properties) {
        String type = properties.get(TYPE_PROPERTY, "", String.class);

        if (EnumUtils.isValidEnumIgnoreCase(CameraType.class, type)) {
            return EnumUtils.getEnum(CameraType.class, type);
        }
        else if (EnumUtils.isValidEnumIgnoreCase(UnitType.class, type)) {
            return EnumUtils.getEnum(UnitType.class, type);
        }
        return null;
    }

    private void setIdentityProperties(Item item, MapObject object) {
        item.setIdentifier(new TextIdentifier(object.getName()));
        item.setVisible(object.isVisible());
    }

    private void setInteractionProperties(Item item, MapProperties properties) {
        item.setTouchable(getTouchable(properties, TOUCHABLE_PROPERTY));
        item.setPosition(getVector(properties, X_PROPERTY, Y_PROPERTY));
        item.setZIndex(getInt(properties, ZINDEX_PROPERTY));
    }

    private void setResourceProperties(Item item, MapProperties properties) {
        if (item instanceof ResourceContainer) {
            ResourceContainer container = (ResourceContainer)item;
            setResource(container, Gold, properties, GOLD_PROPERTY);
            setResource(container, Oil, properties, OIL_PROPERTY);
            setResource(container, Wood, properties, WOOD_PROPERTY);
            setResource(container, Food, properties, FOOD_PROPERTY);
        }
    }

    private void setResource(ResourceContainer container, ResourceType resource, MapProperties properties, String key) {
        if (properties.containsKey(key)) {
            container.setResource(resource, getFloat(properties, key));
        }
    }

    private int getInt(MapProperties properties, String key) {
        return properties.get(key, 0, Integer.class);
    }

    public <T extends Enum<T>> T getEnum(MapProperties properties, String key, Class<T> type, T def) {
        String name = properties.get(key, null, String.class);
        return name != null ? Enum.valueOf(type, name) : def;
    }

    private float getFloat(MapProperties properties, String key) {
        return properties.get(key, 0f, Float.class);
    }

    public String getString(MapProperties properties, String key) {
        return properties.get(key, "", String.class);
    }

    private Vector2 getVector(MapProperties properties, String xKey, String yKey) {
        Vector2 result = new Vector2();
        result.x = properties.get(xKey, 0f, Float.class);
        result.y = properties.get(yKey, 0f, Float.class);
        return result;
    }

    private Touchable getTouchable(MapProperties properties, String key) {
        Boolean touchable = (Boolean)properties.get(key);
        return touchable == Boolean.FALSE ? Touchable.childrenOnly : Touchable.enabled;
    }
}