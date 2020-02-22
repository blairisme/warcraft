/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ai.operation.production;

import com.evilbird.engine.behaviour.framework.tree.ConditionBranch;
import com.evilbird.warcraft.behaviour.ai.operation.production.construct.ConstructBehaviour;
import com.evilbird.warcraft.behaviour.ai.operation.production.produce.ProduceBehaviour;
import com.evilbird.warcraft.data.product.Product;
import com.evilbird.warcraft.data.upgrade.Upgrade;
import com.evilbird.warcraft.object.unit.UnitArchetype;
import com.evilbird.warcraft.object.unit.UnitType;

import javax.inject.Inject;

/**
 * A branch task that executes a behaviour branch appropriate for the product
 * contained in the tasks blackboard. {@link ConstructBehaviour} is used for
 * building products. {@link ProduceBehaviour} is used for unit products.
 *
 * @author Blair Butterworth
 */
public class ProductionTask extends ConditionBranch<ProductionData, Product>
{
    @Inject
    public ProductionTask(ConstructBehaviour constructBehaviour, ProduceBehaviour produceBehaviour) {
        from(ProductionData::getProduct);
        branch(ProductionTask::isConstructable, constructBehaviour);
        branch(ProductionTask::isProducible, produceBehaviour);
    }

    private static boolean isConstructable(Product product) {
        if (product instanceof UnitType) {
            UnitType unitType = (UnitType)product;
            UnitArchetype unitArchetype = unitType.getArchetype();
            return unitArchetype.isBuilding();
        }
        return false;
    }

    private static boolean isProducible(Product product) {
        if (product instanceof UnitType) {
            UnitType unitType = (UnitType)product;
            UnitArchetype unitArchetype = unitType.getArchetype();
            return unitArchetype.isUnit();
        }
        return product instanceof Upgrade;
    }
}
