/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ai.operation.production.construct;

import com.evilbird.engine.behaviour.framework.tree.SubTree;
import com.evilbird.warcraft.behaviour.ai.operation.production.ProductionData;
import com.evilbird.warcraft.object.unit.UnitType;

import javax.inject.Inject;

/**
 * A behaviour tree that constructs buildings for an enemy player.
 *
 * @author Blair Butterworth
 */
public class ConstructBehaviour extends SubTree<ProductionData, ConstructData>
{
    @Inject
    public ConstructBehaviour(ConstructSequence sequence) {
        super(sequence);
    }

    @Override
    protected ConstructData convertObject(ProductionData data) {
        return new ConstructData(data.getPlayer(), (UnitType)data.getProduct());
    }
}
