/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.common.path;

import com.evilbird.engine.object.GameObjectContainer;
import com.evilbird.warcraft.item.unit.gatherer.Gatherer;
import com.evilbird.warcraft.item.unit.resource.Resource;
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