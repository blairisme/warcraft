/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.state.map;

import com.evilbird.engine.common.maps.TiledMapLoader;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.test.testcase.GameTestCase;
import com.evilbird.test.utils.TestFileHandleResolver;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Instances of this unit test validate the {@link LevelLoader} class.
 *
 * @author Blair Butterworth
 */
public class LevelLoaderTest extends GameTestCase
{
    private TiledMapLoader mapLoader;
    private LevelLoader assetloader;

    @Before
    public void setup() {
        super.setup();
        mapLoader = new TiledMapLoader(new TestFileHandleResolver());
        assetloader = new LevelLoader(itemFactory, mapLoader);
    }

    @Test
    public void loadTest() {
        ItemRoot result = assetloader.load("/warcraft/state/level.tmx");
        Assert.assertNotNull(result);
        Assert.assertEquals(6, result.getItems().size());
    }
}