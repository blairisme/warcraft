package com.evilbird.warcraft.action.sequence;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.evilbird.engine.action.common.DisableAction;
import com.evilbird.engine.action.common.SelectAction;
import com.evilbird.engine.action.common.VisibleAction;
import com.evilbird.engine.action.framework.ParallelAction;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.unit.resource.ResourceType;

import javax.inject.Inject;

/**
 * Created by blair on 23/09/2017.
 */
// TODO: Destroy mine if empty
public class GatherGoldSequence extends GatherSequence
{
    @Inject
    public GatherGoldSequence(MoveSequence moveSequence) {
        super(moveSequence);
    }

    @Override
    protected float getGatherCapacity(Item gatherer) {
        return 100f;
    }

    @Override
    protected float getGatherSpeed(Item gatherer) {
        return 5f;
    }

    @Override
    protected Action preObtainAction(Item gatherer, Item resource, ResourceType type) {
        Action deselect = new SelectAction(gatherer, false);
        Action disable = new DisableAction(gatherer, false);
        Action hide = new VisibleAction(gatherer, false);
        Action original = super.preObtainAction(gatherer, resource, type);
        return new ParallelAction(deselect, disable, hide, original);
    }

    @Override
    protected Action postObtainAction(Item gatherer, Item resource, ResourceType type) {
        Action enable = new DisableAction(gatherer, true);
        Action show = new VisibleAction(gatherer, true);
        Action original = super.postObtainAction(gatherer, resource, type);
        return new ParallelAction(enable, show, original);
    }


}
