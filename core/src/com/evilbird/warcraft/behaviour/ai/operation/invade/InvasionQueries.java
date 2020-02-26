/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ai.operation.invade;

import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.cache.CachedPredicate;
import com.evilbird.warcraft.object.common.capability.OffensiveObject;
import com.evilbird.warcraft.object.common.capability.PerishableObject;
import com.evilbird.warcraft.object.common.capability.TerrainType;
import com.evilbird.warcraft.object.data.player.Player;

import java.util.function.Predicate;

/**
 * Defines common {@link Predicate Predicates} for querying the state of
 * {@link OffensiveObject offensive} and {@link PerishableObject perishable}
 * game objects.
 *
 * @author Blair Butterworth
 */
public class InvasionQueries
{
    /**
     * A {@link Predicate condition} that determines if a given
     * {@link GameObject} is a gatherer.
     */
    public static final Predicate<GameObject> MovableAttackers =
        new CachedPredicate<>(InvasionOperations::isMovableAttacker);

    /**
     * A {@link Predicate condition} that determines if a given
     * {@link GameObject attacker} is alive and idle.
     */
    public static final Predicate<GameObject> IdleAttackers =
        InvasionOperations::isIdleAttacker;

    /**
     * A {@link Predicate condition} that determines if a given
     * {@link GameObject} can receive damage and has not yet been killed.
     */
    public static final Predicate<GameObject> PotentialTargets =
        new CachedPredicate<>(InvasionOperations::isPotentialTarget);

    /**
     * A {@link Predicate condition} that determines if the given
     * {@link GameObject} is a player that belongs to a different team than
     * the given player.
     */
    public static Predicate<GameObject> EnemyPlayers(Player player) {
        return new CachedPredicate<>(object -> InvasionOperations.isEnemyPlayer(player, object));
    }

    /**
     * A {@link Predicate condition} that determines if an attacker can attack
     * over the given {@link TerrainType}.
     */
    public static Predicate<GameObject> AttackPossible(TerrainType terrain) {
        return new CachedPredicate<>(object -> InvasionOperations.isAttackPossible(terrain, object));
    }

    /**
     * Disable construction of static helper class.
     */
    private InvasionQueries() {
    }
}
