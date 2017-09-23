package com.evilbird.warcraft.action.sequence;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.evilbird.engine.action.common.AnimateAction;
import com.evilbird.engine.action.common.AudibleAction;
import com.evilbird.engine.action.common.DisableAction;
import com.evilbird.engine.action.common.SelectAction;
import com.evilbird.engine.action.common.VisibleAction;
import com.evilbird.engine.action.framework.DelayedAction;
import com.evilbird.engine.action.framework.ParallelAction;
import com.evilbird.engine.action.framework.RepeatedAction;
import com.evilbird.engine.action.framework.TimeDuration;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.specialized.animated.Animated;
import com.evilbird.engine.item.specialized.animated.Audible;
import com.evilbird.warcraft.item.unit.UnitAnimation;
import com.evilbird.warcraft.item.unit.UnitSound;
import com.evilbird.warcraft.item.unit.resource.ResourceType;

import javax.inject.Inject;

/**
 * Created by blair on 23/09/2017.
 */
public class GatherWoodSequence extends GatherSequence
{
    @Inject
    public GatherWoodSequence()
    {
    }

    @Override
    protected Action obtainSetup(Item gatherer, Item resource, ResourceType type)
    {
        return new AnimateAction((Animated)gatherer, UnitAnimation.GatherWood);
    }

    @Override
    protected Action obtainTeardown(Item gatherer, Item resource, ResourceType type)
    {
        return new AnimateAction((Animated)gatherer, UnitAnimation.Idle);
    }

    @Override
    protected Action obtainTransfer(Item gatherer, Item resource, ResourceType type)
    {
        Action transfer = super.obtainTransfer(gatherer, resource, type);

        Action sound = new AudibleAction((Audible)gatherer, UnitSound.GatherWood);
        Action soundBuffer = new DelayedAction(sound, new TimeDuration(1f));
        Action gatherSound = new RepeatedAction(soundBuffer, 10);

        return new ParallelAction(transfer, gatherSound);
    }

    @Override
    protected Action depositSetup(Item gatherer, Item depot, Item Player, ResourceType type)
    {
        Action deselect = new SelectAction(gatherer, false);
        Action disable = new DisableAction(gatherer, false);
        Action hide = new VisibleAction(gatherer, false);
        return new ParallelAction(deselect, disable, hide);
    }

    @Override
    protected Action depositTeardown(Item gatherer, Item depot, Item Player, ResourceType type)
    {
        Action enable = new DisableAction(gatherer, true);
        Action show = new VisibleAction(gatherer, true);
        return new ParallelAction(enable, show);
    }
}
