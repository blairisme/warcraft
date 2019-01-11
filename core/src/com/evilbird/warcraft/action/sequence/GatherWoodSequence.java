package com.evilbird.warcraft.action.sequence;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.evilbird.engine.action.framework.ParallelAction;
import com.evilbird.engine.action.framework.duration.ActionDuration;
import com.evilbird.engine.action.framework.duration.TimeDuration;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.specialized.animated.SoundIdentifier;
import com.evilbird.warcraft.item.unit.UnitSound;
import com.evilbird.warcraft.item.unit.resource.ResourceType;

import javax.inject.Inject;

/**
 * Created by blair on 23/09/2017.
 */
// TODO: Lumber mill increases rate to 125
public class GatherWoodSequence extends GatherSequence
{
    private AudibleSequence audibleSequence;

    @Inject
    public GatherWoodSequence(AudibleSequence audibleSequence, MoveSequence moveSequence) {
        super(moveSequence);
        this.audibleSequence = audibleSequence;
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
        Action sound = audibleSequence.get(gatherer, obtainSoundId, 40, 1f);
        Action obtain = super.obtainAction(gatherer, resource);
        return new ParallelAction(obtain, sound);
    }
}
