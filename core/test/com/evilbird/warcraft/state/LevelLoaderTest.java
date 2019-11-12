/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
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