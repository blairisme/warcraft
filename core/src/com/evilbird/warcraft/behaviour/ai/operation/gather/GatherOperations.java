/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ai.operation.gather;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.object.GameObject;
import com.evilbird.warcraft.action.gather.GatherActions;
import com.evilbird.warcraft.data.resource.ResourceType;
import com.evilbird.warcraft.object.unit.combatant.gatherer.Gatherer;
import com.evilbird.warcraft.object.unit.combatant.gatherer.LandGatherer;
import com.evilbird.warcraft.object.unit.combatant.gatherer.SeaGatherer;

/**
 * Defines common operations for querying the state of {@link Gatherer} game
 * objects.
 *
 * @author Blair Butterworth
 */
public class GatherOperations
{
    /**
     * Determines if the given {@link GameObject} is a gatherer with positive
     * health.
     */
    public static boolean isLivingGatherer(GameObject object) {
        if (object instanceof Gatherer) {
            Gatherer gatherer = (Gatherer)object;
            return gatherer.isAlive();
        }
        return false;
    }

    /**
     * Determines if the given {@link GameObject} is a gatherer, capable of
     * moving over land, with positive health and no currently assigned
     * actions.
     */
    public static boolean isAvailableLandGatherer(GameObject object) {
        if (object instanceof LandGatherer) {
            LandGatherer gatherer = (LandGatherer)object;
            return gatherer.isAlive() && gatherer.isIdle();
        }
        return false;
    }

    /**
     * Determines if the given {@link GameObject} is a gatherer, capable of
     * moving over water, with positive health and no currently assigned
     * actions.
     */
    public static boolean isAvailableSeaGatherer(GameObject object) {
        if (object instanceof SeaGatherer) {
            SeaGatherer gatherer = (SeaGatherer)object;
            return gatherer.isAlive() && gatherer.isIdle();
        }
        return false;
    }

    /**
     * Returns the {@link ResourceType type} of the resource the given
     * {@link GameObject gatherer} is obtaining resources from, if any. Returns
     * {@code null} if the given {@code GameObject gatherer} is not gathering.
     */
    public static ResourceType getGatheringType(GameObject object) {
        for (Action action: object.getActions()) {
            Identifier identifier = action.getIdentifier();
            if (identifier instanceof GatherActions) {
                return GatherActions.targetedResource((GatherActions)identifier);
            }
        }
        return null;
    }
}
