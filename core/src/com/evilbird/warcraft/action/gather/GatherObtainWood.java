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
import com.evilbird.engine.object.GameObject;
import com.evilbird.warcraft.action.common.transfer.ResourceTransfer;
import com.evilbird.warcraft.object.unit.UnitAnimation;
import com.evilbird.warcraft.object.unit.combatant.gatherer.Gatherer;

import javax.inject.Inject;

import static com.evilbird.warcraft.data.resource.ResourceType.Wood;
import static com.evilbird.warcraft.object.common.query.UnitOperations.reorient;
import static com.evilbird.warcraft.object.unit.UnitAnimation.Idle;
import static com.evilbird.warcraft.object.unit.UnitAnimation.IdleWood;
import static com.evilbird.warcraft.object.unit.UnitAnimation.Move;
import static com.evilbird.warcraft.object.unit.UnitAnimation.MoveWood;
import static com.evilbird.warcraft.object.unit.UnitSound.ChopWood;

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
    public GatherObtainWood(GatherEvents events, ResourceTransfer resources) {
        super(events, null, resources);
        setResource(Wood);
    }

    @Override
    protected boolean initialize() {
        Gatherer gatherer = (Gatherer) getSubject();
        gatherer.setAnimation(UnitAnimation.GatherWood);

        GameObject tree = getTarget();
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
        Gatherer gatherer = (Gatherer) getSubject();
        gatherer.setAnimationAlias(IdleWood, Idle);
        gatherer.setAnimationAlias(MoveWood, Move);
        gatherer.setAnimation(Idle);
        return super.complete();
    }

    @Override
    protected boolean update(float time) {
        if (timer.advance(time)) {
            timer.reset();
            Gatherer gatherer = (Gatherer) getSubject();
            gatherer.setSound(ChopWood);
        }
        return super.update(time);
    }
}
