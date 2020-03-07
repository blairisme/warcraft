/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ai.operation.invade;

import com.evilbird.engine.object.GameObject;
import com.evilbird.warcraft.object.common.capability.MovableObject;
import com.evilbird.warcraft.object.common.capability.OffensiveCapability;
import com.evilbird.warcraft.object.common.capability.OffensiveObject;
import com.evilbird.warcraft.object.common.capability.PerishableObject;
import com.evilbird.warcraft.object.common.capability.TerrainType;
import com.evilbird.warcraft.object.data.player.Player;
import com.evilbird.warcraft.object.layer.LayerType;
import com.evilbird.warcraft.object.unit.Unit;
import com.evilbird.warcraft.object.unit.combatant.naval.Transport;

/**
 * Defines common operations for querying the state of {@link OffensiveObject
 * offensive} game objects.
 *
 * @author Blair Butterworth
 */
public class InvasionOperations
{
    /**
     * Determines if the given {@link GameObject} can do damage to other
     * {@code GameObjects} as well as being alive and currently idle.
     */
    public static boolean isAttackPossible(TerrainType attackerTerrain, GameObject object) {
        if (object instanceof PerishableObject) {
            PerishableObject target = (PerishableObject)object;
            TerrainType targetTerrain = target.getTerrainType();
            return targetTerrain == attackerTerrain;
        }
        return false;
    }

    /**
     * Determines if the given {@link GameObject} is a player that belongs to a
     * different team than the given player.
     */
    public static boolean isEnemy(Player player, GameObject object) {
        if (object instanceof Player) {
            Player otherPlayer = (Player)object;
            return otherPlayer.isEnemy(player)
                && !otherPlayer.isNeutral()
                && !otherPlayer.isDefeated();
        }
        return false;
    }

    /**
     * Determines if the given {@link GameObject} is alive and currently idle.
     */
    public static boolean isIdle(GameObject object) {
        if (object instanceof Unit) {
            Unit attacker = (Unit)object;
            return attacker.isAlive() && attacker.isIdle();
        }
        return false;
    }

    /**
     * Determines if the given {@link GameObject} can do damage to other
     * {@code GameObjects}.
     */
    public static boolean isPotentialAttacker(GameObject gameObject) {
        if (gameObject instanceof OffensiveObject && gameObject instanceof MovableObject) {
            OffensiveObject combatant = (OffensiveObject)gameObject;
            return combatant.getAttackCapability() != OffensiveCapability.None;
        }
        return false;
    }

    /**
     * Determines if the given {@link GameObject} can receive damage and has
     * not yet been killed.
     */
    public static boolean isPotentialTarget(GameObject object) {
        if (object instanceof PerishableObject) {
            PerishableObject target = (PerishableObject)object;
            return target.isAlive();
        }
        return false;
    }

    /**
     * Determines if the given {@link GameObject} can transport other
     * {@code GameObjects}.
     */
    public static boolean isPotentialTransport(GameObject object) {
        return object instanceof Transport;
    }

    /**
     * Determines if the given {@link GameObject} represents part of the shore
     * line.
     */
    public static boolean isShoreLine(GameObject object) {
        return object.getType() == LayerType.Shore;
    }

    /**
     * Disable construction of static helper class.
     */
    private InvasionOperations() {
    }
}
