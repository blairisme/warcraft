/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.state.level;

import com.evilbird.engine.common.maps.TiledMapLoader;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.test.testcase.GameTestCase;
import com.evilbird.test.utils.TestFileHandleResolver;
import com.evilbird.warcraft.state.map.WarcraftLevelLoader;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Instances of this unit test validate the {@link com.evilbird.warcraft.state.map.WarcraftLevelLoader} class.
 *
 * @author Blair Butterworth
 */
public class WarcraftLevelLoaderTest extends GameTestCase
{
    private TiledMapLoader mapLoader;
    private com.evilbird.warcraft.state.map.WarcraftLevelLoader assetloader;

    @Before
    public void setup() {
        super.setup();
        mapLoader = new TiledMapLoader(new TestFileHandleResolver());
        assetloader = new WarcraftLevelLoader(itemFactory, mapLoader);
    }

    @Test
    public void loadTest() {
        ItemRoot result = assetloader.load("/warcraft/state/level.tmx");
        Assert.assertNotNull(result);
        Assert.assertEquals(7, result.getItems().size());
    }
}