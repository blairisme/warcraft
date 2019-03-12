/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.state.asset;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.evilbird.engine.common.maps.TiledMapFile;
import com.evilbird.engine.common.maps.TiledMapLoader;
import com.evilbird.engine.common.lang.TextIdentifier;
import com.evilbird.engine.common.lang.Objects;
import com.evilbird.engine.item.*;
import com.evilbird.warcraft.item.data.DataType;
import com.evilbird.warcraft.item.data.player.Player;
import com.evilbird.warcraft.item.layer.LayerIdentifier;
import com.evilbird.warcraft.item.unit.UnitType;
import com.evilbird.warcraft.item.unit.resource.ResourceType;

import javax.inject.Inject;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

//TODO: use size from level
//TODO: make generic - move to common
//TODO: dispose of map
public class MapFactory
{
    private TiledMapLoader mapLoader;
    private ItemFactory itemFactory;

    @Inject
    public MapFactory(ItemFactory itemFactory) {
        this.itemFactory = itemFactory;
        this.mapLoader = new TiledMapLoader();
    }

    public ItemRoot get(MapDefinition definition) {
        String mapPath = definition.getFilePath();
        TiledMapFile map = mapLoader.load(mapPath);
        return getItem(map);
    }

    private ItemRoot getItem(TiledMapFile level) {
        ItemRoot result = new ItemRoot();
        result.setViewport(new ScreenViewport());
        result.setSpatialGraph(new ItemGraph(32, 32, 32, 32));
        addItems(level, result);
        return result;
    }

    private ItemRoot addItems(TiledMapFile level, ItemRoot root) {
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
                String identifier = item.getIdentifier().toString(); //TODO
                result.put(identifier, (ItemComposite)item);
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
            addLayerItems(map, layer, parents);
        } else {
            addObjectItems(layer, parents);
        }
    }

    private void addLayerItems(TiledMapFile map, MapLayer layer, Map<String, ItemComposite> parents) {
        LayerIdentifier layerIdentifier = new LayerIdentifier(map, (TiledMapTileLayer)layer);  //TODO
        Item item = itemFactory.newItem(layerIdentifier);

        ItemComposite parent = parents.get("root");
        parent.addItem(item);
    }

    private void addObjectItems(MapLayer layer, Map<String, ItemComposite> parents) {
        for (MapObject object : layer.getObjects()) {
            ItemType type = getObjectItemType(layer);
            Item item = newItem(type, object);
            ItemComposite parent = getParent(parents, object);
            parent.addItem(item);
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
        item.setId(new TextIdentifier(object.getName()));
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
        ItemComposite result = parents.get(owner);

        if (result == null) {
            System.out.println("Error: " + owner + " missing");
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

    private Touchable getTouchable(MapProperties properties) {
        Boolean touchable = (Boolean) properties.get("Touchable");
        return touchable == Boolean.FALSE ? Touchable.childrenOnly : Touchable.enabled;
    }

    private String getOwner(MapProperties properties) {
        String owner = (String) properties.get("Owner");
        return owner == null ? "root" : owner;
    }
}
