package com.evilbird.warcraft.action.sequence;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.evilbird.engine.action.common.AnimateAction;
import com.evilbird.engine.action.common.DisableAction;
import com.evilbird.engine.action.common.SelectAction;
import com.evilbird.engine.action.common.VisibleAction;
import com.evilbird.engine.action.framework.ParallelAction;
import com.evilbird.engine.action.framework.SequenceAction;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.specialized.animated.Animated;
import com.evilbird.warcraft.action.common.MoveAction;
import com.evilbird.warcraft.item.common.capability.Movable;
import com.evilbird.warcraft.item.unit.UnitAnimation;
import com.evilbird.warcraft.item.unit.building.Building;
import com.evilbird.warcraft.item.unit.resource.ResourceType;

import javax.inject.Inject;

/**
 * Created by blair on 23/09/2017.
 */
public class GatherGoldSequence extends GatherSequence
{
    @Inject
    public GatherGoldSequence(ConfirmSequence confirmSequence)
    {
        super(confirmSequence);
    }

    @Override
    protected Action obtainSetup(Item gatherer, Item resource, ResourceType type)
    {
        Action deselect = new SelectAction(gatherer, false);
        Action disable = new DisableAction(gatherer, false);
        Action hide = new VisibleAction(gatherer, false);
        Action goldmineIlluminated = new AnimateAction((Animated)resource, UnitAnimation.GatherGold);
        return new ParallelAction(deselect, disable, hide, goldmineIlluminated);
    }

    @Override
    protected Action obtainTeardown(Item gatherer, Item resource, ResourceType type)
    {
        Action enable = new DisableAction(gatherer, true);
        Action show = new VisibleAction(gatherer, true);
        Action idleAnimation = new AnimateAction((Animated)resource, UnitAnimation.Idle);
        return new ParallelAction(enable, show, idleAnimation);
    }
}
