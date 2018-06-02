package com.evilbird.warcraft.action.sequence;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.evilbird.engine.action.framework.ParallelAction;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.specialized.animated.SoundIdentifier;
import com.evilbird.warcraft.item.unit.UnitSound;
import com.evilbird.warcraft.item.unit.resource.ResourceType;

import javax.inject.Inject;

/**
 * Created by blair on 23/09/2017.
 */
// TODO: Lumber mill increases rate to 125
// TODO: Tree destroyed after gathering
// TODO: Move to nearest tree
public class GatherWoodSequence extends GatherSequence
{
    private AudibleSequence audibleSequence;

    @Inject
    public GatherWoodSequence(AudibleSequence audibleSequence, MoveSequence moveSequence) {
        super(moveSequence);
        this.audibleSequence = audibleSequence;
    }

    @Override
    protected float getGatherCapacity(Item gatherer) {
        return 100f;
    }

    @Override
    protected float getGatherSpeed(Item gatherer) {
        return 40f;
    }

    @Override
    protected Action obtainAction(Item gatherer, Item resource, ResourceType type) {
        SoundIdentifier obtainSoundId = UnitSound.getGatherSound(type);
        Action sound = audibleSequence.get(gatherer, obtainSoundId, 40, 1f);
        Action obtain = super.obtainAction(gatherer, resource, type);
        return new ParallelAction(obtain, sound);
    }




    //    @Override
//    protected Action obtainSetup(Item gatherer, Item resource, ResourceType type)
//    {
//        return new AnimateAction((Animated)gatherer, UnitAnimation.GatherWood);
//    }
//
//    @Override
//    protected Action obtainTeardown(Item gatherer, Item resource, ResourceType type)
//    {
//        Action gathererIdle = new AnimateAction((Animated)gatherer, UnitAnimation.Idle);
//        Action resourceDead = new AnimateAction((Animated)resource, UnitAnimation.Dead);
//        Action resourceDisabled = new DisableAction(gatherer, false);
//        return new ParallelAction(gathererIdle, resourceDead, resourceDisabled);
//    }
//
//    @Override
//    protected Action obtainTransfer(Item gatherer, Item resource, ResourceType type)
//    {
//        Action transfer = super.obtainTransfer(gatherer, resource, type);
//        Action sound = new AudibleAction((Audible)gatherer, UnitSound.GatherWood);
//        Action buffer = new DelayedAction(new TimeDuration(1f));
//        Action soundBuffer = new SequenceAction(sound, buffer);
//        Action gatherSound = new RepeatedAction(soundBuffer, 10);
//        return new ParallelAction(transfer, gatherSound);
//    }
}
