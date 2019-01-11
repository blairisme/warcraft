package com.evilbird.warcraft.action.sequence;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.evilbird.engine.action.common.DisableAction;
import com.evilbird.engine.action.common.SelectAction;
import com.evilbird.engine.action.common.VisibleAction;
import com.evilbird.engine.action.framework.ParallelAction;
import com.evilbird.engine.action.framework.duration.ActionDuration;
import com.evilbird.engine.action.framework.duration.TimeDuration;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.unit.resource.ResourceType;

import javax.inject.Inject;

/**
 * Created by blair on 23/09/2017.
 */
public class GatherGoldSequence extends GatherSequence
{
    @Inject
    public GatherGoldSequence(MoveSequence moveSequence) {
        super(moveSequence);
    }

    @Override
    protected ResourceType getResourceType() {
        return ResourceType.Gold;
    }

    @Override
    protected float getGatherCapacity(Item gatherer) {
        return 100f;
    }

    @Override
    protected ActionDuration getGatherSpeed(Item gatherer) {
        return new TimeDuration(5f);
    }

    @Override
    protected Action preObtainAction(Item gatherer, Item resource) {
        Action deselect = new SelectAction(gatherer, false);
        Action disable = new DisableAction(gatherer, false);
        Action hide = new VisibleAction(gatherer, false);
        Action original = super.preObtainAction(gatherer, resource);
        return new ParallelAction(deselect, disable, hide, original);
    }

    @Override
    protected Action postObtainAction(Item gatherer, Item resource) {
        Action enable = new DisableAction(gatherer, true);
        Action show = new VisibleAction(gatherer, true);
        Action original = super.postObtainAction(gatherer, resource);
        return new ParallelAction(enable, show, original);
    }
}
