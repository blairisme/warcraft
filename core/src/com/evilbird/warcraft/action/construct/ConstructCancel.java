/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.construct;

import com.evilbird.engine.action.framework.DelegateAction;
import com.evilbird.engine.events.EventQueue;
import com.evilbird.warcraft.action.death.DeathAction;
import com.evilbird.warcraft.item.common.resource.ResourceQuantity;
import com.evilbird.warcraft.item.data.player.Player;
import com.evilbird.warcraft.item.unit.UnitCosts;
import com.evilbird.warcraft.item.unit.UnitType;
import com.evilbird.warcraft.item.unit.building.Building;
import com.evilbird.warcraft.item.unit.gatherer.Gatherer;

import javax.inject.Inject;
import java.util.Collection;

import static com.evilbird.warcraft.action.common.exclusion.Exclusion.restore;
import static com.evilbird.warcraft.action.common.transfer.TransferOperations.setResources;
import static com.evilbird.warcraft.action.construct.ConstructEvents.notifyConstructCancelled;
import static com.evilbird.warcraft.item.common.query.UnitOperations.getPlayer;
import static com.evilbird.warcraft.item.common.query.UnitOperations.moveAdjacent;

/**
 * Instances of this class stop the construction of a building.
 *
 * @author Blair Butterworth
 */
public class ConstructCancel extends DelegateAction
{
    private transient EventQueue events;
    private transient boolean cancelled;

    /**
     * Creates a new instance of this class given a {@link EventQueue}
     * used to report the transferAll of resources involved in the refund for the
     * partially complete train operation.
     *
     * @param events    a {@code EventQueue} instance. This parameter
     *                  cannot be {@code null}.
     * @param death     an action that will be used to destroy the construction
     *                  site.
     */
    @Inject
    public ConstructCancel(EventQueue events, DeathAction death) {
        super(death);
        this.events = events;
        this.cancelled = false;
    }

    @Override
    public boolean act(float delta) {
        if (! cancelled) {
            cancelled = true;
            cancel();
        }
        return super.act(delta);
    }

    @Override
    public void reset() {
        super.reset();
        cancelled = false;
    }

    private void cancel() {
        Building building = (Building)getItem();
        Gatherer builder = (Gatherer)getTarget();

        configureBuilding(building);
        configureBuilder(builder, building);
        configurePlayer(building);

        notifyConstructCancelled(events, builder, building);
    }

    private void configureBuilding(Building building) {
        building.setAssociatedItem(null);
        building.setConstructionProgress(1);
        building.setHealth(0);
    }

    private void configureBuilder(Gatherer builder, Building building) {
        builder.setAssociatedItem(null);
        builder.clearActions();

        if (!builder.getVisible()) {
            restore(builder);
            moveAdjacent(builder, building);
        }
    }

    private void configurePlayer(Building building) {
        Player player = getPlayer(building);
        setResources(player, getBuildingCost(), events);
    }

    private Collection<ResourceQuantity> getBuildingCost() {
        return UnitCosts.cost(getBuildingType());
    }

    private UnitType getBuildingType() {
        ConstructActions constructAction = (ConstructActions)getIdentifier();
        return constructAction.getProduct();
    }
}
