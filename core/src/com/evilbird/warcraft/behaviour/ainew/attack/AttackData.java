/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ainew.attack;

import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.spatial.GameObjectNode;
import com.evilbird.engine.object.spatial.GameObjectNodeSet;
import com.evilbird.warcraft.object.common.capability.OffensiveObject;

import java.util.Collection;

/**
 * A bundle of attributes encapsulating the data required by attack behaviour.
 *
 * @author Blair Butterworth
 */
public class AttackData
{
    private OffensiveObject attacker;
    private GameObjectNode attackerPosition;
    private GameObjectNodeSet attackablePositions;
    private Collection<GameObject> targets;

    public AttackData(OffensiveObject attacker) {
        this.attacker = attacker;
    }

    public OffensiveObject getAttacker() {
        return attacker;
    }

    public GameObjectNode getAttackerPosition() {
        return attackerPosition;
    }

    public GameObjectNodeSet getAttackablePositions() {
        return attackablePositions;
    }

    public Collection<GameObject> getTargets() {
        return targets;
    }

    public void setAttacker(GameObject attacker) {
        this.attacker = (OffensiveObject)attacker;
    }

    public void setAttackerPosition(GameObjectNode attackerPosition) {
        this.attackerPosition = attackerPosition;
    }

    public void setAttackablePositions(GameObjectNodeSet attackablePositions) {
        this.attackablePositions = attackablePositions;
    }

    public void setTargets(Collection<GameObject> targets) {
        this.targets = targets;
    }
}
