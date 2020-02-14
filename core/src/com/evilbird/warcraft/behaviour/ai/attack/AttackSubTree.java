/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ai.attack;

import com.evilbird.engine.behaviour.framework.tree.SubTree;
import com.evilbird.warcraft.behaviour.ai.PlayerData;
import com.evilbird.warcraft.object.common.capability.OffensiveObject;

import javax.inject.Inject;

/**
 * A behaviour tree that instructs an attacker to attack an available
 * target.
 *
 * @author Blair Butterworth
 */
public class AttackSubTree extends SubTree<PlayerData, AttackData>
{
    private OffensiveObject attacker;

    @Inject
    public AttackSubTree(AttackSequence attackSequence) {
        super(attackSequence);
    }

    public void setSubject(OffensiveObject attacker) {
        this.attacker = attacker;
    }

    @Override
    protected AttackData convertObject(PlayerData playerData) {
        return new AttackData(attacker);
    }
}
