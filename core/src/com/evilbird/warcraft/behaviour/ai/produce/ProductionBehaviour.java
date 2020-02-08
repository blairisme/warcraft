/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ai.produce;

import com.evilbird.warcraft.behaviour.ai.PlayerData;
import com.evilbird.warcraft.behaviour.ai.common.tree.SubTree;

import javax.inject.Inject;

/**
 * A behaviour tree that produces units, buildings and upgrades for an AI player.
 *
 * @author Blair Butterworth
 */
public class ProductionBehaviour extends SubTree<PlayerData, ProductionData>
{
    @Inject
    public ProductionBehaviour(ProductionSequence sequence) {
        super(sequence);
    }

    @Override
    protected ProductionData convertObject(PlayerData object) {
        return new ProductionData(object.getPlayer());
    }
}
