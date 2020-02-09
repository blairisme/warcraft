/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ai.production.train;

import com.evilbird.warcraft.behaviour.ai.common.tree.SubTree;
import com.evilbird.warcraft.behaviour.ai.production.ProductionData;

import javax.inject.Inject;

/**
 * A behaviour tree that trains units for an AI player.
 *
 * @author Blair Butterworth
 */
public class TrainBehaviour extends SubTree<ProductionData, TrainData>
{
    @Inject
    public TrainBehaviour(TrainSequence sequence) {
        super(sequence);
    }

    @Override
    protected TrainData convertObject(ProductionData data) {
        return new TrainData(data.getPlayer(), data.getProduct());
    }
}
