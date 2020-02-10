/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ai.wander;

import com.evilbird.warcraft.behaviour.ai.PlayerData;
import com.evilbird.engine.behaviour.framework.tree.SubTree;

import javax.inject.Inject;

/**
 * A behaviour tree that selects a random movable object and assigns it a
 * random destination, causing it to wander.
 *
 * @author Blair Butterworth
 */
public class WanderTree extends SubTree<com.evilbird.warcraft.behaviour.ai.PlayerData, WanderData>
{
    @Inject
    public WanderTree(WanderSequence wanderSequence) {
        super(wanderSequence);
    }

    @Override
    protected WanderData convertObject(PlayerData playerData) {
        return new WanderData(playerData.getPlayer());
    }
}
