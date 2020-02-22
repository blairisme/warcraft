/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ai.operation.production.produce;

import com.evilbird.engine.behaviour.framework.tree.SubTree;
import com.evilbird.warcraft.behaviour.ai.operation.production.ProductionData;

import javax.inject.Inject;

/**
 * A behaviour tree that trains or upgrades units for an AI player.
 *
 * @author Blair Butterworth
 */
public class ProduceBehaviour extends SubTree<ProductionData, ProduceData>
{
    @Inject
    public ProduceBehaviour(ProduceSequence sequence) {
        super(sequence);
    }

    @Override
    protected ProduceData convertObject(ProductionData data) {
        return new ProduceData(data.getPlayer(), data.getProduct());
    }
}
