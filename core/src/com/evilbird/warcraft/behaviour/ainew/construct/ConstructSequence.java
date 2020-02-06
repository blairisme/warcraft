/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ainew.construct;

import com.badlogic.gdx.ai.btree.Task;
import com.badlogic.gdx.ai.btree.branch.Selector;
import com.badlogic.gdx.ai.btree.branch.Sequence;
import com.badlogic.gdx.ai.btree.decorator.AlwaysSucceed;
import com.evilbird.engine.events.Events;
import com.evilbird.warcraft.action.common.create.CreateEvent;
import com.evilbird.warcraft.action.death.RemoveEvent;
import com.evilbird.warcraft.behaviour.ainew.common.guard.ConditionGuard;
import com.evilbird.warcraft.behaviour.ainew.common.guard.EventGuard;
import com.evilbird.warcraft.behaviour.ainew.common.select.SelectFirstSubject;
import com.evilbird.warcraft.behaviour.ainew.common.select.SelectSubjects;
import com.evilbird.warcraft.object.common.query.UnitOperations;

import javax.inject.Inject;
import java.util.Objects;

/**
 * A {@link Sequence} implementation representing the steps required by the
 * construct behaviour. Namely to select a building for construction, a nearby
 * builder, an unoccupied building location and then to execute the construct
 * action.
 *
 * @author Blair Butterworth
 */
@SuppressWarnings("unchecked")
public class ConstructSequence extends Sequence<ConstructData>
{
    @Inject
    public ConstructSequence(
        Events events,
        ConstructNext selectBuilding,
        ConstructLocation selectLocation,
        ConstructTask construct)
    {
        super(updateManifest(events),
            selectBuilder(),
            selectBuilding,
            selectLocation,
            construct);
    }

    private static Task<ConstructData> selectBuilder() {
        return new SelectFirstSubject<ConstructData>()
            .from(ConstructData::getPlayer)
            .into(ConstructData::setBuilder)
            .when(ConstructStatus::validBuilder);
    }

    private static Task<ConstructData> updateManifest(Events events) {
        Task<ConstructData> initializeTrigger = new ConditionGuard<ConstructData, ConstructManifest>()
            .from(ConstructData::getManifest)
            .pass(Objects::isNull);

        Task<ConstructData> eventTrigger = new EventGuard<ConstructData>(events)
            .trigger(CreateEvent.class)
            .trigger(RemoveEvent.class)
            .subject(UnitOperations::isBuilding);

        Task<ConstructData> updateManifest = new SelectSubjects<ConstructData>()
            .from(ConstructData::getPlayer)
            .into(ConstructData::updateManifest)
            .when(UnitOperations::isBuilding);

        Task<ConstructData> triggerUpdate = new Selector<>(
            initializeTrigger, eventTrigger);

        Task<ConstructData> updateSequence = new Sequence<>(
            triggerUpdate, updateManifest);

        return new AlwaysSucceed<>(updateSequence);
    }
}
