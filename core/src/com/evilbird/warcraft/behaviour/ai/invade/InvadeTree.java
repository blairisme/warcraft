/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ai.invade;

import com.evilbird.engine.behaviour.framework.tree.SubTree;
import com.evilbird.warcraft.behaviour.ai.PlayerData;

import javax.inject.Inject;

/**
 * A behaviour tree that instructs groups of idle attackers to attack an
 * enemy.
 *
 * @author Blair Butterworth
 */
public class InvadeTree extends SubTree<PlayerData, InvadeData>
{
    @Inject
    public InvadeTree(InvadeSequence invadeSequence) {
        super(invadeSequence);
    }

    @Override
    protected InvadeData convertObject(PlayerData playerData) {
        return new InvadeData(playerData.getPlayer());
    }
}
