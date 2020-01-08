/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.common.path;

import com.evilbird.engine.object.GameObjectContainer;
import com.evilbird.warcraft.object.unit.combatant.gatherer.Gatherer;
import com.evilbird.warcraft.object.unit.resource.Resource;
import com.evilbird.warcraft.state.StateTestCase;
import org.junit.Test;

import static com.evilbird.engine.object.utility.GameObjectPredicates.withClazz;
import static org.junit.Assert.assertTrue;

/**
 * Instances of this unit test validate the {@link ItemPathFinder} class.
 *
 * @author Blair Butterworth
 */
public class ItemPathFinderTest extends StateTestCase
{
    @Test
    public void hasPathTest() {
        loadAssets();
        GameObjectContainer world = levelLoader.load("/warcraft/state/level.tmx");

        Gatherer gatherer = (Gatherer)world.find(withClazz(Gatherer.class));
        Resource goldmine = (Resource)world.find(withClazz(Resource.class));

        assertTrue(ItemPathFinder.hasPath(gatherer, goldmine));
    }
}