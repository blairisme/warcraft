/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ai.production.produce;

import com.badlogic.gdx.ai.btree.LeafTask;
import com.badlogic.gdx.ai.btree.Task;
import com.evilbird.engine.common.collection.Lists;
import com.evilbird.engine.object.GameObject;
import com.evilbird.warcraft.data.product.Product;
import com.evilbird.warcraft.data.product.Production;
import com.evilbird.warcraft.object.data.player.Player;
import com.evilbird.warcraft.object.unit.UnitArchetype;
import com.evilbird.warcraft.object.unit.building.Building;
import org.apache.commons.lang3.RandomUtils;

import javax.inject.Inject;
import java.util.Collection;
import java.util.List;

import static com.badlogic.gdx.ai.btree.Task.Status.FAILED;
import static com.badlogic.gdx.ai.btree.Task.Status.SUCCEEDED;
import static com.evilbird.engine.common.collection.CollectionUtils.filter;
import static com.evilbird.warcraft.object.common.query.UnitPredicates.hasArchetype;

/**
 * A task that selects an appropriate production facility for the product
 * contained in the tasks blackboard.
 *
 * @author Blair Butterworth
 */
public class SelectFacility extends LeafTask<ProduceData>
{
    @Inject
    public SelectFacility() {
    }

    @Override
    public Status execute() {
        ProduceData data = getObject();
        UnitArchetype type = getFacilityType(data);
        Building facility = getFacility(data, type);
        data.setProducer(facility);
        return facility != null ? SUCCEEDED : FAILED;
    }

    private UnitArchetype getFacilityType(ProduceData data) {
        Product product = data.getProduct();
        Production production = product.getProduction();
        return production.getProducer();
    }

    private Building getFacility(ProduceData data, UnitArchetype archetype) {
        Player player = data.getPlayer();

        Collection<GameObject> potentialFacilities = player.findAll(hasArchetype(archetype));
        Collection<GameObject> idleFacilities = filter(potentialFacilities, this::isIdle);

        if (!idleFacilities.isEmpty()) {
            List<GameObject> facilities = Lists.toList(idleFacilities);
            return (Building)facilities.get(RandomUtils.nextInt(0, facilities.size()));
        }
        return null;
    }

    private boolean isIdle(GameObject gameObject) {
        if (gameObject instanceof Building) {
            Building building = (Building)gameObject;
            return !building.isProducing();
        }
        return false;
    }

    @Override
    protected Task<ProduceData> copyTo(Task<ProduceData> task) {
        return task;
    }
}
