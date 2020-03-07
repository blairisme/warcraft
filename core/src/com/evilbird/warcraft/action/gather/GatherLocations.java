/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.gather;

import com.evilbird.engine.common.collection.Lists;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectComposite;
import com.evilbird.warcraft.data.resource.ResourceType;
import com.evilbird.warcraft.object.common.capability.MovableObject;

import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

import static com.evilbird.engine.common.collection.CollectionUtils.findFirst;
import static com.evilbird.engine.object.utility.GameObjectComparators.closestItem;
import static com.evilbird.engine.object.utility.GameObjectPredicates.touchableWithType;
import static com.evilbird.warcraft.object.common.query.UnitPredicates.hasPathTo;
import static com.evilbird.warcraft.object.common.query.UnitPredicates.isDepotFor;

/**
 * A utility class for finding the closest resources and resource depots
 * relative to a given gatherer.
 *
 * @author Blair Butterworth
 */
public class GatherLocations
{
    /**
     * Disable construction of static helper class.
     */
    private GatherLocations() {
    }

    /**
     * Returns the closest resource of the given type to the specified locus.
     */
    public static GameObject closestResource(MovableObject gatherer, GameObject locus, Identifier resource) {
        return getClosest(gatherer.getRoot(), gatherer, locus, touchableWithType(resource));
    }

    /**
     * Returns the closest resource depot to the given gatherer.
     */
    public static GameObject closestDepot(MovableObject gatherer, ResourceType depot) {
        return getClosest(gatherer.getParent(), gatherer, gatherer, isDepotFor(depot));
    }

    private static GameObject getClosest(
        GameObjectComposite container,
        MovableObject source,
        GameObject locus,
        Predicate<GameObject> applicability)
    {
        Collection<GameObject> gameObjects = container.findAll(applicability);
        if (! gameObjects.isEmpty()) {
            List<GameObject> closest = Lists.toList(gameObjects);
            Lists.sort(closest, closestItem(locus));
            return findFirst(closest, hasPathTo(source));
        }
        return null;
    }
}
