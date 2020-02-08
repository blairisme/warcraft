/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ai.attack;

import com.evilbird.engine.object.GameObject;
import com.evilbird.warcraft.object.common.capability.OffensiveObject;
import com.evilbird.warcraft.object.common.capability.PerishableObject;
import com.evilbird.warcraft.object.data.player.Player;

import static com.evilbird.warcraft.object.common.capability.OffensivePlurality.Individual;

/**
 * Provides utility functions for determining if game objects can attack each
 * other.
 *
 * @author Blair Butterworth
 */
public class AttackStatus
{
    /**
     * Disable construction of static helper class.
     */
    private AttackStatus() {
    }

    /**
     * Determines if the given attacker is prepared to attack. Specifically if
     * its still alive and isn't currently engaged in another action.
     */
    public static boolean isValidAttacker(OffensiveObject attacker) {
        return attacker.isAlive()
            && isAttackerIdle(attacker)
            && !isNeutral(attacker)
            && !isPassive(attacker);
    }

    /**
     * Determines if the given {@link GameObject target} can be attacked
     * by the given {@link OffensiveObject attacker}.
     */
    public static boolean isValidTarget(OffensiveObject attacker, GameObject target) {
        if (target instanceof PerishableObject) {
            return isValidTarget(attacker, (PerishableObject)target);
        }
        return false;
    }

    /**
     * Determines if the given {@link PerishableObject target} can be attacked
     * by the given {@link OffensiveObject attacker}.
     */
    public static boolean isValidTarget(OffensiveObject attacker, PerishableObject target) {
        return target.isAlive()
            && target.isAttackable()
            && target.isEnemy(attacker)
            && attacker.isAttackPossible(target)
            && !isNeutral(target)
            && !isPassive(target);
    }

    private static boolean isAttackerIdle(OffensiveObject attacker) {
        return attacker.getAttackPlurality() != Individual || !attacker.hasActions();
    }

    private static boolean isNeutral(PerishableObject target) {
        Player player = target.getTeam();
        return player != null && player.isNeutral();
    }

    private static boolean isPassive(PerishableObject target) {
        Player player = target.getTeam();
        return player != null && player.isPassive();
    }
}
