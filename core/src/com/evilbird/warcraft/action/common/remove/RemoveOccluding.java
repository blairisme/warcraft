/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.common.remove;

import com.badlogic.gdx.math.Rectangle;
import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.events.Events;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemGroup;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.warcraft.item.common.query.UnitOperations;

import javax.inject.Inject;

import static com.evilbird.engine.common.function.Predicates.all;
import static com.evilbird.engine.item.utility.ItemPredicates.overlapping;
import static com.evilbird.warcraft.action.common.remove.RemoveEvents.notifyRemove;
import static com.evilbird.warcraft.item.common.query.UnitPredicates.isBuilding;
import static com.evilbird.warcraft.item.common.query.UnitPredicates.isDead;

/**
 * Instances of this {@link Action} remove any
 * {@link UnitOperations#isDead(Item) dead} {@link Item Items} that occupy the
 * same location as the actions target.
 *
 * @author Blair Butterworth
 */
public class RemoveOccluding extends BasicAction
{
    private Events events;

    @Inject
    public RemoveOccluding(Events events) {
        this.events = events;
    }

    @Override
    public boolean act(float delta) {
        Item item = getTarget();
        ItemRoot root = item.getRoot();
        Rectangle bounds = item.getBounds();

        for (Item occluding: root.findAll(all(isBuilding(), isDead(), overlapping(bounds)))) {
            removeItem(occluding);
            notifyRemove(events, occluding);
        }
        return true;
    }

    protected void removeItem(Item item) {
        ItemGroup parent = item.getParent();
        parent.removeItem(item);
    }
}
