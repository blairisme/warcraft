/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ai.gather;

import com.evilbird.warcraft.behaviour.ai.PlayerData;
import com.evilbird.warcraft.behaviour.ai.common.tree.SubTree;

import javax.inject.Inject;

/**
 * A behaviour tree that gatherers resources for an enemy player.
 *
 * @author Blair Butterworth
 */
public class GatherTree extends SubTree<com.evilbird.warcraft.behaviour.ai.PlayerData, GatherData>
{
    @Inject
    public GatherTree(GatherSequence gatherSequence) {
        super(gatherSequence);
    }

    @Override
    protected GatherData convertObject(PlayerData playerData) {
        return new GatherData(playerData.getPlayer());
    }
}
