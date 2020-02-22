/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ai.operation.production;

import com.badlogic.gdx.ai.btree.LeafTask;
import com.badlogic.gdx.ai.btree.Task;
import com.evilbird.engine.common.collection.CollectionUtils;
import com.evilbird.engine.events.Events;
import com.evilbird.engine.object.GameObject;
import com.evilbird.warcraft.action.construct.ConstructEvent;
import com.evilbird.warcraft.action.death.RemoveEvent;
import com.evilbird.warcraft.action.produce.ProduceEvent;
import com.evilbird.warcraft.data.product.Product;
import com.evilbird.warcraft.object.common.query.UnitOperations;
import com.evilbird.warcraft.object.data.player.Player;
import com.evilbird.warcraft.object.unit.Unit;
import com.evilbird.warcraft.object.unit.UnitType;

import javax.inject.Inject;
import java.util.Collection;
import java.util.function.Predicate;

import static com.badlogic.gdx.ai.btree.Task.Status.SUCCEEDED;

/**
 * A behaviour sequence that updates the production manifest when units are
 * created and removed.
 *
 * @author Blair Butterworth
 */
@SuppressWarnings("unchecked")
public class ProductionInventory extends LeafTask<ProductionData>
{
    private Events events;

    @Inject
    public ProductionInventory(Events events) {
        this.events = events;
    }

    @Override
    public Status execute() {
        ProductionData data = getObject();
        ProductionManifest manifest = data.getManifest();
        Player player = data.getPlayer();

        populateManifest(manifest, player);
        updateManifest(manifest, player);

        return SUCCEEDED;
    }

    private void populateManifest(ProductionManifest manifest, Player player) {
        if (manifest.isEmpty()) {
            manifest.addAll(getObjects(player, UnitOperations::isBuilding));
            manifest.addAll(getObjects(player, UnitOperations::isCombatant));
            manifest.addAll(player.getUpgrades());
        }
    }

    private Collection<UnitType> getObjects(Player player, Predicate<GameObject> condition) {
        Collection<GameObject> objects = player.findAll(condition);
        return CollectionUtils.convert(objects, object -> (UnitType)object.getType());
    }

    private void updateManifest(ProductionManifest manifest, Player player) {
        evaluateConstruction(manifest, player);
        evaluateProduction(manifest, player);
        evaluateDestruction(manifest, player);
    }

    private void evaluateConstruction(ProductionManifest manifest, Player player) {
        for (ConstructEvent event: events.getEvents(ConstructEvent.class)) {
            GameObject subject = event.getSubject();
            if (event.isConstructing() && isOwnedByPlayer(subject, player)) {
                Product product = (Product)subject.getType();
                manifest.add(product);
            }
        }
    }

    private void evaluateProduction(ProductionManifest manifest, Player player) {
        for (ProduceEvent event: events.getEvents(ProduceEvent.class)) {
            GameObject subject = event.getSubject();
            if (event.isTraining() && isOwnedByPlayer(subject, player)) {
                Product product = event.getProduct();
                manifest.add(product);
            }
        }
    }

    private void evaluateDestruction(ProductionManifest manifest, Player player) {
        for (RemoveEvent event: events.getEvents(RemoveEvent.class)) {
            GameObject subject = event.getSubject();
            if (isOwnedByPlayer(subject, player)) {
                Product product = (Product)subject.getType();
                manifest.remove(product);
            }
        }
    }

    private boolean isOwnedByPlayer(GameObject object, Player player) {
        if (object instanceof Unit) {
            Unit unit = (Unit)object;
            return unit.getTeam() == player;
        }
        return false;
    }

    @Override
    protected Task<ProductionData> copyTo(Task<ProductionData> task) {
        ProductionInventory productionInventory = (ProductionInventory)task;
        productionInventory.events = this.events;
        return productionInventory;
    }
}
