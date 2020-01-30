/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ainew.gather;

import com.evilbird.warcraft.behaviour.ainew.PlayerData;
import com.evilbird.warcraft.behaviour.ainew.common.SubTree;

import javax.inject.Inject;

/**
 * A behaviour tree that gatherers resources for an enemy player.
 *
 * @author Blair Butterworth
 */
public class GatherTree extends SubTree<PlayerData, GatherData>
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
