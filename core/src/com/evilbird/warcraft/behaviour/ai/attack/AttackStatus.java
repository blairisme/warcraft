/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.behaviour.ai.attack;

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
        return attacker.isAlive() && isAttackerIdle(attacker);
    }

    /**
     * Determines if the given {@link PerishableObject target} can be attacked
     * by the given {@link OffensiveObject attacker}.
     */
    public static boolean isValidTarget(OffensiveObject attacker, PerishableObject target) {
        return target.isAlive()
            && target.isAttackable()
            && target.isEnemy(attacker)
            && !isNeutral(target)
            && attacker.isAlive()
            && attacker.isAttackPossible(target)
            && isAttackerIdle(attacker)
            && !isNeutral(attacker)
            && !isCapturable(attacker);
    }

    private static boolean isAttackerIdle(OffensiveObject attacker) {
        return attacker.getAttackPlurality() != Individual || !attacker.hasActions();
    }

    private static boolean isNeutral(PerishableObject target) {
        Player player = target.getTeam();
        return player != null && player.isNeutral();
    }

    private static boolean isCapturable(PerishableObject target) {
        Player player = target.getTeam();
        return player != null && player.isCapturable();
    }
}
