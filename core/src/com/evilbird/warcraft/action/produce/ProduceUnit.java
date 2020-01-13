/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.produce;

import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.common.time.GameTimer;
import com.evilbird.engine.object.GameObjectFactory;
import com.evilbird.warcraft.action.common.create.CreateEvents;
import com.evilbird.warcraft.action.common.transfer.ResourceTransfer;
import com.evilbird.warcraft.common.WarcraftPreferences;
import com.evilbird.warcraft.data.resource.ResourceSet;
import com.evilbird.warcraft.object.common.capability.MovableObject;
import com.evilbird.warcraft.object.data.player.Player;
import com.evilbird.warcraft.object.unit.Unit;
import com.evilbird.warcraft.object.unit.UnitType;
import com.evilbird.warcraft.object.unit.building.Building;

import javax.inject.Inject;

import static com.evilbird.engine.action.ActionConstants.ActionComplete;
import static com.evilbird.engine.action.ActionConstants.ActionIncomplete;
import static com.evilbird.warcraft.action.common.production.ProductionOperations.getProductionCost;
import static com.evilbird.warcraft.action.common.production.ProductionOperations.getProductionTime;
import static com.evilbird.warcraft.object.common.query.UnitOperations.getPlayer;
import static com.evilbird.warcraft.object.common.query.UnitOperations.moveAdjacent;
import static com.evilbird.warcraft.object.unit.UnitSound.Ready;

/**
 * Instances of this action sequence create a new unit, decrementing the
 * players resources and adding delay before the new unit can be used. The new
 * unit will be placed next to the building that created it, in the first
 * unoccupied space.
 *
 * @author Blair Butterworth
 */
public class ProduceUnit extends BasicAction
{
    private transient GameTimer timer;
    private transient GameObjectFactory factory;
    private transient ResourceTransfer resources;
    private transient WarcraftPreferences preferences;
    private transient ProduceEvents produceEvents;
    private transient CreateEvents createEvents;

    @Inject
    public ProduceUnit(
        CreateEvents createEvents,
        ProduceEvents produceEvents,
        GameObjectFactory factory,
        WarcraftPreferences preferences,
        ResourceTransfer resources)
    {
        this.factory = factory;
        this.resources = resources;
        this.preferences = preferences;
        this.createEvents = createEvents;
        this.produceEvents = produceEvents;
    }

    @Override
    public boolean act(float time) {
        if (! initialized()) {
            return initialize();
        }
        if (! loaded()) {
            return load();
        }
        if (timer.advance(time)) {
            return complete();
        }
        return update();
    }

    @Override
    public void reset() {
        super.reset();
        timer = null;
    }

    @Override
    public void restart() {
        super.restart();
        timer = null;
    }

    private boolean initialized() {
        Building building = (Building) getSubject();
        return building.isProducing();
    }

    private boolean initialize() {
        Building building = (Building) getSubject();
        building.setProductionProgress(0);

        Player player = getPlayer(building);
        UnitType product = getProduct();

        ResourceSet cost = getProductionCost(product, preferences);
        resources.setResources(player, cost.negate());

        produceEvents.notifyProductionStarted(building);
        return ActionIncomplete;
    }

    protected boolean loaded() {
        return timer != null;
    }

    protected boolean load() {
        Building building = (Building) getSubject();
        UnitType product = getProduct();
        timer = new GameTimer(getProductionTime(product, preferences));
        timer.advance(building.getProductionProgress() * timer.duration());
        return ActionIncomplete;
    }

    private boolean update() {
        Building building = (Building) getSubject();
        building.setProductionProgress(timer.completion());
        return ActionIncomplete;
    }

    private boolean complete() {
        Unit product = (Unit)factory.get(getProduct());
        Building building = (Building) getSubject();

        completeProduct(building, product);
        completeBuilding(building, product);
        notifyComplete(building, product);

        return ActionComplete;
    }

    private void completeProduct(Building building, Unit product) {
        product.setVisible(true);
        moveAdjacent((MovableObject)product, building);

        Player player = getPlayer(building);
        player.addObject(product);

        if (preferences.isSpeechEnabled()) {
            product.setSound(Ready);
        }
    }

    private void completeBuilding(Building building, Unit product) {
        building.setProductionProgress(1);
    }

    private void notifyComplete(Building building, Unit product) {
        createEvents.notifyCreate(product);
        produceEvents.notifyProductionCompleted(building);
    }

    private UnitType getProduct() {
        ProduceUnitActions identifier = (ProduceUnitActions)getIdentifier();
        return identifier.getProduct();
    }
}