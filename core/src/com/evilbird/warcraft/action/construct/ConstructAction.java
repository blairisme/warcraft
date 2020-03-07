/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.construct;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.ActionResult;
import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.common.time.GameTimer;
import com.evilbird.engine.object.GameObject;
import com.evilbird.warcraft.action.common.exclusion.ItemExclusion;
import com.evilbird.warcraft.action.common.transfer.ResourceTransfer;
import com.evilbird.warcraft.common.WarcraftPreferences;
import com.evilbird.warcraft.object.data.player.Player;
import com.evilbird.warcraft.object.unit.Unit;
import com.evilbird.warcraft.object.unit.UnitType;
import com.evilbird.warcraft.object.unit.building.Building;
import com.evilbird.warcraft.object.unit.combatant.gatherer.Gatherer;

import javax.inject.Inject;

import static com.evilbird.warcraft.action.common.production.ProductionOperations.getProductionTime;
import static com.evilbird.warcraft.object.common.query.UnitOperations.getPlayer;
import static com.evilbird.warcraft.object.common.query.UnitOperations.moveAdjacent;
import static com.evilbird.warcraft.object.unit.UnitAnimation.Construct;
import static com.evilbird.warcraft.object.unit.UnitAnimation.Idle;
import static com.evilbird.warcraft.object.unit.UnitSound.Build;
import static com.evilbird.warcraft.object.unit.UnitSound.Complete;

/**
 * An {@link Action} that constructs a building.
 *
 * @author Blair Butterworth
 */
public class ConstructAction extends BasicAction
{
    private static final int BUILDING_SOUND_INTERVAL = 10;

    private transient GameTimer soundInterval;
    private transient GameTimer buildTimer;
    private transient ConstructEvents events;
    private transient ItemExclusion exclusion;
    private transient ResourceTransfer resources;
    private transient WarcraftPreferences preferences;

    @Inject
    public ConstructAction(
        ConstructEvents events,
        WarcraftPreferences preferences,
        ItemExclusion exclusion,
        ResourceTransfer resources)
    {
        this.events = events;
        this.preferences = preferences;
        this.exclusion = exclusion;
        this.resources = resources;
        this.soundInterval = new GameTimer(BUILDING_SOUND_INTERVAL);
    }

    @Override
    public ActionResult act(float time) {
        if (! initialized()) {
            return initialize();
        }
        if (buildTimer.advance(time)) {
            return complete();
        }
        return update(time);
    }

    @Override
    public void reset() {
        buildTimer = null;
    }

    private boolean initialized() {
        return buildTimer != null;
    }

    private ActionResult initialize() {
        Unit builder = (Unit)getSubject();
        exclusion.disable(builder);

        Building building = (Building)getTarget();
        building.setAnimation(Construct);

        buildTimer = new GameTimer(getProductionTime((UnitType)building.getType(), preferences));
        buildTimer.advance(building.getConstructionProgress() * buildTimer.duration());

        events.notifyConstructStarted(building, builder);
        return ActionResult.Incomplete;
    }

    private ActionResult update(float time) {
        Building building = (Building)getTarget();
        building.setConstructionProgress(buildTimer.completion());

        Gatherer builder = (Gatherer)getSubject();
        if (preferences.isBuildingSoundsEnabled() && soundInterval.advance(time)) {
            soundInterval.reset();
            builder.setSound(Build);
        }
        return ActionResult.Incomplete;
    }

    private ActionResult complete() {
        Building building = (Building)getTarget();
        Gatherer builder = (Gatherer)getSubject();
        Player player = getPlayer(building);

        finalizeBuilding(building);
        finalizeBuilder(builder, building);
        transferResources(building, player);
        transferUpgrades(building, player);
        notifyBuildingComplete(building, builder);

        return ActionResult.Complete;
    }

    private void finalizeBuilding(Building building) {
        building.setConstructionProgress(1);
        building.setAnimation(Idle);
    }

    private void finalizeBuilder(Gatherer builder, Building building) {
        builder.setConstruction(null);
        builder.setAnimation(Idle);

        exclusion.restore(builder);
        moveAdjacent(builder, building);

        if (preferences.isSpeechEnabled()) {
            builder.setSound(Complete);
        }
    }

    private void transferResources(Building building, Player player) {
        resources.transfer(building, player);
    }

    private void transferUpgrades(Building building, Player player) {
        player.setUpgrades(building.getUpgrades());
        building.clearUpgrades();
    }

    private void notifyBuildingComplete(Building building, GameObject builder) {
        events.notifyConstructComplete(building, builder);
    }
}
