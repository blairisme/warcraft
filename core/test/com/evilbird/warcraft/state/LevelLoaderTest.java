/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.state;

import com.evilbird.engine.object.GameObjectContainer;
import org.junit.Assert;
import org.junit.Test;

/**
 * Instances of this unit test validate the {@link LevelLoader} class.
 *
 * @author Blair Butterworth
 */
public class LevelLoaderTest extends StateTestCase
{
    @Test
    public void loadTest() {
        loadAssets();
        GameObjectContainer result = levelLoader.load("/warcraft/state/level.tmx");

        Assert.assertNotNull(result);
        Assert.assertEquals(6, result.getObjects().size());
    }
}