/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.common.exclusion;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.engine.item.spatial.ItemGraph;
import com.evilbird.warcraft.action.selection.SelectEvents;
import com.evilbird.warcraft.item.unit.Unit;

import javax.inject.Inject;

import static com.evilbird.warcraft.item.unit.UnitAnimation.Idle;

/**
 * Instances of this class remove an item from the game world, visually and
 * interactively, although the item still remains part of the item hierarchy.
 */
public class ItemExclusion
{
    private SelectEvents events;

    @Inject
    public ItemExclusion(SelectEvents events) {
        this.events = events;
    }

    public void restore(Unit item) {
        item.setVisible(true);
        item.setAnimation(Idle);
        item.setSelectable(true);
        item.setTouchable(Touchable.enabled);

        ItemRoot root = item.getRoot();
        ItemGraph graph = root.getSpatialGraph();
        graph.addOccupants(item);
    }

    public void disable(Unit item) {
        if (item.getSelected()) {
            item.setSelected(false);
            events.notifySelected(item, false);
        }
        item.setVisible(false);
        item.setSelectable(false);
        item.setTouchable(Touchable.disabled);

        ItemRoot root = item.getRoot();
        ItemGraph graph = root.getSpatialGraph();
        graph.removeOccupants(item);
    }
}
