/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.behaviour.scenario.supplement;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.common.collection.Lists;
import com.evilbird.engine.events.EventQueue;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemComposite;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.engine.item.spatial.ItemGraph;
import com.evilbird.engine.item.spatial.ItemNode;
import com.evilbird.warcraft.action.move.MoveEvent;
import com.evilbird.warcraft.action.selection.SelectFlash;
import com.evilbird.warcraft.common.WarcraftPreferences;
import com.evilbird.warcraft.item.common.query.UnitOperations;
import com.evilbird.warcraft.item.data.player.Player;
import com.evilbird.warcraft.item.unit.Unit;

import javax.inject.Inject;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.evilbird.warcraft.item.unit.UnitSound.Captured;

/**
 * Defines additional scenario behaviour allowing units of a given type to be
 * captured by an adjacent player.
 *
 * @author Blair Butterworth
 */
public class UnitCapture implements SupplementaryBehaviour
{
    private WarcraftPreferences preferences;
    private Map<ItemNode, List<Item>> capturableItems;

    @Inject
    public UnitCapture(WarcraftPreferences preferences) {
        this.preferences = preferences;
    }

    @Override
    public void accept(ItemRoot state, EventQueue events) {
        initializeCapturableItems(state);
        evaluateMovedItems(events);
    }

    private void initializeCapturableItems(ItemRoot state) {
        if (capturableItems == null) {
            capturableItems = getCapturableItems(state, state.getSpatialGraph());
        }
    }

    private Map<ItemNode, List<Item>> getCapturableItems(ItemRoot state, ItemGraph graph) {
        Map<ItemNode, List<Item>> result = new HashMap<>();

        for (Player player: UnitOperations.getPlayers(state)) {
            if (player.isCapturable()) {
                Collection<Item> units = player.getItems();
                result.putAll(getAdjacentNodes(units, graph));
            }
        }
        return result;
    }

    private Map<ItemNode, List<Item>> getAdjacentNodes(Collection<Item> items, ItemGraph graph) {
        Map<ItemNode, List<Item>> result = new HashMap<>(items.size());
        for (Item item: items) {
            for (ItemNode node: graph.getAdjacentNodes(item)) {
                result.merge(node, Lists.asList(item), Lists::union);
            }
        }
        return result;
    }

    private void evaluateMovedItems(EventQueue queue) {
        for (MoveEvent moveEvent: queue.getEvents(MoveEvent.class)) {
            if (moveEvent.isUpdate()) {
                evaluateMovedItem(moveEvent.getLocation(), moveEvent.getSubject());
            }
        }
    }

    private void evaluateMovedItem(ItemNode location, Item owner) {
        if (capturableItems.containsKey(location)) {
            List<Item> captured = capturableItems.remove(location);
            captureItems(captured, owner);
        }
    }

    private void captureItems(Collection<Item> captured, Item owner) {
        capturableItems.values().forEach(items -> items.removeAll(captured));
        capturableItems.values().removeIf(Collection::isEmpty);

        for (Item captive: captured) {
            captureItem(captive, owner);
        }
    }

    private void captureItem(Item captured, Item owner) {
        setOwner(captured, owner);
        setSoundEffect(captured);
        setAnimation(captured);
    }

    private void setOwner(Item captured, Item owner) {
        ItemComposite oldParent = captured.getParent();
        ItemComposite newParent = owner.getParent();

        oldParent.removeItem(captured);
        newParent.addItem(captured);
    }

    private void setSoundEffect(Item item) {
        if (item instanceof Unit) {
            Unit unit = (Unit)item;
            unit.setSound(Captured, preferences.getEffectsVolume());
        }
    }

    private void setAnimation(Item item) {
        Action action = new SelectFlash();
        action.setItem(item);
        item.addAction(action);
    }
}
