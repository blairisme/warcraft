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
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.events.EventQueue;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemComposite;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.engine.item.spatial.ItemGraph;
import com.evilbird.engine.item.spatial.ItemNode;
import com.evilbird.warcraft.action.move.MoveEvent;
import com.evilbird.warcraft.action.select.SelectFlash;
import com.evilbird.warcraft.common.WarcraftPreferences;
import com.evilbird.warcraft.item.unit.Unit;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.evilbird.engine.item.utility.ItemPredicates.withType;
import static com.evilbird.warcraft.item.unit.UnitSound.Captured;
import static java.util.Collections.emptyList;

/**
 * Defines additional scenario behaviour allowing units of a given type to be
 * captured by an adjacent player.
 *
 * @author Blair Butterworth
 */
public class UnitCapture implements SupplementaryBehaviour
{
    private Identifier capturableType;
    private Map<ItemNode, List<Item>> capturableItems;
    private WarcraftPreferences preferences;

    @Inject
    public UnitCapture(WarcraftPreferences preferences) {
        this.preferences = preferences;
    }

    public void setCapturableType(Identifier capturableType) {
        this.capturableType = capturableType;
    }

    @Override
    public void accept(ItemRoot state, EventQueue events) {
        findCapturableItems(state);
        evaluateMovedItems(events);
    }

    private void findCapturableItems(ItemRoot state) {
        if (capturableItems == null) {
            Collection<Item> items = state.findAll(withType(capturableType));
            capturableItems = getItemNodeMap(items, state.getSpatialGraph());
        }
    }

    private Map<ItemNode, List<Item>> getItemNodeMap(Collection<Item> items, ItemGraph graph) {
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
                evaluateMovedItem(moveEvent.getSubject(), moveEvent.getLocation());
            }
        }
    }

    private void evaluateMovedItem(Item item, ItemNode location) {
        List<Item> capturedItems = new ArrayList<>(capturableItems.getOrDefault(location, emptyList()));
        capturableItems.forEach((node, items) -> items.removeAll(capturedItems));
        capturableItems.entrySet().removeIf(entry -> entry.getValue().isEmpty());

        for (Item capturedItem: capturedItems) {
            setOwner(capturedItem, item);
            setSoundEffect(capturedItem);
            setAnimation(capturedItem);
        }
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
