/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ai.operation.invade;

import com.evilbird.engine.object.GameObject;
import com.evilbird.warcraft.object.common.query.UnitOperations;

import java.util.Comparator;

/**
 * Sorts a collection of {@link GameObject game objects}, prioritising
 * buildings followed by gatherers.
 *
 * @author Blair Butterworth
 */
public class SelectTargetPriority implements Comparator<GameObject>
{
    @Override
    public int compare(GameObject gameObjectA, GameObject gameObjectB) {
        boolean aIsBuilding = UnitOperations.isBuilding(gameObjectA);
        boolean bIsBuilding = UnitOperations.isBuilding(gameObjectB);

        if (aIsBuilding || bIsBuilding) {
            return Boolean.compare(aIsBuilding, bIsBuilding);
        }
        boolean aIsGatherer = UnitOperations.isGatherer(gameObjectA);
        boolean bIsGatherer = UnitOperations.isGatherer(gameObjectB);

        if (aIsGatherer || bIsGatherer) {
            return Boolean.compare(aIsGatherer, bIsGatherer);
        }
        return 0;
    }
}
