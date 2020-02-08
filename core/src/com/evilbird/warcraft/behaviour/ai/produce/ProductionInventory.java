/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ai.produce;

import com.badlogic.gdx.ai.btree.Task;
import com.badlogic.gdx.ai.btree.branch.Selector;
import com.badlogic.gdx.ai.btree.branch.Sequence;
import com.badlogic.gdx.ai.btree.decorator.AlwaysSucceed;
import com.evilbird.engine.events.Events;
import com.evilbird.engine.object.GameObject;
import com.evilbird.warcraft.action.common.create.CreateEvent;
import com.evilbird.warcraft.action.death.RemoveEvent;
import com.evilbird.warcraft.behaviour.ai.common.guard.ConditionGuard;
import com.evilbird.warcraft.behaviour.ai.common.guard.EventGuard;
import com.evilbird.warcraft.behaviour.ai.common.select.SelectSubjects;
import com.evilbird.warcraft.object.unit.building.Building;
import com.evilbird.warcraft.object.unit.combatant.Combatant;

import javax.inject.Inject;
import java.util.Objects;

/**
 * A behaviour sequence that updates the production manifest when units are
 * created and removed.
 *
 * @author Blair Butterworth
 */
@SuppressWarnings("unchecked")
public class ProductionInventory extends AlwaysSucceed<ProductionData>
{
    @Inject
    public ProductionInventory(Events events) {
        Task<ProductionData> initializeTrigger = new ConditionGuard<ProductionData, ProductionManifest>()
            .from(ProductionData::getManifest)
            .pass(Objects::isNull);

        Task<ProductionData> eventTrigger = new EventGuard<ProductionData>(events)
            .trigger(CreateEvent.class)
            .trigger(RemoveEvent.class)
            .subject(ProductionInventory::validProduct);

        Task<ProductionData> updateManifest = new SelectSubjects<ProductionData>()
            .from(ProductionData::getPlayer)
            .into(ProductionData::updateManifest)
            .when(ProductionInventory::validProduct);

        Task<ProductionData> triggerUpdate = new Selector<>(
            initializeTrigger, eventTrigger);

        Task<ProductionData> updateSequence = new Sequence<>(
            triggerUpdate, updateManifest);

        addChildToTask(updateSequence);
    }

    private static boolean validProduct(GameObject gameObject) {
        return (gameObject instanceof Building || gameObject instanceof Combatant);
    }
}
