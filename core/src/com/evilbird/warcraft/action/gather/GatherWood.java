/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.gather;

import com.evilbird.engine.action.common.RepeatedAudibleAction;
import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.framework.ParallelAction;
import com.evilbird.engine.item.ItemType;
import com.evilbird.warcraft.item.unit.UnitSound;
import com.evilbird.warcraft.item.unit.UnitType;
import com.evilbird.warcraft.item.unit.resource.ResourceType;

import javax.inject.Inject;

/**
 * Instances of this {@link Action} instruct a given {@link GatherAction} to gather
 * wood.
 *
 * @author Blair Butterworth
 */
public class GatherWood extends GatherAction
{
    @Inject
    public GatherWood(){
    }

    @Override
    protected ItemType getDepotType() {
        return UnitType.TownHall;
    }

    @Override
    protected ItemType getResourceType() {
        return UnitType.Tree;
    }

    @Override
    protected ResourceType getResourceVariety() {
        return ResourceType.Wood;
    }

    @Override
    protected float getGatherCapacity() {
        return 100f;
    }

    @Override
    protected float getGatherSpeed() {
        return 40f;
    }

    @Override
    protected Action obtainResources() {
        Action sound = new RepeatedAudibleAction(UnitSound.GatherWood, 40, 1f);
        Action obtain = super.obtainResources();
        return new ParallelAction(obtain, sound);
    }
}
