/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.construct;

import com.evilbird.engine.action.framework.TemporalAction;
import com.evilbird.engine.common.time.GameTimer;
import com.evilbird.warcraft.action.common.exclusion.ItemExclusion;
import com.evilbird.warcraft.common.WarcraftPreferences;
import com.evilbird.warcraft.item.unit.Unit;
import com.evilbird.warcraft.item.unit.building.Building;
import com.evilbird.warcraft.item.unit.gatherer.Gatherer;

import javax.inject.Inject;

import static com.evilbird.engine.action.ActionConstants.ActionComplete;
import static com.evilbird.engine.action.ActionConstants.ActionIncomplete;
import static com.evilbird.warcraft.item.common.query.UnitOperations.moveAdjacent;
import static com.evilbird.warcraft.item.unit.UnitAnimation.Construct;
import static com.evilbird.warcraft.item.unit.UnitAnimation.Idle;
import static com.evilbird.warcraft.item.unit.UnitCosts.buildTime;
import static com.evilbird.warcraft.item.unit.UnitSound.Build;
import static com.evilbird.warcraft.item.unit.UnitSound.Complete;

/**
 * Represents an {@link Action} that
 */
public class ConstructAction extends TemporalAction
{
    private static final int BUILDING_SOUND_INTERVAL = 10;
    private static final int UNINITIALIZED_DURATION = -1;

    private transient GameTimer timer;
    private transient ConstructEvents events;
    private transient ItemExclusion exclusion;
    private transient WarcraftPreferences preferences;

    @Inject
    public ConstructAction(ConstructEvents events, WarcraftPreferences preferences, ItemExclusion exclusion) {
        super(UNINITIALIZED_DURATION);
        this.events = events;
        this.preferences = preferences;
        this.exclusion = exclusion;
        this.timer = new GameTimer(BUILDING_SOUND_INTERVAL);
    }

    @Override
    public boolean act(float time) {
        if (! initialized()) {
            return initialize();
        }
        if (super.act(time)) {
            return complete();
        }
        return update(time);
    }

    @Override
    public void reset() {
        setDuration(UNINITIALIZED_DURATION);
    }

    private boolean initialized() {
        return getDuration() != UNINITIALIZED_DURATION;
    }

    private boolean initialize() {
        Unit builder = (Unit)getItem();
        exclusion.disable(builder);

        Building building = (Building)getTarget();
        building.setAnimation(Construct);

        setDuration(buildTime(building));
        setProgress(building.getConstructionProgress() * buildTime(building));

        events.notifyConstructStarted(builder, building);
        return ActionIncomplete;
    }

    private boolean complete() {
        Building building = (Building)getTarget();
        building.setConstructionProgress(1);
        building.setAnimation(Idle);

        Gatherer builder = (Gatherer)getItem();
        builder.setAssociatedItem(null);
        builder.setAnimation(Idle);
        exclusion.restore(builder);
        moveAdjacent(builder, building);

        if (preferences.isSpeechEnabled()) {
            builder.setSound(Complete);
        }
        events.notifyConstructComplete(builder, building);
        return ActionComplete;
    }

    private boolean update(float time) {
        Building building = (Building)getTarget();
        building.setConstructionProgress(getProgress());

        Gatherer builder = (Gatherer)getItem();
        if (preferences.isBuildingSoundsEnabled() && timer.advance(time)) {
            timer.reset();
            builder.setSound(Build);
        }
        return ActionIncomplete;
    }
}
