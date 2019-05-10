/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.state;

import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.evilbird.engine.common.lang.TextIdentifier;
import com.evilbird.engine.common.maps.TiledMapFile;
import com.evilbird.engine.common.maps.TiledMapLoader;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemComposite;
import com.evilbird.engine.item.ItemFactory;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.engine.item.ItemType;
import com.evilbird.engine.item.spatial.ItemGraph;
import com.evilbird.warcraft.item.common.resource.ResourceType;
import com.evilbird.warcraft.item.data.DataType;
import com.evilbird.warcraft.item.data.player.Player;
import com.evilbird.warcraft.item.layer.LayerIdentifier;
import com.evilbird.warcraft.item.unit.UnitType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Instances of this class load persisted game state from files. Game state is
 * stored in TMX format.
 *
 * @author Blair Butterworth
 */
public class WarcraftStateFileLoader
{
    private static final Logger logger = LoggerFactory.getLogger(WarcraftStateFileLoader.class);

    private static final String ROOT_ID = "root";
    private static final String PLAYER_ID = "Player";
    private static final String CAMERA_ID = "Camera";
    private static final String FOREST_ID = "Forest";
    private static final String TERRAIN_ID = "Map";
    private static final String OPAQUE_FOG_ID = "OpaqueFog";
    private static final String TRANSPARENT_FOG_ID = "TransparentFog";

    private static final String AI_PROPERTY = "AI";
    private static final String OWNER_PROPERTY = "Owner";
    private static final String DESCRIPTION_PROPERTY = "Description";
    private static final String TOUCHABLE_PROPERTY = "Touchable";
    private static final String POSITION_X_PROPERTY = "x";
    private static final String POSITION_Y_PROPERTY = "y";
    private static final String GOLD_PROPERTY = "Gold";
    private static final String OIL_PROPERTY = "Oil";
    private static final String WOOD_PROPERTY = "Wood";
    private static final String ZINDEX_PROPERTY = "ZIndex";

    private TiledMapLoader mapLoader;
    private ItemFactory itemFactory;

    public WarcraftStateFileLoader(ItemFactory itemFactory) {
        this(itemFactory, new TiledMapLoader());
    }

    public WarcraftStateFileLoader(ItemFactory itemFactory, FileHandleResolver fileResolver) {
        this(itemFactory, new TiledMapLoader(fileResolver));
    }

    @Inject
    public WarcraftStateFileLoader(ItemFactory itemFactory, TiledMapLoader mapLoader) {
        this.mapLoader = mapLoader;
        this.itemFactory = itemFactory;
    }

    public ItemRoot load(WarcraftStateAsset identifier) {
        return load(identifier.getFilePath());
    }

    public ItemRoot load(String path) {
        TiledMapFile level = mapLoader.load(path);
        return getItem(level);
    }

    private ItemRoot getItem(TiledMapFile level) {
        GridPoint2 mapSize = level.getMapSize();
        GridPoint2 tileSize = level.getTileSize();

        ItemRoot result = new ItemRoot();
        result.setIdentifier(new TextIdentifier("world"));
        result.setViewport(new ScreenViewport());
        result.setSpatialGraph(new ItemGraph(tileSize.x, tileSize.y, mapSize.x, mapSize.y));

        addPrimaryItems(level, getComposites(result));
        addSecondaryItems(level, getComposites(result.getItems()));

        return result;
    }

    private Map<String, ItemComposite> getComposites(ItemRoot root) {
        Map<String, ItemComposite> rootItems = new HashMap<>();
        rootItems.put(ROOT_ID, root);
        return rootItems;
    }

    private Map<String, ItemComposite> getComposites(Collection<Item> items) {
        Map<String, ItemComposite> result = new HashMap<>();
        for (Item item : items) {
            if (item instanceof ItemComposite) {
                result.put(item.getIdentifier().toString(), (ItemComposite)item);
            }
        }
        return result;
    }

    private void addPrimaryItems(TiledMapFile level, Map<String, ItemComposite> parents) {
        for (MapLayer layer : level.getLayers()) {
            if (isPrimaryItem(layer)) {
                addItems(level, layer, parents);
            }
        }
    }

    private void addSecondaryItems(TiledMapFile level, Map<String, ItemComposite> parents) {
        for (MapLayer layer : level.getLayers()) {
            if (isSecondaryItem(layer)) {
                addItems(level, layer, parents);
            }
        }
    }

    private void addItems(TiledMapFile map, MapLayer layer, Map<String, ItemComposite> parents) {
        if (isLayerItem(layer)) {
            addLayerItems(map, (TiledMapTileLayer)layer, parents);
        } else {
            addObjectItems(layer, parents);
        }
    }

    private void addLayerItems(TiledMapFile map, TiledMapTileLayer layer, Map<String, ItemComposite> parents) {
        LayerIdentifier layerIdentifier = new LayerIdentifier(map.getFile(), layer.getName(), layer);
        Item item = itemFactory.newItem(layerIdentifier);

        ItemComposite parent = parents.get(ROOT_ID);
        parent.addItem(item);
    }

    private void addObjectItems(MapLayer layer, Map<String, ItemComposite> parents) {
        for (MapObject object : layer.getObjects()) {
            ItemType type = getObjectItemType(layer);
            Item item = newItem(type, object);
            ItemComposite parent = getParent(parents, object);

            if (parent != null) {
                parent.addItem(item);
            }
        }
    }

    private ItemType getObjectItemType(MapLayer layer) {
        if (isDataItem(layer)) {
            return DataType.valueOf(layer.getName());
        }
        return UnitType.valueOf(layer.getName());
    }

    private Item newItem(ItemType type, MapObject object) {
        Item item = createItem(type, object);
        setGeneralAttributes(item, object.getProperties());
        setCustomAttributes(item, object.getProperties());
        return item;
    }

    private Item createItem(ItemType type, MapObject object) {
        Item item = itemFactory.newItem(type);
        item.setIdentifier(new TextIdentifier(object.getName()));
        item.setVisible(object.isVisible());
        return item;
    }

    private void setGeneralAttributes(Item item, MapProperties properties) {
        item.setTouchable(getTouchable(properties));
        item.setPosition((Float)properties.get(POSITION_X_PROPERTY), (Float)properties.get(POSITION_Y_PROPERTY));

        if (properties.containsKey(ZINDEX_PROPERTY)) {
            item.setZIndex((Integer)properties.get(ZINDEX_PROPERTY));
        }
    }

    private void setCustomAttributes(Item item, MapProperties properties) {
        if (item instanceof Player) {
            Player player = (Player)item;
            player.setCorporeal(! properties.get(AI_PROPERTY, Boolean.class));
            player.setDescription(properties.get(DESCRIPTION_PROPERTY, String.class));
            player.setResource(ResourceType.Gold, properties.get(GOLD_PROPERTY, Float.class));
            player.setResource(ResourceType.Oil, properties.get(OIL_PROPERTY, Float.class));
            player.setResource(ResourceType.Wood, properties.get(WOOD_PROPERTY, Float.class));
        }
    }

    private ItemComposite getParent(Map<String, ItemComposite> parents, MapObject object) {
        MapProperties properties = object.getProperties();
        String owner = getOwner(properties);
        ItemComposite result = parents.get(owner);

        if (result == null) {
            logger.warn("Referenced map element missing: {}", owner);
        }
        return result;
    }

    private boolean isPrimaryItem(MapLayer layer) {
        return isLayerItem(layer) || isDataItem(layer);
    }

    private boolean isSecondaryItem(MapLayer layer) {
        return !isPrimaryItem(layer);
    }

    private boolean isLayerItem(MapLayer layer) {
        return Objects.equals(layer.getName(), TERRAIN_ID)
                || Objects.equals(layer.getName(), FOREST_ID)
                || Objects.equals(layer.getName(), OPAQUE_FOG_ID)
                || Objects.equals(layer.getName(), TRANSPARENT_FOG_ID);
    }

    private boolean isDataItem(MapLayer layer) {
        return isPlayerItem(layer) || isCameraItem(layer) ;
    }

    private boolean isPlayerItem(MapLayer layer) {
        return Objects.equals(layer.getName(), PLAYER_ID);
    }

    private boolean isCameraItem(MapLayer layer) {
        return Objects.equals(layer.getName(), CAMERA_ID);
    }

    private Touchable getTouchable(MapProperties properties) {
        Boolean touchable = (Boolean)properties.get(TOUCHABLE_PROPERTY);
        return touchable == Boolean.FALSE ? Touchable.childrenOnly : Touchable.enabled;
    }

    private String getOwner(MapProperties properties) {
        String owner = (String)properties.get(OWNER_PROPERTY);
        return owner == null ? ROOT_ID : owner;
    }
}
