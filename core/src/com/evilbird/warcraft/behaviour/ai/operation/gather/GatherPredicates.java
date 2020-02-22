/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ai.operation.gather;

import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.cache.CachedPredicate;
import com.evilbird.warcraft.object.common.query.UnitOperations;
import com.evilbird.warcraft.object.unit.combatant.gatherer.Gatherer;

import java.util.function.Predicate;

/**
 * Defines common {@link Predicate Predicates} for querying the state of
 * {@link Gatherer} game objects.
 *
 * @author Blair Butterworth
 */
public class GatherPredicates
{
    /**
     * A {@link Predicate condition} that determines if a given
     * {@link GameObject} is a gatherer.
     */
    public static final Predicate<GameObject> Gatherers =
        new CachedPredicate<>(UnitOperations::isGatherer);

    /**
     * A {@link Predicate condition} that determines if a given
     * {@link GameObject} is a gatherer with positive health.
     */
    public static final Predicate<GameObject> LivingGatherers =
        GatherOperations::isLivingGatherer;

    /**
     * A {@link Predicate condition} that determines if a given
     * {@link GameObject} is a gatherer, capable of moving over land, with
     * positive health and no currently assigned actions.
     */
    public static final Predicate<GameObject> AvailableLandGatherers =
        GatherOperations::isAvailableLandGatherer;

    /**
     * A {@link Predicate condition} that determines if a given
     * {@link GameObject} is a gatherer, capable of moving over water, with
     * positive health and no currently assigned actions.
     */
    public static final Predicate<GameObject> AvailableSeaGatherers =
        GatherOperations::isAvailableSeaGatherer;

    /**
     * Disable construction of static helper class.
     */
    private GatherPredicates() {
    }
}
