/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.gather;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.common.time.GameTimer;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.action.common.transfer.ResourceTransfer;
import com.evilbird.warcraft.item.unit.UnitAnimation;
import com.evilbird.warcraft.item.unit.gatherer.Gatherer;

import javax.inject.Inject;

import static com.evilbird.warcraft.item.common.query.UnitOperations.reorient;
import static com.evilbird.warcraft.item.common.resource.ResourceType.Wood;
import static com.evilbird.warcraft.item.unit.UnitAnimation.Idle;
import static com.evilbird.warcraft.item.unit.UnitAnimation.IdleWood;
import static com.evilbird.warcraft.item.unit.UnitAnimation.Move;
import static com.evilbird.warcraft.item.unit.UnitAnimation.MoveWood;
import static com.evilbird.warcraft.item.unit.UnitSound.ChopWood;

/**
 * An {@link Action} that instructs a {@link Gatherer} to obtain wood from a
 * forest.
 *
 * @author Blair Butterworth
 */
public class GatherObtainWood extends GatherObtain
{
    private transient GameTimer timer;

    @Inject
    public GatherObtainWood(GatherEvents events, ResourceTransfer transferer) {
        super(events, null, transferer);
        setResource(Wood);
    }

    @Override
    protected boolean initialize() {
        Gatherer gatherer = (Gatherer)getItem();
        gatherer.setAnimation(UnitAnimation.GatherWood);

        Item tree = getTarget();
        reorient(gatherer, tree, false);

        return super.initialize();
    }

    @Override
    protected boolean load() {
        timer = new GameTimer(1);
        timer.advance(timer.duration());
        return super.load();
    }

    @Override
    protected boolean complete() {
        Gatherer gatherer = (Gatherer)getItem();
        gatherer.setAnimationAlias(IdleWood, Idle);
        gatherer.setAnimationAlias(MoveWood, Move);
        gatherer.setAnimation(Idle);
        return super.complete();
    }

    @Override
    protected boolean update(float time) {
        if (timer.advance(time)) {
            timer.reset();
            Gatherer gatherer = (Gatherer)getItem();
            gatherer.setSound(ChopWood);
        }
        return super.update(time);
    }
}