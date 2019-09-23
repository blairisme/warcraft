/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.produce;

import com.evilbird.engine.action.framework.TemporalAction;
import com.evilbird.engine.item.ItemFactory;
import com.evilbird.warcraft.action.common.create.CreateEvents;
import com.evilbird.warcraft.action.common.transfer.ResourceTransfer;
import com.evilbird.warcraft.common.WarcraftPreferences;
import com.evilbird.warcraft.item.common.movement.Movable;
import com.evilbird.warcraft.item.common.resource.ResourceSet;
import com.evilbird.warcraft.item.data.player.Player;
import com.evilbird.warcraft.item.unit.Unit;
import com.evilbird.warcraft.item.unit.UnitType;
import com.evilbird.warcraft.item.unit.building.Building;

import javax.inject.Inject;

import static com.evilbird.engine.action.ActionConstants.ActionComplete;
import static com.evilbird.engine.action.ActionConstants.ActionIncomplete;
import static com.evilbird.warcraft.item.common.query.UnitOperations.getPlayer;
import static com.evilbird.warcraft.item.common.query.UnitOperations.moveAdjacent;
import static com.evilbird.warcraft.item.unit.UnitCosts.buildTime;
import static com.evilbird.warcraft.item.unit.UnitCosts.cost;
import static com.evilbird.warcraft.item.unit.UnitSound.Ready;

/**
 * Instances of this action sequence create a new unit, decrementing the
 * players resources and adding delay before the new unit can be used. The new
 * unit will be placed next to the building that created it, in the first
 * unoccupied space.
 *
 * @author Blair Butterworth
 */
public class ProduceUnit extends TemporalAction
{

    private transient ItemFactory factory;
    private transient ResourceTransfer resources;
    private transient WarcraftPreferences preferences;
    private transient ProduceEvents produceEvents;
    private transient CreateEvents createEvents;

    @Inject
    public ProduceUnit(
        CreateEvents createEvents,
        ProduceEvents produceEvents,
        ItemFactory factory,
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
        if (super.act(time)) {
            return complete();
        }
        return update();
    }

    private boolean initialized() {
        Building building = (Building)getItem();
        return building.isProducing();
    }

    private boolean initialize() {
        Building building = (Building)getItem();
        building.setProductionProgress(0);

        Player player = getPlayer(building);
        UnitType product = getProduct();

        ResourceSet cost = new ResourceSet(cost(product));
        resources.setResources(player, cost.negate());

        setDuration(buildTime(product));
        setProgress(building.getProductionProgress() * getDuration());

        produceEvents.notifyProductionStarted(building);
        return ActionIncomplete;
    }

    private boolean complete() {
        Unit product = (Unit)factory.get(getProduct());
        if (preferences.isSpeechEnabled()) {
            product.setSound(Ready);
        }

        Building building = (Building)getItem();
        moveAdjacent((Movable)product, building);

        Player player = getPlayer(building);
        player.addItem(player);

        createEvents.notifyCreate(product);
        produceEvents.notifyProductionCompleted(building);
        return ActionComplete;
    }

    private boolean update() {
        Building building = (Building)getItem();
        building.setProductionProgress(getProgress());
        return ActionIncomplete;
    }

    private UnitType getProduct() {
        ProduceUnitActions identifier = (ProduceUnitActions)getIdentifier();
        return identifier.getProduct();
    }
}