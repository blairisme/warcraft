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
import com.evilbird.engine.action.framework.Action;
import com.evilbird.engine.action.framework.ParallelAction;
import com.evilbird.engine.action.framework.duration.ActionDuration;
import com.evilbird.engine.action.framework.duration.TimeDuration;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.specialized.animated.SoundIdentifier;
import com.evilbird.warcraft.item.unit.UnitSound;
import com.evilbird.warcraft.item.unit.resource.ResourceType;

import javax.inject.Inject;

/**
 * Instances of this {@link Action} instruct a given {@link Gather} to gather
 * wood.
 *
 * @author Blair Butterworth
 */
// TODO: Lumber mill increases rate to 125
public class GatherWood extends Gather
{
    @Inject
    public GatherWood(){
    }

    @Override
    protected ResourceType getResourceType() {
        return ResourceType.Wood;
    }

    @Override
    protected float getGatherCapacity(Item gatherer) {
        return 100f;
    }

    @Override
    protected ActionDuration getGatherSpeed(Item gatherer) {
        return new TimeDuration(40f);
    }

    @Override
    protected Action obtainAction(Item gatherer, Item resource) {
        SoundIdentifier obtainSoundId = UnitSound.getGatherSound(getResourceType());
        Action sound = new RepeatedAudibleAction(gatherer, obtainSoundId, 40, 1f);
        Action obtain = super.obtainAction(gatherer, resource);
        return new ParallelAction(obtain, sound);
    }
}
