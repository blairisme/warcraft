/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.common.exclusion;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.evilbird.engine.object.GameObjectContainer;
import com.evilbird.engine.object.spatial.GameObjectGraph;
import com.evilbird.warcraft.action.selection.SelectEvents;
import com.evilbird.warcraft.object.unit.Unit;

import javax.inject.Inject;

import static com.evilbird.warcraft.object.unit.UnitAnimation.Idle;

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

        GameObjectContainer root = item.getRoot();
        GameObjectGraph graph = root.getSpatialGraph();
        graph.addOccupants(item);
    }

    public void disable(Unit item) {
        if (item.getSelected()) {
            item.setSelected(false);
            events.selectionUpdated(item, false);
        }
        item.setVisible(false);
        item.setSelectable(false);
        item.setTouchable(Touchable.disabled);

        GameObjectContainer root = item.getRoot();
        GameObjectGraph graph = root.getSpatialGraph();
        graph.removeOccupants(item);
    }
}
