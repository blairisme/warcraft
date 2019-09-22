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
import com.evilbird.engine.events.Events;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.engine.item.spatial.ItemGraph;
import com.evilbird.warcraft.item.unit.Unit;

import static com.evilbird.warcraft.action.select.SelectEvents.notifySelected;
import static com.evilbird.warcraft.item.unit.UnitAnimation.Idle;

public class Exclusion
{
    private Exclusion() {
    }

    public static void restore(Unit item) {
        item.setVisible(true);
        item.setAnimation(Idle);
        item.setSelectable(true);
        item.setTouchable(Touchable.enabled);

        ItemRoot root = item.getRoot();
        ItemGraph graph = root.getSpatialGraph();
        graph.addOccupants(item);
    }

    public static void disable(Unit item, Events events) {
        if (item.getSelected()) {
            item.setSelected(false);
            notifySelected(events, item, false);
        }
        item.setVisible(false);
        item.setSelectable(false);
        item.setTouchable(Touchable.disabled);

        ItemRoot root = item.getRoot();
        ItemGraph graph = root.getSpatialGraph();
        graph.removeOccupants(item);
    }
}
