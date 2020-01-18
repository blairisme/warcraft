/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
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
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectContainer;
import com.evilbird.engine.object.GameObjectFactory;
import com.evilbird.engine.object.GameObjectGroup;
import com.evilbird.engine.object.GameObjectType;
import com.evilbird.engine.object.spatial.GameObjectGraph;
import com.evilbird.warcraft.common.TeamColour;
import com.evilbird.warcraft.common.WarcraftFaction;
import com.evilbird.warcraft.common.WarcraftNation;
import com.evilbird.warcraft.data.resource.ResourceContainer;
import com.evilbird.warcraft.data.resource.ResourceType;
import com.evilbird.warcraft.object.data.camera.CameraType;
import com.evilbird.warcraft.object.data.player.Player;
import com.evilbird.warcraft.object.data.player.PlayerType;
import com.evilbird.warcraft.object.layer.LayerIdentifier;
import com.evilbird.warcraft.object.layer.LayerType;
import com.evilbird.warcraft.object.unit.UnitType;
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

import static com.evilbird.engine.object.GameObjectContainerType.World;
import static com.evilbird.warcraft.data.resource.ResourceType.Food;
import static com.evilbird.warcraft.data.resource.ResourceType.Gold;
import static com.evilbird.warcraft.data.resource.ResourceType.Oil;
import static com.evilbird.warcraft.data.resource.ResourceType.Wood;

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

    private static final String TOUCHABLE_PROPERTY = "Touchable";
    private static final String X_PROPERTY = "x";
    private static final String Y_PROPERTY = "y";
    private static final String GOLD_PROPERTY = "Gold";
    private static final String OIL_PROPERTY = "Oil";
    private static final String WOOD_PROPERTY = "Wood";
    private static final String FOOD_PROPERTY = "Food";
    private static final String LEVEL_PROPERTY = "Level";
    private static final String TYPE_PROPERTY = "type";
    private static final String NATION_PROPERTY = "Nation";
    private static final String FACTION_PROPERTY = "Faction";
    private static final String PARENT_PROPERTY = "Parent";
    private static final String TEAM_PROPERTY = "Team";
    private static final String COLOUR_PROPERTY = "Colour";
    private static final String CAPTURABLE = "Capturable";
    private static final String CONTROLLABLE = "Controllable";
    private static final String PASSIVE = "Passive";
    private static final String VIEWABLE = "Viewable";

    private TiledMapLoader mapLoader;
    private GameObjectFactory objectFactory;

    @Inject
    public LevelLoader(Device device, GameObjectFactory objectFactory) {
        this(objectFactory, device.getAssetStorage());
    }

    public LevelLoader(GameObjectFactory objectFactory, AssetManager assetManager) {
        this.objectFactory = objectFactory;
        this.mapLoader = new TiledMapLoader(assetManager);
    }

    public GameObjectContainer load(Level level) {
        return load(level.getFilePath());
    }

    public GameObjectContainer load(String path) {
        TiledMapFile level = mapLoader.load(path);
        return load(level);
    }

    private GameObjectContainer load(TiledMapFile map) {
        GridPoint2 mapSize = map.getMapSize();
        GridPoint2 tileSize = map.getTileSize();

        GameObjectContainer result = new GameObjectContainer();
        result.setIdentifier(World);
        result.setViewport(new ScreenViewport());
        result.setSpatialGraph(new GameObjectGraph(tileSize.x, tileSize.y, mapSize.x, mapSize.y));
        result.addObjects(assignParents(getItems(map)));

        return result;
    }

    private Map<MapLayer, GameObject> getItems(TiledMapFile map) {
        Map<MapLayer, GameObject> result = new LinkedHashMap<>();
        for (MapLayer layer: map.getLayers()) {
            GameObject gameObject = getItem(map, layer);
            if (gameObject != null) {
                result.put(layer, gameObject);
            }
        }
        return result;
    }

    private Collection<GameObject> assignParents(Map<MapLayer, GameObject> layerItems) {
        Map<String, GameObject> parents = getParents(layerItems);
        for (Entry<MapLayer, GameObject> layerItem: layerItems.entrySet()) {
            MapLayer layer = layerItem.getKey();
            if (hasParentOverride(layer)) {
                String parentId = getParentOverride(layer);
                GameObjectGroup parent = (GameObjectGroup)parents.get(parentId);
                if (parent != null) {
                    parent.addObject(layerItem.getValue());
                } else {
                    logger.warn("Unknown parent: {}", parentId);
                }
            }
        }
        return parents.values();
    }

    private Map<String, GameObject> getParents(Map<MapLayer, GameObject> layerItems) {
        Map<String, GameObject> result = new LinkedHashMap<>(layerItems.size());
        for (Entry<MapLayer, GameObject> layerItem: layerItems.entrySet()) {
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

    private GameObject getItem(TiledMapFile map, MapLayer layer) {
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

    private GameObject getLayerItem(TiledMapFile map, MapLayer layer) {
        TiledMapTileLayer mapLayer = (TiledMapTileLayer)layer;

//        //TODO
//        if (layer.getName().equals("TransparentFog")) {
//            mapLayer = (TiledMapTileLayer)map.getLayers().get("Map");
//        }

        LayerIdentifier identifier = new LayerIdentifier(map.getFile(), layer.getName(), mapLayer);
        return objectFactory.get(identifier);
    }

    private GameObject getPlayerItem(MapLayer layer) {
        PlayerType type = getPlayerType(layer);
        if (type != null) {
            return getPlayerItem(type, layer);
        }
        else {
            logger.warn("Unknown player type: {}", layer.getName());
            return null;
        }
    }

    private Player getPlayerItem(PlayerType type, MapLayer layer) {
        MapProperties properties = layer.getProperties();
        Player player = (Player) objectFactory.get(type);
        player.setIdentifier(new TextIdentifier(layer.getName()));
        player.setLevel(getInt(properties, LEVEL_PROPERTY));
        player.setTeam(getInt(properties, TEAM_PROPERTY));
        player.setCapturable(getBoolean(properties, CAPTURABLE));
        player.setControllable(getBoolean(properties, CONTROLLABLE));
        player.setPassive(getBoolean(properties, PASSIVE));
        player.setViewable(getBoolean(properties, VIEWABLE));
        player.setColour(getEnum(properties, COLOUR_PROPERTY, TeamColour.class, TeamColour.None));
        player.setNation(getEnum(properties, NATION_PROPERTY, WarcraftNation.class, WarcraftNation.Unknown));
        player.setFaction(getEnum(properties, FACTION_PROPERTY, WarcraftFaction.class, WarcraftFaction.Neutral));
        player.setResource(Gold, getFloat(properties, GOLD_PROPERTY));
        player.setResource(Oil, getFloat(properties, OIL_PROPERTY));
        player.setResource(Wood, getFloat(properties, WOOD_PROPERTY));
        player.setResource(Food, getFloat(properties, FOOD_PROPERTY));
        player.addObjects(getObjectItems(layer.getObjects()));
        return player;
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

    private Collection<GameObject> getObjectItems(MapObjects objects) {
        Collection<GameObject> result = new ArrayList<>();
        for (MapObject object: objects) {
            GameObject gameObject = getObjectItem(object);
            if (gameObject != null) {
                result.add(gameObject);
            }
        }
        return result;
    }

    private GameObject getObjectItem(MapObject object) {
        MapProperties properties = object.getProperties();
        GameObjectType type = getItemType(properties);

        if (type != null) {
            GameObject gameObject = objectFactory.get(type);
            setIdentityProperties(gameObject, object);
            setInteractionProperties(gameObject, properties);
            setResourceProperties(gameObject, properties);
            return gameObject;
        }
        else {
            logger.warn("Unknown object type: {}", object.getName());
            return null;
        }
    }

    private GameObjectType getItemType(MapProperties properties) {
        String type = properties.get(TYPE_PROPERTY, "", String.class);

        if (EnumUtils.isValidEnumIgnoreCase(CameraType.class, type)) {
            return EnumUtils.getEnum(CameraType.class, type);
        }
        else if (EnumUtils.isValidEnumIgnoreCase(UnitType.class, type)) {
            return EnumUtils.getEnum(UnitType.class, type);
        }
        return null;
    }

    private void setIdentityProperties(GameObject gameObject, MapObject object) {
        gameObject.setIdentifier(new TextIdentifier(object.getName()));
        gameObject.setVisible(object.isVisible());
    }

    private void setInteractionProperties(GameObject gameObject, MapProperties properties) {
        gameObject.setTouchable(getTouchable(properties, TOUCHABLE_PROPERTY));
        gameObject.setPosition(getVector(properties, X_PROPERTY, Y_PROPERTY));
    }

    private void setResourceProperties(GameObject gameObject, MapProperties properties) {
        if (gameObject instanceof ResourceContainer) {
            ResourceContainer container = (ResourceContainer) gameObject;
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

    private boolean getBoolean(MapProperties properties, String key) {
        return properties.get(key, false, Boolean.class);
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