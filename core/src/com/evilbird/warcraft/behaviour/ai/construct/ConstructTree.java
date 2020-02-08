/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ai.construct;

import com.evilbird.warcraft.behaviour.ai.PlayerData;
import com.evilbird.warcraft.behaviour.ai.common.tree.SubTree;

import javax.inject.Inject;

/**
 * A behaviour tree that constructs buildings for an enemy player.
 *
 * @author Blair Butterworth
 */
public class ConstructTree extends SubTree<PlayerData, ConstructData>
{
    @Inject
    public ConstructTree(ConstructSequence sequence) {
        super(sequence);
    }

    @Override
    protected ConstructData convertObject(PlayerData object) {
        return new ConstructData(object.getPlayer());
    }
}
