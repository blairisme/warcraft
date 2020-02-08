/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ai.produce;

import com.evilbird.warcraft.behaviour.ai.common.tree.ConditionBranch;
import com.evilbird.warcraft.behaviour.ai.produce.construct.ConstructBehaviour;
import com.evilbird.warcraft.behaviour.ai.produce.train.TrainBehaviour;
import com.evilbird.warcraft.object.unit.UnitArchetype;

import javax.inject.Inject;

public class ProductionTask extends ConditionBranch<ProductionData, UnitArchetype>
{
    @Inject
    public ProductionTask(ConstructBehaviour constructBehaviour, TrainBehaviour trainBehaviour) {
        from(ProductionData::getProductArchetype);
        branch(UnitArchetype::isBuilding, constructBehaviour);
        branch(UnitArchetype::isUnit, trainBehaviour);
    }
}
