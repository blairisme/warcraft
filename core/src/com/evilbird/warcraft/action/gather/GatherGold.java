/*
 * Blair Butterworth (c) 2018
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.gather;

import com.evilbird.engine.action.common.DisableAction;
import com.evilbird.engine.action.common.SelectAction;
import com.evilbird.engine.action.common.VisibleAction;
import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.framework.ParallelAction;
import com.evilbird.engine.item.ItemType;
import com.evilbird.warcraft.item.unit.UnitType;
import com.evilbird.warcraft.item.unit.resource.ResourceType;

import javax.inject.Inject;

/**
 * Instances of this {@link Action} instruct a given {@link GatherAction} to gather
 * gold.
 *
 * @author Blair Butterworth
 */
public class GatherGold extends GatherAction
{
    @Inject
    public GatherGold(){
    }

    @Override
    protected ItemType getDepotType() {
        return UnitType.TownHall;
    }

    @Override
    protected ItemType getResourceType() {
        return UnitType.GoldMine;
    }

    @Override
    protected ResourceType getResourceVariety() {
        return ResourceType.Gold;
    }

    @Override
    protected float getGatherCapacity() {
        return 100f;
    }

    @Override
    protected float getGatherSpeed() {
        return 5;
    }

    @Override
    protected Action preObtainAnimation() {
        Action deselect = new SelectAction(false);
        Action disable = new DisableAction(false);
        Action hide = new VisibleAction(false);
        Action original = super.preObtainAnimation();
        return new ParallelAction(deselect, disable, hide, original);
    }

    @Override
    protected Action postObtainAnimation() {
        Action enable = new DisableAction(true);
        Action show = new VisibleAction(true);
        Action original = super.postObtainAnimation();
        return new ParallelAction(enable, show, original);
    }
}
